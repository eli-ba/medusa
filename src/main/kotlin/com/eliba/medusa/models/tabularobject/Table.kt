package com.eliba.medusa.models.tabularobject

import com.eliba.medusa.models.constraint.ForeignKeyConstraint
import com.eliba.medusa.models.constraint.NotNullCheckConstraint
import com.eliba.medusa.models.constraint.PrimaryKeyConstraint
import java.util.*

class Table(
    val name: String,
    val primaryKeyConstraint: PrimaryKeyConstraint? = null,
    val columns: ArrayList<Column> = ArrayList(),
    val foreignKeyConstraints: ArrayList<ForeignKeyConstraint> = ArrayList(),
    val notNullCheckConstraints: ArrayList<NotNullCheckConstraint> = ArrayList(),
) : TabularObject {
    override fun render(): String {
        val buffer = StringBuilder()
        buffer.append("create table ").append(name).append("\n")
        buffer.append("(\n")
        for (column in columns) {
            buffer.append("  ").append(column.render()).append(",\n")
        }
        buffer.deleteCharAt(buffer.length - 2)
        buffer.append(");")
        return buffer.toString()
    }

    override fun name(): String {
        return name
    }
}