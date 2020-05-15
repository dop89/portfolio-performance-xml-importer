package com.github.dop89.importer.model

import java.math.BigDecimal

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
    val amount: BigDecimal
)
