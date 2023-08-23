package dev.shorthouse.coinwatch.ui.screen

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import com.google.common.truth.Truth.assertThat
import dev.shorthouse.coinwatch.model.CoinChart
import dev.shorthouse.coinwatch.model.CoinDetail
import dev.shorthouse.coinwatch.model.Percentage
import dev.shorthouse.coinwatch.model.Price
import dev.shorthouse.coinwatch.ui.model.ChartPeriod
import dev.shorthouse.coinwatch.ui.screen.detail.CoinDetailScreen
import dev.shorthouse.coinwatch.ui.screen.detail.CoinDetailUiState
import dev.shorthouse.coinwatch.ui.theme.AppTheme
import java.math.BigDecimal
import org.junit.Rule
import org.junit.Test

class CoinDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun when_uiStateLoading_should_showSkeletonLoader() {
        val uiStateLoading = CoinDetailUiState.Loading

        composeTestRule.setContent {
            AppTheme {
                CoinDetailScreen(
                    uiState = uiStateLoading,
                    onNavigateUp = {},
                    onClickFavouriteCoin = {},
                    onClickChartPeriod = {},
                    onErrorRetry = {}
                )
            }
        }

        composeTestRule.apply {
            onNodeWithContentDescription("Back").assertIsDisplayed()
            onNodeWithContentDescription("Favourite").assertIsDisplayed()
            onNodeWithText("Chart Range").assertIsDisplayed()
            onNodeWithText("Market Stats").assertExists()
        }
    }

    @Test
    fun when_uiStateError_should_showErrorState() {
        val uiStateError = CoinDetailUiState.Error("Error message")

        composeTestRule.setContent {
            AppTheme {
                CoinDetailScreen(
                    uiState = uiStateError,
                    onNavigateUp = {},
                    onClickFavouriteCoin = {},
                    onClickChartPeriod = {},
                    onErrorRetry = {}
                )
            }
        }

        composeTestRule.apply {
            onNodeWithText("An error has occurred").assertIsDisplayed()
            onNodeWithText("Error message").assertIsDisplayed()
            onNodeWithText("Retry").assertIsDisplayed()
            onNodeWithText("Retry").assertHasClickAction()
        }
    }

    @Test
    fun when_uiStateErrorRetryClicked_should_callOnErrorRetry() {
        var onErrorRetryCalled = false
        val uiStateError = CoinDetailUiState.Error("Error message")

        composeTestRule.setContent {
            AppTheme {
                CoinDetailScreen(
                    uiState = uiStateError,
                    onNavigateUp = {},
                    onClickFavouriteCoin = {},
                    onClickChartPeriod = {},
                    onErrorRetry = { onErrorRetryCalled = true }
                )
            }
        }

        composeTestRule.apply {
            onNodeWithText("Retry").performClick()
        }

        assert(onErrorRetryCalled)
    }

    @Test
    fun when_uiStateSuccess_should_showExpectedContent() {
        val uiStateSuccess = CoinDetailUiState.Success(
            CoinDetail(
                id = "ethereum",
                name = "Ethereum",
                symbol = "ETH",
                imageUrl = "https://cdn.coinranking.com/rk4RKHOuW/eth.svg",
                currentPrice = Price("1879.14"),
                marketCap = Price("225722901094"),
                marketCapRank = "2",
                volume24h = "6,627,669,115",
                circulatingSupply = "120,186,525",
                allTimeHigh = Price("4878.26"),
                allTimeHighDate = "10 Nov 2021",
                listedDate = "7 Aug 2015"
            ),
            CoinChart(
                prices = listOf(
                    BigDecimal("1755.19"),
                    BigDecimal("1749.71"),
                    BigDecimal("1750.94"),
                    BigDecimal("1748.44"),
                    BigDecimal("1743.98"),
                    BigDecimal("1740.25")
                ),
                minPrice = Price("1632.46"),
                maxPrice = Price("1922.83"),
                periodPriceChangePercentage = Percentage("7.06")
            ),
            chartPeriod = ChartPeriod.Day,
            isCoinFavourite = true
        )

        composeTestRule.setContent {
            AppTheme {
                CoinDetailScreen(
                    uiState = uiStateSuccess,
                    onNavigateUp = {},
                    onClickFavouriteCoin = {},
                    onClickChartPeriod = {},
                    onErrorRetry = {}
                )
            }
        }

        composeTestRule.apply {
            onNodeWithContentDescription("Back").assertIsDisplayed()
            onNodeWithContentDescription("Favourite").assertIsDisplayed()

            onNodeWithText("Ethereum").assertIsDisplayed()
            onNodeWithText("ETH").assertIsDisplayed()
            onNodeWithText("$1,879.14").assertIsDisplayed()
            onNodeWithText("+7.06%").assertIsDisplayed()
            onNodeWithText("Past day").assertIsDisplayed()

            onNodeWithText("1H").assertIsDisplayed()
            onNodeWithText("1D").assertIsDisplayed()
            onNodeWithText("1W").assertIsDisplayed()
            onNodeWithText("1M").assertIsDisplayed()
            onNodeWithText("3M").assertIsDisplayed()
            onNodeWithText("1Y").assertIsDisplayed()
            onNodeWithText("5Y").assertIsDisplayed()

            onNodeWithText("Chart Range").assertIsDisplayed()
            onNodeWithText("Low").assertIsDisplayed()
            onNodeWithText("$1,632.46").assertIsDisplayed()
            onNodeWithText("High").assertIsDisplayed()
            onNodeWithText("$1,922.83").assertIsDisplayed()

            onNodeWithTag("coin_detail_content")
                .performTouchInput { swipeUp(durationMillis = 500) }

            onNodeWithText("Market Stats").assertIsDisplayed()
            onNodeWithText("Market Cap Rank").assertIsDisplayed()
            onNodeWithText("2").assertIsDisplayed()
            onNodeWithText("Market Cap").assertIsDisplayed()
            onNodeWithText("$225,722,901,094.00").assertIsDisplayed()
            onNodeWithText("Volume 24h").assertIsDisplayed()
            onNodeWithText("6,627,669,115").assertIsDisplayed()
            onNodeWithText("Circulating Supply").assertIsDisplayed()
            onNodeWithText("120,186,525").assertIsDisplayed()
            onNodeWithText("All Time High").assertIsDisplayed()
            onNodeWithText("$4,878.26").assertIsDisplayed()
            onNodeWithText("All Time High Date").assertIsDisplayed()
            onNodeWithText("10 Nov 2021").assertIsDisplayed()
            onNodeWithText("Listed Date").assertIsDisplayed()
            onNodeWithText("7 Aug 2015").assertIsDisplayed()
        }
    }

    @Test
    fun when_backClicked_should_callOnNavigateUp() {
        var onNavigateUpCalled = false

        val uiStateSuccess = CoinDetailUiState.Success(
            CoinDetail(
                id = "ethereum",
                name = "Ethereum",
                symbol = "ETH",
                imageUrl = "https://cdn.coinranking.com/rk4RKHOuW/eth.svg",
                currentPrice = Price("1879.14"),
                marketCap = Price("225722901094"),
                marketCapRank = "2",
                volume24h = "6,627,669,115",
                circulatingSupply = "120,186,525",
                allTimeHigh = Price("4878.26"),
                allTimeHighDate = "10 Nov 2021",
                listedDate = "7 Aug 2015"
            ),
            CoinChart(
                prices = listOf(
                    BigDecimal("1755.19"),
                    BigDecimal("1749.71"),
                    BigDecimal("1750.94"),
                    BigDecimal("1748.44"),
                    BigDecimal("1743.98"),
                    BigDecimal("1740.25")
                ),
                minPrice = Price("1632.46"),
                maxPrice = Price("1922.83"),
                periodPriceChangePercentage = Percentage("7.06")
            ),
            chartPeriod = ChartPeriod.Day,
            isCoinFavourite = true
        )

        composeTestRule.setContent {
            AppTheme {
                CoinDetailScreen(
                    uiState = uiStateSuccess,
                    onNavigateUp = { onNavigateUpCalled = true },
                    onClickFavouriteCoin = {},
                    onClickChartPeriod = {},
                    onErrorRetry = {}
                )
            }
        }

        composeTestRule.apply {
            onNodeWithContentDescription("Back").performClick()
        }

        assertThat(onNavigateUpCalled).isTrue()
    }

    @Test
    fun when_favouriteCoinClicked_should_callOnClickFavouriteCoin() {
        var onClickFavouriteCoinCalled = false

        val uiStateSuccess = CoinDetailUiState.Success(
            CoinDetail(
                id = "ethereum",
                name = "Ethereum",
                symbol = "ETH",
                imageUrl = "https://cdn.coinranking.com/rk4RKHOuW/eth.svg",
                currentPrice = Price("1879.14"),
                marketCap = Price("225722901094"),
                marketCapRank = "2",
                volume24h = "6,627,669,115",
                circulatingSupply = "120,186,525",
                allTimeHigh = Price("4878.26"),
                allTimeHighDate = "10 Nov 2021",
                listedDate = "7 Aug 2015"
            ),
            CoinChart(
                prices = listOf(
                    BigDecimal("1755.19"),
                    BigDecimal("1749.71"),
                    BigDecimal("1750.94"),
                    BigDecimal("1748.44"),
                    BigDecimal("1743.98"),
                    BigDecimal("1740.25")
                ),
                minPrice = Price("1632.46"),
                maxPrice = Price("1922.83"),
                periodPriceChangePercentage = Percentage("7.06")
            ),
            chartPeriod = ChartPeriod.Day,
            isCoinFavourite = true
        )

        composeTestRule.setContent {
            AppTheme {
                CoinDetailScreen(
                    uiState = uiStateSuccess,
                    onNavigateUp = {},
                    onClickFavouriteCoin = { onClickFavouriteCoinCalled = true },
                    onClickChartPeriod = {},
                    onErrorRetry = {}
                )
            }
        }

        composeTestRule.apply {
            onNodeWithContentDescription("Favourite").performClick()
        }

        assertThat(onClickFavouriteCoinCalled).isTrue()
    }

    @Test
    fun when_chartPeriodsClicked_should_callOnClickChartPeriod() {
        val onClickChartPeriodMap = ChartPeriod.values()
            .associateWith { false }
            .toMutableMap()

        val uiStateSuccess = CoinDetailUiState.Success(
            CoinDetail(
                id = "ethereum",
                name = "Ethereum",
                symbol = "ETH",
                imageUrl = "https://cdn.coinranking.com/rk4RKHOuW/eth.svg",
                currentPrice = Price("1879.14"),
                marketCap = Price("225722901094"),
                marketCapRank = "2",
                volume24h = "6,627,669,115",
                circulatingSupply = "120,186,525",
                allTimeHigh = Price("4878.26"),
                allTimeHighDate = "10 Nov 2021",
                listedDate = "7 Aug 2015"
            ),
            CoinChart(
                prices = listOf(
                    BigDecimal("1755.19"),
                    BigDecimal("1749.71"),
                    BigDecimal("1750.94"),
                    BigDecimal("1748.44"),
                    BigDecimal("1743.98"),
                    BigDecimal("1740.25")
                ),
                minPrice = Price("1632.46"),
                maxPrice = Price("1922.83"),
                periodPriceChangePercentage = Percentage("7.06")
            ),
            chartPeriod = ChartPeriod.Day,
            isCoinFavourite = true
        )

        composeTestRule.setContent {
            AppTheme {
                CoinDetailScreen(
                    uiState = uiStateSuccess,
                    onNavigateUp = {},
                    onClickFavouriteCoin = {},
                    onClickChartPeriod = { onClickChartPeriodMap[it] = true },
                    onErrorRetry = {}
                )
            }
        }

        composeTestRule.apply {
            onNodeWithText("1H").performClick()
            onNodeWithText("1D").performClick()
            onNodeWithText("1W").performClick()
            onNodeWithText("1M").performClick()
            onNodeWithText("3M").performClick()
            onNodeWithText("1Y").performClick()
            onNodeWithText("5Y").performClick()
        }

        onClickChartPeriodMap.values.forEach { isChartPeriodClicked ->
            assertThat(isChartPeriodClicked).isTrue()
        }
    }
}