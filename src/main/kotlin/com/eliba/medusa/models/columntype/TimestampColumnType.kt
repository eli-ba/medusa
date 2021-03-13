package com.eliba.medusa.models.columntype

class TimestampColumnType : ColumnType {
    override fun render(): String {
        return name()
    }

    override fun name(): String {
        return "timestamp"
    }
}