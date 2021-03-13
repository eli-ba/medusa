package com.eliba.medusa.models.columntype

class IntegerColumnType : ColumnType {
    override fun render(): String {
        return name()
    }

    override fun name(): String {
        return "integer"
    }
}