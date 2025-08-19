package com.example.anew.prasentation.view.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.anew.R
import com.example.anew.data.model.Article
import com.example.anew.data.model.NewsDto
import com.example.anew.prasentation.viewmodel.NewsViewModel
import com.example.anew.util.Result
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(viewModel: NewsViewModel= viewModel(),navHostController: NavHostController){
    val context = LocalContext.current

    val newsState by viewModel.newsState.collectAsState()
    val articles = remember { mutableStateListOf<Article>() } // FIX: Store Article instead of NewsDto
    class Box<T>(val data: T)
    val boxedArticles = Box(articles)

    // Fetch news once at start

    LaunchedEffect(Unit) {
        viewModel.getNews()
    }

    LaunchedEffect(newsState) {
        if (newsState is Result.Success) {
            val result = (newsState as Result.Success<NewsDto>).data
            articles.clear()
            articles.addAll(result.articles)
        }
    }

    Scaffold(topBar = { Top(navHostController) }, bottomBar = { Bottom(navHostController) }){
        Surface(modifier = Modifier.fillMaxSize(), color = Color.Black){
            when(val state=newsState){
                is Result.Loading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is Result.Error -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Something went wrong!", color = Color.Red)
                    }
                }
                is Result.Success -> {
                    val articlesList = boxedArticles.data.take(articles.size)

                    // Use VerticalPager instead of LazyColumn
                    val pagerState = rememberPagerState()

                    VerticalPager(
                        count = articlesList.size,
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                            .background(Color.Black)
                    ) { page ->

                        val article = articlesList[page]

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color(0xFFEFF2FA), shape = RoundedCornerShape(20.dp))
                        ) {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                // Image
                                Image(
                                    painter = if (!article.urlToImage.isNullOrEmpty()) {
                                        rememberAsyncImagePainter(article.urlToImage)
                                    } else {
                                        painterResource(id = R.drawable.img)
                                    },
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(260.dp)
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                                    contentScale = ContentScale.FillBounds
                                )

                                // Three-dot icon
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(end = 15.dp),
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Spacer(Modifier.height(10.dp))
                                    Box(
                                        modifier = Modifier
                                            .size(30.dp)
                                            .background(color = Color(0x66000000), shape = CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.MoreHoriz,
                                            contentDescription = null,
                                            modifier = Modifier.size(20.dp),
                                            tint = Color.White
                                        )
                                    }
                                }

                                // Bottom Row (News tag + icons)
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    Spacer(Modifier.height(240.dp))
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 15.dp, top = 10.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(200.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .background(color = Color.White, shape = RoundedCornerShape(30.dp))
                                                .padding(horizontal = 14.dp, vertical = 7.dp)
                                        ) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Icon(
                                                    imageVector = Icons.Default.AppRegistration,
                                                    contentDescription = null,
                                                    modifier = Modifier.size(20.dp),
                                                    tint = Color.Red
                                                )
                                                Spacer(modifier = Modifier.width(6.dp))
                                                Text(
                                                    "News",
                                                    fontSize = 15.sp,
                                                    fontWeight = FontWeight.Medium,
                                                    color = Color.Black
                                                )
                                            }
                                        }

                                        Box(
                                            modifier = Modifier
                                                .background(color = Color.White, shape = RoundedCornerShape(30.dp))
                                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                                .shadow(
                                                    elevation = 10.dp,
                                                    shape = RoundedCornerShape(30.dp),
                                                    clip = false
                                                )
                                        ) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Icon(
                                                    imageVector = Icons.Default.Bookmark,
                                                    contentDescription = null,
                                                    modifier = Modifier.size(20.dp),
                                                    tint = Color.Red
                                                )
                                                Spacer(modifier = Modifier.width(6.dp))
                                                Icon(
                                                    imageVector = Icons.Default.Share,
                                                    contentDescription = "Share",
                                                    modifier = Modifier
                                                        .size(20.dp)
                                                        .clickable {
                                                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                                                type = "text/plain"
                                                                putExtra(Intent.EXTRA_SUBJECT, article.title)
                                                                putExtra(Intent.EXTRA_TEXT, article.url)
                                                            }
                                                            context.startActivity(Intent.createChooser(shareIntent, "Share via"))
                                                        },
                                                    tint = Color.Red
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                            Spacer(Modifier.height(10.dp))

                            // Article Content
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 20.dp)
                            ) {
                                article.title?.let { title ->
                                    Text(
                                        text = title,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                }
                                Spacer(Modifier.height(7.dp))
                                article.content?.let { content ->
                                    Text(
                                        text = content,
                                        fontSize = 18.sp,
                                        color = Color.Gray, modifier = Modifier.clickable {  val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                                            context.startActivity(intent)}
                                    )
                                }
                                Spacer(Modifier.height(10.dp))
                                Text(
                                    "few hours ago | Sakshita | ANI",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }

                else->{}
            }

        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HomePreview(){
//    HomeScreen(n:NewsViewModel=vew)
//}