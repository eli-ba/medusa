package com.eliba.medusa.core.models

import com.eliba.medusa.core.options.RenderOptions

interface DatabaseObject {
    fun render(options: RenderOptions): String
}