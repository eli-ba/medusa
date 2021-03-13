package com.eliba.medusa.models.columntype

import com.eliba.medusa.models.DatabaseObject

interface ColumnType : DatabaseObject {
    fun name(): String
}