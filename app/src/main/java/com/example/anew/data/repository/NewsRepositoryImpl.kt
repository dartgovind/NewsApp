package com.example.anew.data.repository

import com.example.anew.data.model.NewsDto
import com.example.anew.data.services.NewsApiService
import com.example.anew.domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val newsApiService: NewsApiService
):NewsRepository {
    override suspend fun getNews(): NewsDto {
        return newsApiService.getNews()
    }
}