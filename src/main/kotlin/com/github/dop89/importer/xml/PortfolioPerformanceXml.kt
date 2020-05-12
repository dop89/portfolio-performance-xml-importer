package com.github.dop89.importer.xml

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.time.LocalDateTime
import java.util.*

@JacksonXmlRootElement(localName = "client")
data class PortfolioPerformanceXml(
    val version: Int,
    val baseCurrency: String,
    val securities: List<Security>,
    val accounts: List<Account>
)

data class Security(
    val uuid: UUID,
    val name: String,
    val currencyCode: String?,
    val isin: String?,
    val tickerSymbol: String?
)

data class Account(
    val uuid: UUID,
    val name: String,
    val currencyCode: String?,
    val transactions: List<AccountTransaction> = emptyList()
)

data class AccountTransaction(
    val date: String?,
    val currencyCode: String?,
    val amount: Long,
    val type: AccountTransactionType?,
    val crossEntry: CrossEntry?,
    @JacksonXmlProperty(isAttribute = true, localName = "reference")
    val reference: String?
)

data class CrossEntry(
    val portfolio: Portfolio?,
    val reference: String?,
    @JsonProperty("class") val clazz: String?
) {
    companion object {
        @JvmStatic
        @JsonCreator
        fun construct(reference: String?, @JsonProperty("class") clazz: String?): CrossEntry {
            return CrossEntry(null, reference, clazz)
        }
    }
}

data class Portfolio(
    val uuid: UUID,
    val name: String,
    val referenceAccount: String,
    val transactions: List<PortfolioTransaction> = emptyList()
)

data class PortfolioTransaction(
    val date: DateRef?,
    val currencyCode: String?,
    val amount: Long,
    val security: SecurityRef?,
    val shares: Long?,
    val type: AccountTransactionType?
)

data class SecurityRef (
    val reference: String?
) {

    companion object {
        @JvmStatic
        @JsonCreator
        fun construct(reference: String?): SecurityRef {
            return SecurityRef(reference)
        }
    }
}

data class DateRef(
    val reference: String?,
    val date: LocalDateTime?
) {

    companion object {
        @JvmStatic
        @JsonCreator
        fun construct(reference: String?, date: String?): DateRef {
            return DateRef(reference, null)
        }

        @JvmStatic
        @JsonCreator
        fun construct(data: String?): DateRef {
            return DateRef(null, LocalDateTime.parse(data))
        }
    }
}

enum class AccountTransactionType {
    BUY, DEPOSIT, DIVIDENDS, INTEREST, SELL
}
