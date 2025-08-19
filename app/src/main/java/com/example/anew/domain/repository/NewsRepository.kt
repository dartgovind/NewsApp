package com.example.anew.domain.repository

import com.example.anew.data.model.NewsDto
interface NewsRepository {
    suspend fun getNews(): NewsDto
}