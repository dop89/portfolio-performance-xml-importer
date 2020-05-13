package com.githug.dop89.importer

import com.github.dop89.importer.PortfolioPerformanceImporter
import com.github.dop89.importer.model.PortfolioPosition
import com.github.dop89.importer.model.PortfolioSummary
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.math.BigDecimal


class PortfolioPerformanceImporterTest {

    @Test
    fun `should import the test file`() {
        val xmlFile = PortfolioPerformanceImporterTest::class.java.getResource("/kommer.xml").readText()
        assertNotNull(PortfolioPerformanceImporter().import(xmlFile))
    }

    @Test
    fun `should parse the test file and create portfolio summary`() {
        val xmlFile = PortfolioPerformanceImporterTest::class.java.getResource("/kommer.xml").readText()
        val result = PortfolioPerformanceImporter().import(xmlFile).getPortfolioSummary()

        assertEquals(
            PortfolioSummary(
                listOf(
                    PortfolioPosition(
                        isin = "DE0006289473",
                        symbol = "EXHB.DE",
                        name = "iShares eb.rexx Government Germany 1,5-2,5",
                        shares = BigDecimal("351.000000")
                    ),
                    PortfolioPosition(
                        isin = "FR0010222224",
                        symbol = "LYQ2.DE",
                        name = "Lyxor ETF EuroMTS 1-3Y",
                        shares = BigDecimal("203.000000")
                    ),
                    PortfolioPosition(
                        isin = "DE000A0HG2S8",
                        symbol = "IBCI.F",
                        name = "iShares Barclays Capital Euro Inflation Linked Bonds",
                        shares = BigDecimal("115.000000")
                    ),
                    PortfolioPosition(
                        isin = "DE000A0D8Q49",
                        symbol = "EXX5.DE",
                        name = "iShares DJ US Select Divident (DE)",
                        shares = BigDecimal("235.000000")
                    ),
                    PortfolioPosition(
                        isin = "DE0002635299",
                        symbol = "EXSH.DE",
                        name = "iShares DJ STOXX Select Dividend 30",
                        shares = BigDecimal("431.000000")
                    ),
                    PortfolioPosition(
                        isin = "DE000A0H0744",
                        symbol = "EXXW.DE",
                        name = "iShares DJ Asia Pacific Select Divident 30 (DE)",
                        shares = BigDecimal("96.000000")
                    ),
                    PortfolioPosition(
                        isin = "LU0392496005",
                        symbol = "X023.DE",
                        name = "ComStage ETF MSCI USA Small Cap",
                        shares = BigDecimal("374.000000")
                    ),
                    PortfolioPosition(
                        isin = "LU0322253906",
                        symbol = "DX2J.DE",
                        name = "db x-trackers MSCI European Small Cap TRN",
                        shares = BigDecimal("334.000000")
                    ),
                    PortfolioPosition(
                        isin = "DE000A0RFEC9",
                        symbol = "IUS4.DE",
                        name = "iShares MSCI Japan SmallCap",
                        shares = BigDecimal("147.000000")
                    ),
                    PortfolioPosition(
                        isin = "IE00B0M63177",
                        symbol = "IQQE.DE",
                        name = "iShares MSCI Emerging Markets UCITS ETF (Dist)",
                        shares = BigDecimal("418.000000")
                    ),
                    PortfolioPosition(
                        isin = "IE00B1FZS350",
                        symbol = "IQQ6.DE",
                        name = "iShares FTSE EPRA/NAREIT Global Property",
                        shares = BigDecimal("392.000000")
                    ),
                    PortfolioPosition(
                        isin = "LU0292106167",
                        symbol = "DXSM.DE",
                        name = "db x-trackers DBLCI-OY - balanced",
                        shares = BigDecimal("155.000000")
                    )
                )
            ), result
        )
    }

}
