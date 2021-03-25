package com.eliba.medusa.core.options

class RenderOptions(
    private val indentSize: Int,
    private val continuationIndentSize: Int,
) {
    fun indent(): String {
        return " ".repeat(indentSize)
    }

    fun continuationIndent(): String {
        return " ".repeat(continuationIndentSize)
    }

    fun newLine(): String {
        return "\r\n"
    }
}