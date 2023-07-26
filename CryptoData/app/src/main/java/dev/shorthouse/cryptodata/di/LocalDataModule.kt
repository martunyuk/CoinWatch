package dev.shorthouse.cryptodata.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.shorthouse.cryptodata.data.repository.favouriteCoin.FavouriteCoinRepository
import dev.shorthouse.cryptodata.data.repository.favouriteCoin.FavouriteCoinRepositoryImpl
import dev.shorthouse.cryptodata.data.source.local.CoinDatabase
import dev.shorthouse.cryptodata.data.source.local.CoinLocalDataSource
import dev.shorthouse.cryptodata.data.source.local.FavouriteCoinDao
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    @Singleton
    fun provideFavouriteCoinRepository(
        coinLocalDataSource: CoinLocalDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): FavouriteCoinRepository {
        return FavouriteCoinRepositoryImpl(
            coinLocalDataSource = coinLocalDataSource,
            ioDispatcher = ioDispatcher
        )
    }

    @Provides
    @Singleton
    fun provideCoinLocalDataSource(favouriteCoinDao: FavouriteCoinDao): CoinLocalDataSource {
        return CoinLocalDataSource(favouriteCoinDao = favouriteCoinDao)
    }

    @Provides
    @Singleton
    fun provideFavouriteCoinDao(database: CoinDatabase): FavouriteCoinDao {
        return database.favouriteCoinDao()
    }

    @Provides
    @Singleton
    fun provideCoinDatabase(@ApplicationContext context: Context): CoinDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CoinDatabase::class.java,
            "Coin.db"
        ).build()
    }
}