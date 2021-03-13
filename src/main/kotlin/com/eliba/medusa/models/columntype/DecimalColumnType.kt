package com.eliba.medusa.models.columntype

class DecimalColumnType : ColumnType {
    override fun render(): String {
        return name()
    }

    override fun name(): String {
        return "decimal"
    }
}