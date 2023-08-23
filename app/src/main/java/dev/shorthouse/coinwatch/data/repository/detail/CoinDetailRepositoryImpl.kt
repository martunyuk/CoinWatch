package dev.shorthouse.coinwatch.data.repository.detail

import dev.shorthouse.coinwatch.common.Result
import dev.shorthouse.coinwatch.common.toDoubleOrZero
import dev.shorthouse.coinwatch.data.source.remote.CoinNetworkDataSourceImpl
import dev.shorthouse.coinwatch.data.source.remote.model.CoinDetailApiModel
import dev.shorthouse.coinwatch.di.IoDispatcher
import dev.shorthouse.coinwatch.model.CoinDetail
import dev.shorthouse.coinwatch.model.Price
import java.text.NumberFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class CoinDetailRepositoryImpl @Inject constructor(
    private val coinNetworkDataSource: CoinNetworkDataSourceImpl,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CoinDetailRepository {
    override fun getCoinDetail(coinId: String): Flow<Result<CoinDetail>> = flow {
        val response = coinNetworkDataSource.getCoinDetail(coinId = coinId)
        val body = response.body()

        if (response.isSuccessful && body != null) {
            emit(Result.Success(body.toCoinDetail()))
        } else {
            Timber.e("getCoinDetail unsuccessful retrofit response ${response.message()}")
            emit(Result.Error("Unable to fetch coin details"))
        }
    }.catch { e ->
        Timber.e("getCoinDetail exception ${e.message}")
        emit(Result.Error("Unable to fetch coin details"))
    }.flowOn(ioDispatcher)

    private fun CoinDetailApiModel.toCoinDetail(): CoinDetail {
        val numberGroupingFormat = NumberFormat.getNumberInstance(Locale.US).apply {
            isGroupingUsed = true
        }

        val dateFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.US)

        val coinDetail = coinDetailData.coinDetail

        return CoinDetail(
            id = coinDetail.id.orEmpty(),
            name = coinDetail.name.orEmpty(),
            symbol = coinDetail.symbol.orEmpty(),
            imageUrl = coinDetail.iconUrl.orEmpty(),
            currentPrice = Price(coinDetail.currentPrice),
            marketCap = Price(coinDetail.marketCap),
            marketCapRank = coinDetail.marketCapRank.orEmpty(),
            volume24h = numberGroupingFormat.format(
                coinDetail.volume24h?.toDoubleOrZero()
            ),
            circulatingSupply = numberGroupingFormat.format(
                coinDetail.supply?.circulatingSupply?.toDoubleOrZero()
            ),
            allTimeHigh = Price(coinDetail.allTimeHigh?.price),
            allTimeHighDate = dateFormatter.format(
                Instant.ofEpochSecond(coinDetail.allTimeHigh?.timestamp ?: 0L)
                    .atZone(ZoneId.systemDefault())
            ),
            listedDate = dateFormatter.format(
                Instant.ofEpochSecond(coinDetail.listedAt ?: 0L)
                    .atZone(ZoneId.systemDefault())
            )
        )
    }
}