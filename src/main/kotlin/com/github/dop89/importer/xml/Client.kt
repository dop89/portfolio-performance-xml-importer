package com.github.dop89.importer.xml

import java.time.LocalDateTime
import java.util.*

data class Client(
    val accounts: List<Account>,
    val portfolios: List<Portfolio>,
    val securities: List<Security>
)

data class Portfolio(
    val uuid: UUID?,
    val name: String?,
    val referenceAccount: Account?,
    val transactions: List<PortfolioTransaction> = emptyList(),
    val reference: String?
)

data class PortfolioTransaction(
    val date: Date?,
    val currencyCode: String?,
    val amount: Long,
    val security: Security?,
    val shares: Long?,
    val type: AccountTransactionType?,
    val crossEntry: CrossEntry?
)

data class Account(
    val uuid: UUID,
    val name: String,
    val currencyCode: String?,
    val transactions: List<AccountTransaction> = emptyList()
)

data class AccountTransaction(
    val date: Date,
    val type: AccountTransactionType?,
    val currencyCode: String?,
    val amount: Long,
    val security: Security?,
    val crossEntry: CrossEntry?,
    val shares: Long?,
    val note: String?
)

data class Date(
    val date: LocalDateTime?
)

data class Security(
    val uuid: String?,
    val name: String?,
    val currencyCode: String?,
    val isin: String?,
    val wkn: String?,
    val tickerSymbol: String?
)

interface CrossEntry {

}

enum class AccountTransactionType {
    BUY, DEPOSIT, DIVIDENDS, INTEREST, SELL
}

data class BuySell(
    val portfolio: Portfolio?,
    val portfolioTransaction: PortfolioTransaction?,
    val account: Account?,
    val accountTransaction: AccountTransaction?
) : CrossEntry
