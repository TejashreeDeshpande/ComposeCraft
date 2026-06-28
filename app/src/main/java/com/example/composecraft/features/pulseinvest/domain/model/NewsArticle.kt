package com.example.composecraft.features.pulseinvest.domain.model

data class NewsArticle(
    val id: String,
    val title: String,
    val source: String,
    val publishedAt: String,
    val isFeatured: Boolean = false,
    val relatedSymbol: String? = null,
    val portfolioImpact: String? = null,
    val emoji: String = "📰"
)
