package com.github.dop89.importer

import com.github.dop89.importer.model.PortfolioPosition
import com.github.dop89.importer.model.PortfolioSummary
import com.github.dop89.importer.xml.*
import com.github.dop89.importer.xml.converter.DateConverter
import com.thoughtworks.xstream.XStream
import java.math.BigDecimal


class PortfolioPerformanceImporter {

    fun import(xmlFile: String): PortfolioSummary {
        val xStream = XStream()

        xStream.registerConverter(DateConverter())

        xStream.alias("account", Account::class.java)
        xStream.alias("account-transaction", AccountTransaction::class.java)
        xStream.alias("buysell", BuySell::class.java)
        xStream.alias("client", Client::class.java)
        xStream.alias("portfolio", Portfolio::class.java)
        xStream.alias("portfolio-transaction", PortfolioTransaction::class.java)
        xStream.alias("security", Security::class.java)

        xStream.classLoader = Client::class.java.classLoader
        xStream.ignoreUnknownElements()
        xStream.allowTypes(listOf(Client::class.java).toTypedArray())

        val portfolio = xStream.fromXML(xmlFile) as Client
        return convertToPortfolioSummary(portfolio)
    }

    private fun convertToPortfolioSummary(client: Client): PortfolioSummary {
        // merge all transactions from all portfolios together
        val allTransactions = client.portfolios.flatMap { it.transactions }
        val items = allTransactions.groupBy { it.security }.map { security ->
                Pair(
                    security.key,
                    security.value.map { transaction ->
                        when (transaction.type) {
                            AccountTransactionType.BUY -> transaction.shares
                            AccountTransactionType.SELL -> transaction.shares * -1 // multiply by -1 to subtract
                            else -> 0
                        }
                    }.sum() ) // maybe add price here
            }.map { i -> PortfolioPosition( i.first.isin, i.first.wkn, i.first.tickerSymbol, i.first.name, convertSharesCount(i.second)) }
            return PortfolioSummary(items)
    }

    /**
     * Converts the number of shares from Long value into BigDecimal by shifting the floating point 6 digits to the left
     */
    private fun convertSharesCount(shares: Long) = BigDecimal(shares).movePointLeft(6)

}

data class Transaction(
    val shares: Long,
    val type: AccountTransactionType
)
