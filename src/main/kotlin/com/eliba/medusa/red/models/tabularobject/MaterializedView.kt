package com.eliba.medusa.red.models.tabularobject

import com.eliba.medusa.core.models.TabularObject
import com.eliba.medusa.core.options.RenderOptions
import java.util.*

class MaterializedView(
    val name: String,
    val columns: List<Column> = ArrayList(),
) : TabularObject {
    override fun render(options: RenderOptions): String {
        val primaryTable = columns.stream().map(Column::table).findFirst().get()
        val buffer = StringBuilder()
        buffer.append("create materialized view ")
            .append(name)
            .append(" as").append(options.newLine()).append("select ")
        for ((index, column) in columns.withIndex()) {
            when (index) {
                0 -> {
                    buffer.append(column.table.name).append(".").append(column.name).append(",").append(options.newLine())
                }
                columns.size - 1 -> {
                    buffer.append(" ".repeat("select".length + 1)).append(column.table.name)
                        .append(".").append(column.name).append(options.newLine())
                }
                else -> {
                    buffer.append(" ".repeat("select".length + 1)).append(column.table.name)
                        .append(".").append(column.name).append(",").append(options.newLine())
                }
            }
        }
        buffer.append("from ").append(primaryTable.name).append(options.newLine())
        for ((index, column) in columns.withIndex()) {
            if (column.foreignColumn!!.table == primaryTable) {
                continue
            }
            buffer.append(options.continuationIndent()).append(" left join ")
                .append(column.foreignColumn.table.name)
                .append(" on ")
                .append(column.table.name).append(".").append(column.name)
                .append(" = ")
                .append(column.foreignColumn.table.name).append(".").append(column.foreignColumn.name)
            if (index != columns.size - 1) {
                buffer.append(options.newLine())
            }
        }
        buffer.append(";")
        return buffer.toString()
    }

    override fun name(): String {
        return name
    }

    override fun toString(): String {
        return name
    }
}