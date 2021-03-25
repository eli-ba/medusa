package com.eliba.medusa.mysql

import com.eliba.medusa.core.options.RenderOptions
import com.eliba.medusa.mysql.models.tabularobject.Table
import com.eliba.medusa.mysql.services.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("mysql")
class MySqlController {

    @GetMapping(produces = ["text/plain"])
    fun generate(
        @RequestParam("tableCount", defaultValue = "5", required = false) tableCount: Int,
        @RequestParam("foreignKeyMaxCount", defaultValue = "3", required = false) foreignKeyMaxCount: Int,
        @RequestParam("viewCount", defaultValue = "5", required = false) viewCount: Int,
        @RequestParam("materializedViewCount", defaultValue = "5", required = false) materializedViewCount: Int,
        @RequestParam("indentSize", defaultValue = "4", required = false) indentSize: Int,
        @RequestParam("continuationIndentSize", defaultValue = "8", required = false) continuationIndentSize: Int,
    ): String {
        val renderOptions = RenderOptions(
            indentSize = indentSize,
            continuationIndentSize = continuationIndentSize,
        )

        val sql = StringBuilder()

        val tables = TableGenerator(tableCount).generate()
        val primaryKeyConstraints = PrimaryKeyConstraintGenerator(tables as List<Table>).generate()
        val nullCheckConstraints = NotNullCheckConstraintGenerator(tables).generate()
        val foreignKeyConstraints = ForeignKeyConstraintGenerator(tables, foreignKeyMaxCount).generate()
        val views = ViewGenerator(viewCount, tables).generate()

        tables.forEachIndexed { index, element ->
            sql.append(element.render(renderOptions))
            if (index != tables.size - 1) {
                sql.append(renderOptions.newLine().repeat(2))
            }
        }
        sql.append(renderOptions.newLine().repeat(2))

        primaryKeyConstraints.forEachIndexed { index, element ->
            sql.append(element.render(renderOptions))
            if (index != primaryKeyConstraints.size - 1) {
                sql.append(renderOptions.newLine().repeat(2))
            }
        }
        sql.append(renderOptions.newLine().repeat(2))

        nullCheckConstraints.forEachIndexed { index, element ->
            sql.append(element.render(renderOptions))
            if (index != nullCheckConstraints.size - 1) {
                sql.append(renderOptions.newLine().repeat(2))
            }
        }
        sql.append(renderOptions.newLine().repeat(2))

        foreignKeyConstraints.forEachIndexed { index, element ->
            sql.append(element.render(renderOptions))
            if (index != foreignKeyConstraints.size - 1) {
                sql.append(renderOptions.newLine().repeat(2))
            }
        }
        sql.append(renderOptions.newLine().repeat(2))

        views.forEachIndexed { index, element ->
            sql.append(element.render(renderOptions))
            if (index != views.size - 1) {
                sql.append(renderOptions.newLine().repeat(2))
            }
        }

        sql.append(renderOptions.newLine())

        return sql.toString()
    }
}