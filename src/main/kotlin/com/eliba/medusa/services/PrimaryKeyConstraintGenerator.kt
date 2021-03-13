package com.eliba.medusa.services

import com.eliba.medusa.models.DatabaseObject
import com.eliba.medusa.models.constraint.PrimaryKeyConstraint
import com.eliba.medusa.models.tabularobject.Table
import com.eliba.medusa.util.shorten

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