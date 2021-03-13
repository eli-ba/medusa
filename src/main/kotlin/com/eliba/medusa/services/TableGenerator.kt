package com.eliba.medusa.services

import com.eliba.medusa.models.DatabaseObject
import com.eliba.medusa.models.columntype.*
import com.eliba.medusa.models.tabularobject.Column
import com.eliba.medusa.models.tabularobject.Table
import com.eliba.medusa.util.unique
import com.github.javafaker.Faker
import org.apache.commons.math3.random.RandomDataGenerator


class TableGenerator(private val count: Int) : Generator {
    override fun generate(): List<DatabaseObject> {
        val faker = Faker()
        val randomDataGenerator = RandomDataGenerator()
        val columnTypes: MutableList<ColumnType> = ArrayList()
        val varchar2TableColumnTypes: MutableList<Varchar2ColumnType> = ArrayList()
        for (i in 0..4) {
            varchar2TableColumnTypes.add(
                Varchar2ColumnType(
                    size = randomDataGenerator.nextInt(1, 4000),
                )
            )
        }
        columnTypes.addAll(varchar2TableColumnTypes)
        columnTypes.add(NumberColumnType())
        columnTypes.add(TimestampColumnType())
        columnTypes.add(ClobColumnType())
        columnTypes.add(DateColumnType())
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
                columnType = Varchar2ColumnType(size = 32),
                defaultValue = "sys_guid()",
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
                    defaultValue = "sysdate",
                    table = table
                )
            )
            table.columns.add(
                Column(
                    name = "update_time",
                    columnType = TimestampColumnType(),
                    defaultValue = "sysdate",
                    table = table,
                )
            )
            tables.add(table)
        }
        databaseObjects.addAll(tables)
        return databaseObjects
    }
}