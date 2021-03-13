package com.eliba.medusa.models.tabularobject

import java.util.*

class MaterializedView(
    val name: String,
    val columns: List<Column> = ArrayList(),
) : TabularObject {
    override fun render(): String {
        val primaryTable = columns.stream().map(Column::table).findFirst().get()
        val buffer = StringBuilder()
        buffer.append("create materialized view ")
            .append(name)
            .append(" as\n").append("select ")
        for ((name1, _, table) in columns) {
            buffer.append(table.name).append(".").append(name1).append(", ")
        }
        buffer.delete(buffer.length - 2, buffer.length)
        buffer.append("\nfrom ").append(primaryTable.name).append("\n")
        for ((name1, _, table, _, foreignColumn) in columns) {
            if (foreignColumn!!.table == primaryTable) {
                continue
            }
            buffer.append("left join ")
                .append(foreignColumn.table.name)
                .append(" on ")
                .append(table.name).append(".").append(name1)
                .append(" = ")
                .append(foreignColumn.table.name).append(".").append(foreignColumn.name)
                .append("\n")
        }
        buffer.deleteCharAt(buffer.length - 1)
        buffer.append(";")
        return buffer.toString()
    }

    override fun name(): String {
        return name
    }
}