package com.github.dop89.importer

import com.github.dop89.importer.model.PortfolioSummary
import com.github.dop89.importer.xml.*
import com.thoughtworks.xstream.XStream


class PortfolioPerformanceImporter {

    fun import(xmlFile: String): PortfolioSummary {
        val xStream =  XStream()

        xStream.alias("account", Account::class.java)
        xStream.alias("account-transaction", AccountTransaction::class.java)
        xStream.alias("buysell", BuySell::class.java)
        xStream.alias("client", Client::class.java)
        xStream.alias("portfolio", Portfolio::class.java)
        xStream.alias("portfolio-transaction", PortfolioTransaction::class.java)
        xStream.alias("security", Security::class.java)

        val classLoader = PortfolioXml::class.java.classLoader
        xStream.classLoader = classLoader
        xStream.ignoreUnknownElements()

        xStream.allowTypes(listOf(PortfolioXml::class.java, Client::class.java).toTypedArray())
        val portfolio = xStream.fromXML(xmlFile) as Client

        return PortfolioSummary(emptyList())
    }


}
