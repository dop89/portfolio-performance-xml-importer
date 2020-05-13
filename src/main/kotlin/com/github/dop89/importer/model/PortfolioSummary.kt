package com.github.dop89.importer.model

import java.math.BigDecimal

data class PortfolioSummary(
    val items: List<PortfolioPosition>
)

data class PortfolioPosition(
    val isin: String?,
    val wkn: String? = null,
    val symbol: String? = null,
    val name: String,
    val shares: BigDecimal
)
