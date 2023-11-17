package dev.shorthouse.coinwatch.data.datastore

import androidx.annotation.StringRes
import dev.shorthouse.coinwatch.R
import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
    val coinSort: CoinSort = CoinSort.MarketCap,
    val currency: Currency = Currency.USD
)

enum class Currency(val symbol: String, @StringRes val nameId: Int) {
    USD("$", R.string.currency_usd),
    GBP("£", R.string.currency_gbp),
    EUR("€", R.string.currency_eur)
}

enum class CoinSort(@StringRes val nameId: Int) {
    MarketCap(R.string.coin_sort_market_cap),
    Price(R.string.coin_sort_price),
    PriceChange24h(R.string.coin_sort_price_change),
    Volume24h(R.string.coin_sort_volume)
}
