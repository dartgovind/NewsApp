package com.example.anew.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsDto(
    val articles: List<Article>,
    val status: String?,
    val totalResults: Int?
)