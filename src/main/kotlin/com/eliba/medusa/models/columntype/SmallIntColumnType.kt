package com.eliba.medusa.models.columntype

class SmallIntColumnType : ColumnType {
    override fun render(): String {
        return name()
    }

    override fun name(): String {
        return "smallint"
    }
}