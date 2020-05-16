package com.githug.dop89.importer

import com.github.dop89.importer.PortfolioPerformanceImporter
import com.github.dop89.importer.model.LatestPrice
import com.github.dop89.importer.model.PortfolioPosition
import com.github.dop89.importer.model.PortfolioSummary
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate


class PortfolioPerformanceImporterTest {

    @Test
    fun `should import the test file`() {
        val xmlFile = PortfolioPerformanceImporterTest::class.java.getResource("/kommer.xml").readText()
        assertNotNull(PortfolioPerformanceImporter().importPortfolio(xmlFile))
    }

    @Test
    fun `should parse the test file and create portfolio summary`() {
        val xmlFile = PortfolioPerformanceImporterTest::class.java.getResource("/kommer.xml").readText()
        val result = PortfolioPerformanceImporter().importPortfolio(xmlFile).getPortfolioSummary()

        assertEquals(
            PortfolioSummary(
                listOf(
                    PortfolioPosition(
                        isin = "DE0006289473",
                        symbol = "EXHB.DE",
                        name = "iShares eb.rexx Government Germany 1,5-2,5",
                        shares = BigDecimal("351.000000"),
                        amount = BigDecimal("35060.29"),
                        latestPrice = LatestPrice(LocalDate.parse("2020-04-06"), BigDecimal("84.4900"))
                    ),
                    PortfolioPosition(
                        isin = "FR0010222224",
                        symbol = "LYQ2.DE",
                        name = "Lyxor ETF EuroMTS 1-3Y",
                        shares = BigDecimal("203.000000"),
                        amount = BigDecimal("23192.74"),
                        latestPrice = LatestPrice(LocalDate.parse("2020-04-03"), BigDecimal("124.2800"))
                    ),
                    PortfolioPosition(
                        isin = "DE000A0HG2S8",
                        symbol = "IBCI.F",
                        name = "iShares Barclays Capital Euro Inflation Linked Bonds",
                        shares = BigDecimal("115.000000"),
                        amount = BigDecimal("18860.45"),
                        latestPrice = LatestPrice(LocalDate.parse("2020-04-03"), BigDecimal("206.9500"))
                    ),
                    PortfolioPosition(
                        isin = "DE000A0D8Q49",
                        symbol = "EXX5.DE",
                        name = "iShares DJ US Select Divident (DE)",
                        shares = BigDecimal("235.000000"),
                        amount = BigDecimal("3620.79"),
                        latestPrice = LatestPrice(LocalDate.parse("2020-05-08"), BigDecimal("51.4100"))
                    ),
                    PortfolioPosition(
                        isin = "DE0002635299",
                        symbol = "EXSH.DE",
                        name = "iShares DJ STOXX Select Dividend 30",
                        shares = BigDecimal("431.000000"),
                        amount = BigDecimal("4021.80"),
                        latestPrice = LatestPrice(LocalDate.parse("2020-05-11"), BigDecimal("12.8500"))
                    ),
                    PortfolioPosition(
                        isin = "DE000A0H0744",
                        symbol = "EXXW.DE",
                        name = "iShares DJ Asia Pacific Select Divident 30 (DE)",
                        shares = BigDecimal("96.000000"),
                        amount = BigDecimal("619.92"),
                        latestPrice = LatestPrice(LocalDate.parse("2020-05-11"), BigDecimal("21.1500"))
                    ),
                    PortfolioPosition(
                        isin = "LU0392496005",
                        symbol = "X023.DE",
                        name = "ComStage ETF MSCI USA Small Cap",
                        shares = BigDecimal("374.000000"),
                        amount = BigDecimal("1660.82"),
                        latestPrice = LatestPrice(LocalDate.parse("2020-05-11"), BigDecimal("37.8950"))
                    ),
                    PortfolioPosition(
                        isin = "LU0322253906",
                        symbol = "DX2J.DE",
                        name = "db x-trackers MSCI European Small Cap TRN",
                        shares = BigDecimal("334.000000"),
                        amount = BigDecimal("1567.26"),
                        latestPrice = LatestPrice(LocalDate.parse("2020-05-08"), BigDecimal("38.8450"))
                    ),
                    PortfolioPosition(
                        isin = "DE000A0RFEC9",
                        symbol = "IUS4.DE",
                        name = "iShares MSCI Japan SmallCap",
                        shares = BigDecimal("147.000000"),
                        amount = BigDecimal("1929.12"),
                        latestPrice = LatestPrice(LocalDate.parse("2020-04-03"), BigDecimal("28.3350"))
                    ),
                    PortfolioPosition(
                        isin = "IE00B0M63177",
                        symbol = "IQQE.DE",
                        name = "iShares MSCI Emerging Markets UCITS ETF (Dist)",
                        shares = BigDecimal("418.000000"),
                        amount = BigDecimal("3905.67"),
                        latestPrice = LatestPrice(LocalDate.parse("2020-04-03"), BigDecimal("29.5260"))
                    ),
                    PortfolioPosition(
                        isin = "IE00B1FZS350",
                        symbol = "IQQ6.DE",
                        name = "iShares FTSE EPRA/NAREIT Global Property",
                        shares = BigDecimal("392.000000"),
                        amount = BigDecimal("1195.28"),
                        latestPrice = LatestPrice(LocalDate.parse("2020-05-11"), BigDecimal("19.4000"))
                    ),
                    PortfolioPosition(
                        isin = "LU0292106167",
                        symbol = "DXSM.DE",
                        name = "db x-trackers DBLCI-OY - balanced",
                        shares = BigDecimal("155.000000"),
                        amount = BigDecimal("4290.98"),
                        latestPrice = LatestPrice(LocalDate.parse("2020-04-06"), BigDecimal("12.9440"))
                    )
                )
            ), result
        )
    }

}
