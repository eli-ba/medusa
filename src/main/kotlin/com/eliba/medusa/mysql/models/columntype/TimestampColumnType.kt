package com.eliba.medusa.mysql.models.columntype

import com.eliba.medusa.core.models.ColumnType
import com.eliba.medusa.core.options.RenderOptions

class TimestampColumnType : ColumnType {
    override fun render(options: RenderOptions): String {
        return name()
    }

    override fun name(): String {
        return "timestamp"
    }
}