package com.eliba.medusa.red.services

import com.eliba.medusa.core.models.DatabaseObject
import com.eliba.medusa.core.services.Generator
import com.eliba.medusa.core.utils.prefix
import com.eliba.medusa.red.models.tabularobject.MaterializedView
import com.eliba.medusa.red.models.tabularobject.Table

class MaterializedViewGenerator(
    private val count: Int,
    private val tables: List<Table>,
) : Generator {
    override fun generate(): List<DatabaseObject> {
        val chosenTables: List<Table> = tables
            .sortedBy { e -> e.foreignKeyConstraints.size }
            .filter { e -> e.foreignKeyConstraints.size > 1 }
            .take(count)
            .reversed()
        val databaseObjects: MutableList<DatabaseObject> = ArrayList()
        for (table in chosenTables) {
            databaseObjects.add(
                MaterializedView(
                    name = prefix("mv_", table.name),
                    columns = table.columns.filter { it.foreignColumn != null }
                )
            )
        }
        return databaseObjects
    }
}