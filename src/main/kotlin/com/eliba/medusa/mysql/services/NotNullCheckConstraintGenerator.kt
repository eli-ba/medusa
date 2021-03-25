package com.eliba.medusa.mysql.services

import com.eliba.medusa.core.models.DatabaseObject
import com.eliba.medusa.core.services.Generator
import com.eliba.medusa.core.utils.suffix
import com.eliba.medusa.mysql.models.constraint.NotNullCheckConstraint
import com.eliba.medusa.mysql.models.tabularobject.Column
import com.eliba.medusa.mysql.models.tabularobject.Table
import org.apache.commons.math3.random.RandomDataGenerator

class NotNullCheckConstraintGenerator(private val tables: List<Table>) : Generator {
    override fun generate(): List<DatabaseObject> {
        val randomDataGenerator = RandomDataGenerator()
        val databaseObjects: MutableList<DatabaseObject> = ArrayList()
        val notNullCheckConstraints: MutableList<NotNullCheckConstraint> = ArrayList()
        for (table in tables) {
            val allExceptId: List<Column> = table.columns
                .filter { e -> e.name.toLowerCase() != "id" }
            val column = allExceptId[randomDataGenerator.nextInt(0, allExceptId.size - 1)]
            notNullCheckConstraints.add(
                NotNullCheckConstraint(
                    name = suffix(table.name, "_ck"),
                    column = column,
                )
            )
        }
        databaseObjects.addAll(notNullCheckConstraints)
        return databaseObjects
    }
}