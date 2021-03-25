package com.eliba.medusa.core.models

interface ColumnType : DatabaseObject {
    fun name(): String
}