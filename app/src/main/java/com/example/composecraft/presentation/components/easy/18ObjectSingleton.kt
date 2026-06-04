package com.example.composecraft.presentation.components.easy

import java.net.URI

object AppConfig {
    private const val BASE_URL = "https://example.com"

    fun buildUrl(path: String): String {
        return URI.create(BASE_URL).resolve(path).toString()
    }
}