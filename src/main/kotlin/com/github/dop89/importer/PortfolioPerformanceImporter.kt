package com.github.dop89.importer

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.dop89.importer.model.PortfolioSummary
import com.github.dop89.importer.xml.PortfolioPerformanceXml


class PortfolioPerformanceImporter {

    fun import(xmlFile: String): PortfolioSummary {
        val mapper = getObjectMapper()
        val model = mapper.readValue(xmlFile, PortfolioPerformanceXml::class.java)

        return PortfolioSummary(emptyList())
    }

    private fun getObjectMapper() =
        XmlMapper()
            .registerModule(KotlinModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
}
