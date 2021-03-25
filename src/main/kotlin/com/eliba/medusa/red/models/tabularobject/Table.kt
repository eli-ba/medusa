package com.eliba.medusa.red.models.tabularobject

import com.eliba.medusa.core.models.TabularObject
import com.eliba.medusa.core.options.RenderOptions
import com.eliba.medusa.red.models.constraint.ForeignKeyConstraint
import com.eliba.medusa.red.models.constraint.NotNullCheckConstraint
import com.eliba.medusa.red.models.constraint.PrimaryKeyConstraint
import java.util.*

class Table(
    val name: String,
    val primaryKeyConstraint: PrimaryKeyConstraint? = null,
    val columns: ArrayList<Column> = ArrayList(),
    val foreignKeyConstraints: ArrayList<ForeignKeyConstraint> = ArrayList(),
    val notNullCheckConstraints: ArrayList<NotNullCheckConstraint> = ArrayList(),
) : TabularObject {
    override fun render(options: RenderOptions): String {
        val buffer = StringBuilder()
        buffer.append("create table ").append(name).append(options.newLine())
        buffer.append("(").append(options.newLine())
        for ((index, column) in columns.withIndex()) {
            if (index == columns.size - 1) {
                buffer.append(options.indent()).append(column.render(options)).append(options.newLine())
            } else {
                buffer.append(options.indent()).append(column.render(options)).append(",").append(options.newLine())
            }
        }
        buffer.append(");")
        return buffer.toString()
    }

    override fun name(): String {
        return name
    }

    override fun toString(): String {
        return name
    }
}