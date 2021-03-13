package com.eliba.medusa.services

import com.eliba.medusa.models.DatabaseObject

interface Generator {
    fun generate(): List<DatabaseObject>
}