package com.eliba.medusa.models.columntype

class NumericColumnType : ColumnType {
    override fun render(): String {
        return name()
    }

    override fun name(): String {
        return "numeric"
    }
}