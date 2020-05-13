# portfolio-performance-xml-importer
An importer for Portfolio Performance XML files written in Kotlin

## Motivation
[Portfolio Performance](https://github.com/buchen/portfolio) is a nice tool for managing a stocks portfolio. It keeps
its data stored in an XML file, which is not that easy to access. 

For my own projects, I needed a library to get this data, so I wrote this small (and limited) library. 

## Features
Currently, its feature set is quite limited. You can only import an Portfolio Performance XML file and get a summary
that aggregates all stocks over all portfolios together with name, isin, wkn and shares count. 

That feature was enough for me to release this first version. 

## How to use?

```kotlin
fun parseXmlFile() {
    //val portfolioXmlAsString = ...
    
    val importedPortfolio = PortfolioPerformanceImporter().import(portfolioXmlAsString)
    val summary = importedPortfolio.getPortfolioSummary()
}
```
