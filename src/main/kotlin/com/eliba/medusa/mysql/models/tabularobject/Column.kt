package com.eliba.medusa.mysql.models.tabularobject

import com.eliba.medusa.core.models.ColumnType
import com.eliba.medusa.core.models.DatabaseObject
import com.eliba.medusa.core.options.RenderOptions

data class Column(
    val name: String,
    val columnType: ColumnType,
    val notNull: Boolean = false,
    val table: Table,
    val defaultValue: String? = null,
    val foreignColumn: Column? = null,
) : DatabaseObject {
    override fun render(options: RenderOptions): String {
        var value = name + " " + columnType.render(options)
        if (notNull) {
            value += " not null"
        }
        if (defaultValue != null && defaultValue.isNotEmpty()) {
            value += " default $defaultValue"
        }
        return value
    }

    override fun toString(): String {
        return name
    }
}