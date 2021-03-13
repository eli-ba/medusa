package com.eliba.medusa.models.columntype

class FloatColumnType : ColumnType {
    override fun render(): String {
        return name()
    }

    override fun name(): String {
        return "float"
    }
}