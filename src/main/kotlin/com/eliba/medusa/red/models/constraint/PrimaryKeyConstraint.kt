package com.eliba.medusa.red.models.constraint

import com.eliba.medusa.core.models.Constraint
import com.eliba.medusa.core.options.RenderOptions
import com.eliba.medusa.red.models.tabularobject.Column

class PrimaryKeyConstraint(
    val name: String,
    val column: Column,
) : Constraint {
    override fun render(options: RenderOptions): String {
        return "alter table " + column.table.name + options.newLine() +
            options.indent() + "add constraint " + name + " primary key (" + column.name + ");"
    }

    override fun toString(): String {
        return name
    }
}