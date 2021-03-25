package com.eliba.medusa.mysql.services

import com.eliba.medusa.core.models.DatabaseObject
import com.eliba.medusa.core.services.Generator
import com.eliba.medusa.core.utils.shorten
import com.eliba.medusa.mysql.models.constraint.PrimaryKeyConstraint
import com.eliba.medusa.mysql.models.tabularobject.Table

class PrimaryKeyConstraintGenerator(private val tables: List<Table>) : Generator {
    override fun generate(): List<DatabaseObject> {
        val databaseObjects: MutableList<DatabaseObject> = ArrayList()
        val primaryKeyConstraints: MutableList<PrimaryKeyConstraint> = ArrayList()
        for (table in tables) {
            val id = table.columns.firstOrNull { it.name.toLowerCase() == "id" } ?: continue
            primaryKeyConstraints.add(
                PrimaryKeyConstraint(
                    name = shorten(table.name, 27) + "_pk",
                    column = id,
                )
            )
        }
        databaseObjects.addAll(primaryKeyConstraints)
        return databaseObjects
    }
}