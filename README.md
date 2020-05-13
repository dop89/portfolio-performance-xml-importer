# portfolio-performance-xml-importer
An importer for Portfolio Performance XML files written in Kotlin

![Build library](https://github.com/dop89/portfolio-performance-xml-importer/workflows/Build%20library/badge.svg?branch=master)


## Motivation
[Portfolio Performance](https://github.com/buchen/portfolio) is a nice tool for managing a stocks portfolio. It keeps
its data stored in an XML file, which is not that easy to access. 

For my own projects, I needed a library to get this data, so I wrote this small (and limited) library. 


## Features
Currently, its feature set is quite limited. You can only import a Portfolio Performance XML file and get a summary
that aggregates all stocks over all portfolios together with name, isin, wkn and shares count. Serialized to JSON, 
a portfolio summary would look like this:

```json
{
   "items":[
      {
         "isin":"DE0006289473",
         "symbol":"EXHB.DE",
         "wkn":"628947",
         "name":"iShares eb.rexx Government Germany 1,5-2,5",
         "shares":315.0
      }
   ]
}
```

That feature was enough for me to release this first version. ;-)

## How to use?
### Dependency
Add the follow maven dependency to your project

```xml
<dependency>
  <groupId>com.github.dop89</groupId>
  <artifactId>portfolio-performance-xml-importer</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```
or use gradle

```kotlin
compile 'com.github.dop89:portfolio-performance-xml-importer:1.0-SNAPSHOT'
```
### Usage

```kotlin
fun parseXmlFile() {
    //val portfolioXmlAsString = ...
    
    val importedPortfolio = PortfolioPerformanceImporter().import(portfolioXmlAsString)
    val summary = importedPortfolio.getPortfolioSummary()
}
```
