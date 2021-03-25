package com.eliba.medusa.red.models.columntype

import com.eliba.medusa.core.models.ColumnType
import com.eliba.medusa.core.options.RenderOptions

class IntegerColumnType : ColumnType {
    override fun render(options: RenderOptions): String {
        return name()
    }

    override fun name(): String {
        return "integer"
    }
}