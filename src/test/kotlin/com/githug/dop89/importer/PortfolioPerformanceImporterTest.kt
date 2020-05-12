package com.githug.dop89.importer

import com.github.dop89.importer.PortfolioPerformanceImporter
import com.github.dop89.importer.model.PortfolioPosition
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class PortfolioPerformanceImporterTest {

    @Test
    fun `should import a file`() {
        val xmlFile = PortfolioPerformanceImporterTest::class.java.getResource("/kommer.xml").readText()
        val result = PortfolioPerformanceImporter().import(xmlFile)
        assertEquals(emptyList<PortfolioPosition>(), result)
    }

}
