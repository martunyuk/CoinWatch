package dev.shorthouse.cryptodata.data.repository

import dev.shorthouse.cryptodata.common.Resource
import dev.shorthouse.cryptodata.data.source.remote.CoinApi
import dev.shorthouse.cryptodata.model.Coin
import dev.shorthouse.cryptodata.model.CoinDetail
import dev.shorthouse.cryptodata.model.toCoin
import dev.shorthouse.cryptodata.model.toCoinDetail
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinApi
) : CoinRepository {
    override fun getCryptocurrencies(): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading())

        try {
            val response = api.getCoins()

            if (response.isSuccessful && response.body() != null) {
                val cryptocurrencies = response.body()!!.map {
                    it.toCoin()
                }

                emit(Resource.Success(cryptocurrencies))
            } else {
                val errorMessage = response.errorBody()?.string().orEmpty().ifEmpty {
                    "An unexpected error occurred"
                }

                emit(Resource.Error(errorMessage))
            }
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

    override fun getCoinDetail(coinId: String): Flow<Resource<CoinDetail>> = flow {
        emit(Resource.Loading())

        try {
            val response = api.getCoinDetail(coinId = coinId)

            if (response.isSuccessful && response.body() != null) {
                val coinDetail = response.body()!!.toCoinDetail()

                emit(Resource.Success(coinDetail))
            } else {
                val errorMessage = response.errorBody()?.string().orEmpty().ifEmpty {
                    "An unexpected error occurred"
                }

                emit(Resource.Error(errorMessage))
            }
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}
