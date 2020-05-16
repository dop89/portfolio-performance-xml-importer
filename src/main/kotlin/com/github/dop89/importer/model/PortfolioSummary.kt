package com.github.dop89.importer.model

import java.math.BigDecimal
import java.time.LocalDate

data class PortfolioSummary(
    val items: List<PortfolioPosition>
)

/**
 * Representation of a portfolio position
 */
data class PortfolioPosition(
    /**
     * Isin of the security
     */
    val isin: String?,

    /**
     * Wkn of the security
     */
    val wkn: String? = null,

    /**
     * The symbol of the security
     */
    val symbol: String? = null,

    /**
     * The name of the security
     */
    val name: String,

    /**
     * The number of shares
     */
    val shares: BigDecimal,

    /**
     * The total amount (total price) of this position. Not the current market value!
     */
    val amount: BigDecimal,

    /**
     * The latest price stored in the xml file
     */
    val latestPrice: LatestPrice?
)

data class LatestPrice(
    val date: LocalDate?,
    val value: BigDecimal?
)
