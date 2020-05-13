package com.github.dop89.importer

import com.github.dop89.importer.model.PortfolioPosition
import com.github.dop89.importer.model.PortfolioSummary
import com.github.dop89.importer.xml.AccountTransactionType
import com.github.dop89.importer.xml.Client
import java.math.BigDecimal

class ImportedPortfolio(
    private val client: Client
) {

    /**
     * Returns a summary over all portfolios with aggregated stocks
     */
    fun getPortfolioSummary(): PortfolioSummary {
        // merge all transactions from all portfolios together
        val allTransactions = client.portfolios.flatMap { it.transactions }
        // TODO is group by security correct? what happens if security objects are not equal but they have the same isin? Is it better to group by isin?
        val items = allTransactions.groupBy { it.security }.map { security ->
            Pair(
                security.key,
                security.value.map { transaction ->
                    when (transaction.type) {
                        AccountTransactionType.BUY -> transaction.shares
                        AccountTransactionType.SELL -> transaction.shares * -1 // multiply by -1 to subtract
                        else -> 0
                    }
                }.sum()
            )
        }.map { symbolSharePair ->
            PortfolioPosition(
                symbolSharePair.first.isin,
                symbolSharePair.first.wkn,
                symbolSharePair.first.tickerSymbol,
                symbolSharePair.first.name,
                convertSharesCount(symbolSharePair.second)
            )
        }
        return PortfolioSummary(items)
    }

    /**
     * Converts the number of shares from Long value into BigDecimal by shifting the floating point 6 digits to the left
     */
    private fun convertSharesCount(shares: Long) = BigDecimal(shares).movePointLeft(6)
}
