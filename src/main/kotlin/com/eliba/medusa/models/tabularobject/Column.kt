package com.eliba.medusa.models.tabularobject

import com.eliba.medusa.models.DatabaseObject
import com.eliba.medusa.models.columntype.ColumnType

data class Column(
    val name: String,
    val columnType: ColumnType,
    val table: Table,
    val defaultValue: String? = null,
    val foreignColumn: Column? = null,
) : DatabaseObject {
    override fun render(): String {
        var value = name + " " + columnType.render()
        if (defaultValue != null && defaultValue.isNotEmpty()) {
            value += " default $defaultValue"
        }
        return value
    }
}