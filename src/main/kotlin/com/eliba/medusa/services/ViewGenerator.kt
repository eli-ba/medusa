package com.eliba.medusa.services

import com.eliba.medusa.models.DatabaseObject
import com.eliba.medusa.models.tabularobject.Table
import com.eliba.medusa.models.tabularobject.View
import com.eliba.medusa.util.prefix

class ViewGenerator(private val count: Int, private val tables: List<Table>) : Generator {
    override fun generate(): List<DatabaseObject> {
        val chosenTables: List<Table> = tables
            .sortedBy { it.foreignKeyConstraints.size }
            .filter { it.foreignKeyConstraints.size > 1 }
            .takeLast(count)
            .reversed()
        val databaseObjects: MutableList<DatabaseObject> = ArrayList()
        for (table in chosenTables) {
            databaseObjects.add(
                View(
                    name = prefix("v_", table.name),
                    columns = table.columns.filter { it.foreignColumn != null }
                )
            )
        }
        return databaseObjects
    }
}