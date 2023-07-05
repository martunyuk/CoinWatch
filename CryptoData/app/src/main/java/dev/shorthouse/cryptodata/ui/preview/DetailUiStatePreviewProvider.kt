package dev.shorthouse.cryptodata.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.shorthouse.cryptodata.model.CoinChart
import dev.shorthouse.cryptodata.model.CoinDetail
import dev.shorthouse.cryptodata.model.Price
import dev.shorthouse.cryptodata.ui.screen.detail.DetailUiState
import kotlin.time.Duration.Companion.days

class DetailUiStatePreviewProvider : PreviewParameterProvider<DetailUiState> {
    override val values = sequenceOf(
        DetailUiState.Success(
            CoinDetail(
                id = "ethereum",
                name = "Ethereum",
                symbol = "ETH",
                image = " https://assets.coingecko.com/coins/images/279/large/ethereum.png?1595348880",
                currentPrice = Price(1879.14),
                marketCapRank = 2,
                marketCap = Price(225722901094),
                circulatingSupply = "120,186,525",
                allTimeLow = Price(0.43),
                allTimeHigh = Price(4878.26),
                allTimeLowDate = "20 Oct 2015",
                allTimeHighDate = "10 Nov 2021"
            ),
            CoinChart(
                prices = listOf(1755.19, 1749.71, 1750.94, 1748.44, 1743.98, 1740.25, 1737.53, 1730.56, 1738.12, 1736.1, 1740.2, 1740.64, 1741.49, 1738.87, 1734.92, 1736.79, 1743.53, 1743.21, 1744.75, 1744.85, 1741.76, 1741.46, 1739.82, 1740.15, 1745.08, 1743.29, 1746.12, 1745.99, 1744.89, 1741.1, 1741.91, 1738.47, 1737.67, 1741.82, 1735.95, 1728.11, 1657.23, 1649.89, 1649.71, 1650.68, 1654.04, 1648.55, 1650.1, 1651.87, 1651.29, 1642.75, 1637.79, 1635.8, 1637.01, 1632.46, 1633.31, 1640.08, 1638.61, 1645.47, 1643.5, 1640.57, 1640.41, 1641.38, 1660.21, 1665.73, 1660.33, 1665.65, 1664.11, 1665.71, 1661.9, 1661.17, 1662.54, 1665.58, 1666.27, 1669.82, 1671.34, 1669.87, 1670.62, 1668.97, 1668.86, 1664.58, 1665.96, 1664.53, 1656.15, 1670.91, 1685.59, 1693.69, 1718.1, 1719.56, 1724.42, 1717.22, 1718.34, 1716.38, 1715.37, 1716.46, 1719.39, 1717.94, 1722.92, 1755.97, 1749.11, 1742.58, 1742.88, 1743.36, 1742.95, 1739.68, 1736.65, 1739.88, 1734.35, 1727.31, 1728.35, 1724.05, 1730.04, 1726.87, 1727.71, 1728.49, 1729.93, 1726.37, 1722.92, 1726.67, 1724.76, 1728.41, 1729.2, 1728.2, 1727.98, 1729.96, 1727.8, 1732.04, 1730.22, 1733.16, 1734.14, 1734.31, 1739.62, 1737.76, 1739.52, 1742.98, 1738.36, 1740.77, 1729.88, 1725.04, 1725.1, 1719.27, 1721.85, 1725.61, 1726.42, 1724.03, 1721.39, 1726.32, 1724.28, 1724.99, 1721.97, 1723.03, 1723.98, 1724.5, 1730.3, 1727.67, 1719.28, 1720.27, 1716.18, 1728.95, 1732.22, 1722.28, 1730.7, 1729.73, 1733.56, 1734.79, 1740.08, 1733.49, 1736.26, 1731.89, 1730.01, 1728.34, 1728.55, 1729.4, 1725.16, 1729.71, 1730.05, 1729.59, 1732.05, 1727.89, 1719.39, 1732.66, 1744.44, 1755.89, 1783.58, 1778.96, 1783.33, 1775.72, 1785.55, 1792.12, 1798.66, 1806.81, 1808.94, 1810.84, 1814.58, 1820.17, 1810.84, 1812.01, 1809.21, 1813.83, 1812.75, 1829.03, 1816.93, 1837.53, 1839.58, 1850.36, 1882.71, 1870.32, 1877.66, 1881.27, 1879.38, 1878.25, 1885.97, 1892.9, 1913.18, 1916.7, 1922.83, 1916.26, 1917.31, 1910.83, 1909.37, 1909.37, 1911.29, 1907.12, 1907.19, 1909.27, 1888.02, 1897.01, 1900.49, 1883.55, 1874.06, 1883.34, 1889.46, 1884.2, 1887.19, 1886.87, 1879.59, 1876.3, 1874.18, 1877.32, 1880.43, 1877.59, 1881.71, 1880.7, 1882.79, 1880.07, 1881.19, 1885.27, 1881.64, 1879.87, 1878.65, 1874.2, 1869.61, 1882.54, 1907.42, 1910.82, 1919.61, 1907.74, 1901.6, 1903.97, 1888.26, 1890.3, 1891.35, 1882.18, 1887.68, 1897.15, 1895.12, 1893.61, 1895.11, 1890.69, 1884.26, 1887.12, 1888.96, 1893.2, 1892.08, 1896.97, 1892.07, 1892.55, 1876.03, 1876.83, 1884.31, 1887.24, 1888.57, 1879.8, 1875.91, 1875.56, 1875.06, 1874.24, 1879.45, 1885.54, 1895.19, 1911.3, 1913.14, 1921.48, 1911.8, 1919.84, 1920.17, 1917.25, 1916.8, 1914.08, 1906.3, 1892.66, 1885.78, 1893.61, 1892.49, 1891.53, 1892.39, 1898.1, 1895.87, 1901.73, 1899.96, 1895.2, 1878.62, 1876.8, 1877.3, 1877.53, 1878.34, 1890.65, 1892.47, 1891.95, 1886.42, 1877.5, 1880.18, 1879.6, 1900.87, 1881.74, 1891.43, 1860.83, 1848.03, 1851.01, 1854.68, 1853.67, 1852.61, 1859.33, 1859.8, 1863.04, 1863.72, 1867.58, 1873.66, 1871.94, 1866.43, 1866.74, 1877.48, 1877.33),
                minPrice = Price(1632.46),
                minPriceChangePercentage = 15.11,
                maxPrice = Price(1922.83),
                maxPriceChangePercentage = -2.27,
                periodPriceChangePercentage = 7.06
            ),
            chartPeriod = 7.days
        )
    )
}
