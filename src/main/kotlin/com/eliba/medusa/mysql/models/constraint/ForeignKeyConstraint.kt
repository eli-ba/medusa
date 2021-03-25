package com.eliba.medusa.mysql.models.constraint

import com.eliba.medusa.core.models.Constraint
import com.eliba.medusa.core.options.RenderOptions
import com.eliba.medusa.mysql.models.tabularobject.Column

class ForeignKeyConstraint(
    val name: String,
    val column: Column,
    val foreignColumn: Column,
) : Constraint {
    override fun render(options: RenderOptions): String {
        return "alter table " + column.table.name + options.newLine() +
            options.indent() + "add constraint " + name + " foreign key (" + column.name + ") references " +
            foreignColumn.table.name + " (" + foreignColumn.name + ");"
    }

    override fun toString(): String {
        return name
    }
}