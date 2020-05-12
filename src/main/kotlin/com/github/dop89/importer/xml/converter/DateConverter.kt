package com.github.dop89.importer.xml.converter

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter
import java.time.LocalDateTime

class DateConverter : AbstractSingleValueConverter() {
    override fun fromString(str: String?) = LocalDateTime.parse(str)
    override fun canConvert(type: Class<*>?) = type == LocalDateTime::class.java
}
