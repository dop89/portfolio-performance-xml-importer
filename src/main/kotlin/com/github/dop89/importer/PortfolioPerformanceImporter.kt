package com.github.dop89.importer

import com.github.dop89.importer.xml.*
import com.github.dop89.importer.xml.converters.DateConverter
import com.thoughtworks.xstream.XStream


class PortfolioPerformanceImporter {

    fun importPortfolio(xmlFile: String): ImportedPortfolio =
        ImportedPortfolio(configureXStream().fromXML(xmlFile) as Client)

    private fun configureXStream(): XStream {
        val xStream = XStream()

        xStream.registerConverter(DateConverter())

        xStream.alias("account", Account::class.java)
        xStream.alias("account-transaction", AccountTransaction::class.java)
        xStream.alias("buysell", BuySell::class.java)
        xStream.alias("client", Client::class.java)

        xStream.alias("portfolio", Portfolio::class.java)
        xStream.alias("portfolio-transaction", PortfolioTransaction::class.java)
        xStream.alias("security", Security::class.java)

        // TODO fix me -> deserializing of dates not possible
        //xStream.useAttributeFor(DateRef::class.java, "date")

        xStream.alias("price", SecurityPrice::class.java)
        xStream.useAttributeFor(SecurityPrice::class.java, "date")
        xStream.aliasField("t", SecurityPrice::class.java, "date")
        xStream.useAttributeFor(SecurityPrice::class.java, "value")
        xStream.aliasField("v", SecurityPrice::class.java, "value")

        xStream.ignoreUnknownElements()
        xStream.allowTypeHierarchy(Client::class.java)

        return xStream
    }
}
