package com.eliba.medusa.red.models.columntype

import com.eliba.medusa.core.models.ColumnType
import com.eliba.medusa.core.options.RenderOptions

data class Varchar2ColumnType(
    val size: Int = 0,
) : ColumnType {
    override fun render(options: RenderOptions): String {
        return name() + "(" + size + ")"
    }

    override fun name(): String {
        return "varchar2"
    }
}