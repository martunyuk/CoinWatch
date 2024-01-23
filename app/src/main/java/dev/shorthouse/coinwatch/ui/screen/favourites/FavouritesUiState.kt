package dev.shorthouse.coinwatch.ui.screen.favourites

import dev.shorthouse.coinwatch.data.source.local.model.FavouriteCoin
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class FavouritesUiState(
    val favouriteCoins: ImmutableList<FavouriteCoin> = persistentListOf(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
