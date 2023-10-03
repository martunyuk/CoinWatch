package dev.shorthouse.coinwatch.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.shorthouse.coinwatch.data.mapper.CoinChartMapper
import dev.shorthouse.coinwatch.data.mapper.CoinDetailMapper
import dev.shorthouse.coinwatch.data.mapper.CoinMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideCoinMapper(): CoinMapper {
        return CoinMapper()
    }

    @Provides
    @Singleton
    fun provideCoinChartMapper(): CoinChartMapper {
        return CoinChartMapper()
    }

    @Provides
    @Singleton
    fun provideCoinDetailMapper(): CoinDetailMapper {
        return CoinDetailMapper()
    }
}