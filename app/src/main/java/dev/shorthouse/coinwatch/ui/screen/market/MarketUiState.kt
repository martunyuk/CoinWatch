package dev.shorthouse.coinwatch.ui.screen.market

import dev.shorthouse.coinwatch.data.datastore.CoinSort
import dev.shorthouse.coinwatch.data.datastore.Currency
import dev.shorthouse.coinwatch.data.source.local.model.CachedCoin
import dev.shorthouse.coinwatch.ui.model.TimeOfDay
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class MarketUiState(
    val coins: ImmutableList<CachedCoin> = persistentListOf(),
    val coinSort: CoinSort = CoinSort.MarketCap,
    val showCoinSortBottomSheet: Boolean = false,
    val currency: Currency = Currency.USD,
    val showCurrencyBottomSheet: Boolean = false,
    val isRefreshing: Boolean = false,
    val timeOfDay: TimeOfDay = TimeOfDay.Morning,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
