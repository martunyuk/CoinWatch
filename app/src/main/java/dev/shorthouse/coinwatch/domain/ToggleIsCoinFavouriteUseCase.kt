package dev.shorthouse.coinwatch.domain

import dev.shorthouse.coinwatch.data.repository.favouriteCoinId.FavouriteCoinIdRepository
import dev.shorthouse.coinwatch.data.source.local.model.FavouriteCoinId
import javax.inject.Inject

class ToggleIsCoinFavouriteUseCase @Inject constructor(
    private val favouriteCoinIdRepository: FavouriteCoinIdRepository
) {
    suspend operator fun invoke(favouriteCoinId: FavouriteCoinId) {
        return toggleIsCoinFavourite(favouriteCoinId = favouriteCoinId)
    }

    private suspend fun toggleIsCoinFavourite(favouriteCoinId: FavouriteCoinId) {
        return favouriteCoinIdRepository.toggleIsCoinFavourite(favouriteCoinId = favouriteCoinId)
    }
}
