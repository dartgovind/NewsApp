package com.example.anew

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.anew.data.repository.NewsRepositoryImpl
import com.example.anew.data.services.NewsApiService
import com.example.anew.navigation.Navigation
import com.example.anew.prasentation.view.screen.HomeScreen
import com.example.anew.prasentation.view.screen.VideoItem
import com.example.anew.prasentation.viewmodel.NewsViewModel
import com.example.anew.ui.theme.NewTheme

class MainActivity : ComponentActivity() {
    private val apiKey = "bae8e215f7894887a5827bb264ce7f93"
    private val newsApiService by lazy {
        NewsApiService(apiKey)
    }

    private val newsRepository by lazy {
        NewsRepositoryImpl(newsApiService)
    }

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return NewsViewModel(newsRepository) as T
                }
            }
        )[NewsViewModel::class.java]
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewTheme {
//                HomeScreen(viewModel)
               Navigation(viewModel)
            }
        }
    }
}


