package com.github.dop89.importer

import com.github.dop89.importer.xml.*
import com.github.dop89.importer.xml.converters.DateConverter
import com.thoughtworks.xstream.XStream


class PortfolioPerformanceImporter {

    fun import(xmlFile: String): ImportedPortfolio {
        val xStream = XStream()

        xStream.registerConverter(DateConverter())

        xStream.alias("account", Account::class.java)
        xStream.alias("account-transaction", AccountTransaction::class.java)
        xStream.alias("buysell", BuySell::class.java)
        xStream.alias("client", Client::class.java)
        xStream.alias("portfolio", Portfolio::class.java)
        xStream.alias("portfolio-transaction", PortfolioTransaction::class.java)
        xStream.alias("security", Security::class.java)

        xStream.ignoreUnknownElements()

        val client = xStream.fromXML(xmlFile) as Client
        return ImportedPortfolio(client)
    }

}
