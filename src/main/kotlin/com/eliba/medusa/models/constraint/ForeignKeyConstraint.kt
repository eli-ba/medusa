package com.eliba.medusa.models.constraint

import com.eliba.medusa.models.tabularobject.Column

class ForeignKeyConstraint(
    val name: String,
    val column: Column,
    val foreignColumn: Column,
) : Constraint {
    override fun render(): String {
        return "alter table " + column.table.name + " add constraint " +
            name + " foreign key (" + column.name + ") references " +
            foreignColumn.table.name + " (" + foreignColumn.name + ");"
    }
}