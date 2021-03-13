package com.eliba.medusa

import com.eliba.medusa.models.tabularobject.Table
import com.eliba.medusa.services.*
import org.apache.commons.io.FileUtils
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption
import java.io.File
import java.io.IOException

@ShellComponent
class Commands {
    @ShellMethod(key = ["generate"], value = "Generate Medusa SQL")
    fun generate(
        @ShellOption(value = ["--outputFile"], defaultValue = "./medusa.sql", help = "Output file") outputFile: String?,
        @ShellOption(value = ["--tableCount"], defaultValue = "5", help = "Table count") tableCount: Int?,
        @ShellOption(value = ["--foreignKeyMaxCount"], defaultValue = "3", help = "Maximum number of foreign keys") foreignKeyMaxCount: Int?,
        @ShellOption(value = ["--viewCount"], defaultValue = "5", help = "View count") viewCount: Int?,
        @ShellOption(value = ["--materializedViewCount"], defaultValue = "5", help = "Materialized view count") materializedViewCount: Int?
    ) {
        val sql = StringBuilder()

        val tables = TableGenerator(tableCount!!).generate()
        tables.forEach { sql.append(it.render()).append("\n\n") }

        PrimaryKeyConstraintGenerator(tables as List<Table>)
            .generate()
            .forEach { sql.append(it.render()).append("\n") }
        sql.append("\n")

        NotNullCheckConstraintGenerator(tables)
            .generate()
            .forEach { sql.append(it.render()).append("\n") }
        sql.append("\n")

        ForeignKeyConstraintGenerator(tables, foreignKeyMaxCount!!)
            .generate()
            .forEach { sql.append(it.render()).append("\n") }
        sql.append("\n")

        ViewGenerator(viewCount!!, tables)
            .generate()
            .forEach { sql.append(it.render()).append("\n\n") }

        MaterializedViewGenerator(materializedViewCount!!, tables)
            .generate()
            .forEach { sql.append(it.render()).append("\n\n") }

        if (outputFile != null && outputFile.isNotBlank()) {
            try {
                FileUtils.writeStringToFile(File(outputFile), sql.toString(), "UTF-8")
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            println(sql.toString())
        }
    }
}