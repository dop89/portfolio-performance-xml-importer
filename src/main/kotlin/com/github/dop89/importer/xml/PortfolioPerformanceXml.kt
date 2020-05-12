package com.github.dop89.importer.xml

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "client")
data class PortfolioPerformanceXml(
    val version: Int,
    val baseCurrency: String
)
