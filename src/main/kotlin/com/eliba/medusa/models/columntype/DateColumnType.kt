package com.eliba.medusa.models.columntype

class DateColumnType : ColumnType {
    override fun render(): String {
        return name()
    }

    override fun name(): String {
        return "date"
    }
}