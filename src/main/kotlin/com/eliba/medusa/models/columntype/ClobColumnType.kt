package com.eliba.medusa.models.columntype

class ClobColumnType : ColumnType {
    override fun render(): String {
        return name()
    }

    override fun name(): String {
        return "clob"
    }
}