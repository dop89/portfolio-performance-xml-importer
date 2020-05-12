package com.githug.dop89.importer

import com.github.dop89.importer.PortfolioPerformanceImporter
import com.github.dop89.importer.model.PortfolioPosition
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class PortfolioPerformanceImporterTest {

    @Test
    fun `should import a file`() {
        val result = PortfolioPerformanceImporter().import()
        assertEquals(emptyList<PortfolioPosition>(), result.items)
    }

}
