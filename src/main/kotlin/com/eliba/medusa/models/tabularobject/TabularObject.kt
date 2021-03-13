package com.eliba.medusa.models.tabularobject

import com.eliba.medusa.models.DatabaseObject

interface TabularObject : DatabaseObject {
    fun name(): String
}