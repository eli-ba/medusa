package com.eliba.medusa.core.services

import com.eliba.medusa.core.models.DatabaseObject

interface Generator {
    fun generate(): List<DatabaseObject>
}