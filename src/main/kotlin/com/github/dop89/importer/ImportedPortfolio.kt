package com.github.dop89.importer

import com.github.dop89.importer.model.LatestPrice
import com.github.dop89.importer.model.PortfolioPosition
import com.github.dop89.importer.model.PortfolioSummary
import com.github.dop89.importer.xml.AccountTransactionType
import com.github.dop89.importer.xml.Client
import com.github.dop89.importer.xml.Security
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
            val totalShares = security.value.map { transaction ->
                when (transaction.type) {
                    AccountTransactionType.BUY -> transaction.shares
                    AccountTransactionType.SELL -> transaction.shares * -1 // multiply by -1 to subtract
                    else -> 0
                }
            }.sum()
            val totalAmount = security.value.map { transaction ->
                when (transaction.type) {
                    AccountTransactionType.BUY -> transaction.amount
                    AccountTransactionType.SELL -> transaction.amount * -1 // multiply by -1 to subtract
                    else -> 0
                }
            }.sum()

            AggregatedTransaction(
                security.key,
                totalShares,
                totalAmount
            )
        }.map { aggregatedTransaction ->
            val shares = convertSharesCount(aggregatedTransaction.shares)
            val amount = convertAmount(aggregatedTransaction.amount)

            PortfolioPosition(
                aggregatedTransaction.security.isin,
                aggregatedTransaction.security.wkn,
                aggregatedTransaction.security.tickerSymbol,
                aggregatedTransaction.security.name,
                shares,
                amount,
                findLatestPrice(aggregatedTransaction.security.isin)
            )
        }
        return PortfolioSummary(items)
    }

    private fun findLatestPrice(isin: String?): LatestPrice {
        val price = client.securities.find { it.isin == isin }?.prices?.lastOrNull()
        val priceValue = BigDecimal(price?.value ?: 0).movePointLeft(4)

        return LatestPrice(price?.date, priceValue)
    }

    /**
     * Converts the number of shares from Long value into BigDecimal by shifting the floating point 6 digits to the left
     */
    private fun convertSharesCount(shares: Long) = BigDecimal(shares).movePointLeft(6)

    /**
     * Converts the amount (total price) from Long value into BigDecimal by shifting the floating point 2 digits to the left
     */
    private fun convertAmount(amount: Long) = BigDecimal(amount).movePointLeft(2)
}

data class AggregatedTransaction(
    val security: Security,
    val shares: Long,
    val amount: Long
)
