package com.eliba.medusa.mysql.services

import com.eliba.medusa.core.models.ColumnType
import com.eliba.medusa.core.models.DatabaseObject
import com.eliba.medusa.core.services.Generator
import com.eliba.medusa.core.utils.unique
import com.eliba.medusa.mysql.models.columntype.*
import com.eliba.medusa.mysql.models.tabularobject.Column
import com.eliba.medusa.mysql.models.tabularobject.Table
import com.github.javafaker.Faker
import org.apache.commons.math3.random.RandomDataGenerator


class TableGenerator(private val count: Int) : Generator {
    override fun generate(): List<DatabaseObject> {
        val faker = Faker()
        val randomDataGenerator = RandomDataGenerator()
        val columnTypes: MutableList<ColumnType> = ArrayList()
        val varcharTableColumnTypes: MutableList<VarcharColumnType> = ArrayList()
        for (i in 0..4) {
            varcharTableColumnTypes.add(
                VarcharColumnType(
                    size = randomDataGenerator.nextInt(1, 255),
                )
            )
        }
        columnTypes.addAll(varcharTableColumnTypes)
        columnTypes.add(TimestampColumnType())
        columnTypes.add(TextColumnType())
        columnTypes.add(DatetimeColumnType())
        columnTypes.add(DecimalColumnType())
        columnTypes.add(FloatColumnType())
        columnTypes.add(IntegerColumnType())
        columnTypes.add(NumericColumnType())
        columnTypes.add(SmallIntColumnType())
        val databaseObjects: MutableList<DatabaseObject> = ArrayList()
        val tables: MutableList<Table> = ArrayList()
        for (i in 0 until count) {
            val table = Table(
                name = unique(tables.map { it.name }) { faker.gameOfThrones().house() },
            )
            val id = Column(
                name = "id",
                columnType = VarcharColumnType(size = 36),
                notNull = true,
                table = table,
            )
            table.columns.add(id)
            for (j in 0 until randomDataGenerator.nextInt(3, 100)) {
                table.columns.add(
                    Column(
                        name = unique(table.columns.map { it.name }) { faker.gameOfThrones().character() },
                        columnType = columnTypes[randomDataGenerator.nextInt(0, columnTypes.size - 1)],
                        table = table,
                    )
                )
            }
            table.columns.add(
                Column(
                    name = "create_time",
                    columnType = TimestampColumnType(),
                    notNull = true,
                    defaultValue = "current_timestamp",
                    table = table
                )
            )
            table.columns.add(
                Column(
                    name = "update_time",
                    columnType = TimestampColumnType(),
                    table = table,
                )
            )
            tables.add(table)
        }
        databaseObjects.addAll(tables)
        return databaseObjects
    }
}