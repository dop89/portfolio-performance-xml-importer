package com.github.dop89.importer.model

import java.math.BigDecimal

data class PortfolioSummary(
    val items: List<PortfolioPosition>
)

data class PortfolioPosition(
    val isin: String?,
    val wkn: String?,
    val symbol: String?,
    val name: String,
    val amount: BigDecimal,
    val value: BigDecimal
)
