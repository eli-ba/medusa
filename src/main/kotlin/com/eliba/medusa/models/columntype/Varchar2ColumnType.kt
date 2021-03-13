package com.eliba.medusa.models.columntype

data class Varchar2ColumnType(
    val size: Int = 0,
) : ColumnType {
    override fun render(): String {
        return name() + "(" + size + ")"
    }

    override fun name(): String {
        return "varchar2"
    }
}