package com.eliba.medusa.red.services

import com.eliba.medusa.core.models.DatabaseObject
import com.eliba.medusa.core.services.Generator
import com.eliba.medusa.core.utils.suffix
import com.eliba.medusa.red.models.constraint.ForeignKeyConstraint
import com.eliba.medusa.red.models.tabularobject.Column
import com.eliba.medusa.red.models.tabularobject.Table
import org.apache.commons.math3.random.RandomDataGenerator

class ForeignKeyConstraintGenerator(
    private val tables: List<Table>,
    private val foreignKeyMaxCount: Int,
) : Generator {
    override fun generate(): List<DatabaseObject> {
        val randomDataGenerator = RandomDataGenerator()
        val databaseObjects: ArrayList<DatabaseObject> = ArrayList()
        val foreignKeyConstraints: ArrayList<ForeignKeyConstraint> = ArrayList()
        for (table in tables) {
            val usedForeignTables: ArrayList<Table> = ArrayList()
            val foreignKeyCount = randomDataGenerator.nextInt(1, foreignKeyMaxCount)
            for (i in 0 until foreignKeyCount) {
                val usableTables: List<Table> = tables
                    .filter { e -> e.foreignKeyConstraints.none { x -> x.foreignColumn.table == table } }
                    .filter { e -> e != table }
                    .filter { e -> usedForeignTables.none { x -> x == e } }
                if (usableTables.isEmpty()) {
                    continue
                }
                val foreignTable: Table = usableTables[randomDataGenerator.nextInt(0, usableTables.size - 1)]
                val foreignTableColumn = foreignTable.columns.firstOrNull { e -> e.name.toLowerCase() == "id" } ?: continue
                usedForeignTables.add(foreignTable)
                val column = Column(
                    name = suffix(foreignTable.name, "_id"),
                    foreignColumn = foreignTableColumn,
                    columnType = foreignTableColumn.columnType,
                    table = table
                )
                table.columns.add(column)
                val constraint = ForeignKeyConstraint(
                    name = suffix(table.name, "_fk$i"),
                    foreignColumn = foreignTableColumn,
                    column = column,
                )
                foreignKeyConstraints.add(constraint)
                table.foreignKeyConstraints.add(constraint)
            }
        }
        databaseObjects.addAll(foreignKeyConstraints)
        return databaseObjects
    }

}