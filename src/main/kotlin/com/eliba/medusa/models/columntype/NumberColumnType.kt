package com.eliba.medusa.models.columntype

class NumberColumnType : ColumnType {
    override fun render(): String {
        return name()
    }

    override fun name(): String {
        return "number"
    }
}