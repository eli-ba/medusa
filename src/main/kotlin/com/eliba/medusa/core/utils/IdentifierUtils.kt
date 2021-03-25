package com.eliba.medusa.core.utils

private const val MAX_LENGTH = 30

fun sanitize(value: String): String {
    var sanitized = value.trim { it <= ' ' }.toLowerCase()
        .replace(" ", "_")
        .replace("[^a-zA-Z_]".toRegex(), "")
    sanitized = shorten(sanitized, 30)
    return sanitized
}

fun shorten(value: String, maxLength: Int): String {
    var value = value
    if (value.length > maxLength) {
        value = value.substring(0, maxLength)
        if (value.endsWith("_")) {
            value.replace("_$".toRegex(), "")
        }
    }
    return value
}

fun prefix(prefix: String, value: String): String {
    return prefix + shorten(value, MAX_LENGTH - prefix.length)
}

fun suffix(value: String, suffix: String): String {
    return shorten(value, MAX_LENGTH - suffix.length) + suffix
}

typealias NextValue = () -> String

fun unique(used: List<String>, nextValue: NextValue): String {
    while (true) {
        val name = sanitize(nextValue())
        if (used.stream().noneMatch { e: String -> e.equals(name, ignoreCase = true) }) {
            return name
        }
    }
}