package com.github.dop89.importer.xml

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

// TODO check which fields are optional and which are mandatory and change them here
data class Client(
    val accounts: List<Account>,
    val portfolios: List<Portfolio>,
    val securities: List<Security>
)

data class Portfolio(
    val uuid: UUID?,
    val name: String,
    val referenceAccount: Account?,
    val transactions: List<PortfolioTransaction> = emptyList(),
    val reference: String?


) {
    override fun toString(): String {
        return "Portfolio(uuid=$uuid, name='$name', referenceAccount=$referenceAccount, transactions=$transactions, reference=$reference)"
    }
}

data class PortfolioTransaction(
    val date: DateRef?,
    val currencyCode: String?,
    val amount: Long,
    val security: Security,
    val shares: Long,
    val type: AccountTransactionType,
    val crossEntry: CrossEntry?


) {
    override fun toString(): String {
        return "PortfolioTransaction(date=$date, currencyCode=$currencyCode, amount=$amount, security=$security, shares=$shares, type=$type)"
    }
}

data class Account(
    val uuid: UUID,
    val name: String,
    val currencyCode: String?,
    val transactions: List<AccountTransaction> = emptyList()


) {
    override fun toString(): String {
        return "Account(uuid=$uuid, name='$name', currencyCode=$currencyCode, transactionsCount=${transactions.size})"
    }
}

data class AccountTransaction(
    val date: DateRef,
    val type: AccountTransactionType?,
    val currencyCode: String?,
    val amount: Long,
    val security: Security?,
    val crossEntry: CrossEntry?,
    val shares: Long?,
    val note: String?


) {
    override fun toString(): String {
        return "AccountTransaction(date=$date, type=$type, currencyCode=$currencyCode, amount=$amount, security=$security, shares=$shares, note=$note)"
    }
}

data class DateRef(
    val date: LocalDateTime?,
    val value: Long?
)

data class Security(
    val uuid: String?,
    val name: String,
    val currencyCode: String?,
    val isin: String?,
    val wkn: String?,
    val tickerSymbol: String?,
    val prices: List<SecurityPrice> = emptyList()
)

data class SecurityPrice(
    val date: LocalDate,
    val value: Long
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
