package com.eliba.medusa.models.constraint

import com.eliba.medusa.models.tabularobject.Column

class PrimaryKeyConstraint(
    val name: String,
    val column: Column,
) : Constraint {
    override fun render(): String {
        return "alter table " + column.table.name + " add constraint " + name + " primary key (" + column.name + ");"
    }
}