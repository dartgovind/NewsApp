package com.example.anew.prasentation.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.anew.navigation.Routes

@Composable
fun Top(navHostController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(color = Color.Black)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(Modifier.height(40.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .padding(end = 20.dp)
                    .horizontalScroll(rememberScrollState()) // 👈 added for scroll
            ) {
                Text("My Feed", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Medium, modifier = Modifier.clickable { navHostController.navigate(
                    Routes.HomeScreen) })
                Spacer(Modifier.width(20.dp))
                Text("Finance", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Medium)
                Spacer(Modifier.width(20.dp))
                Text("Videos", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Medium, modifier = Modifier.clickable { navHostController.navigate(
                    Routes.Video) })
                Spacer(Modifier.width(20.dp))
                Text("Insights", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Medium)
                Spacer(Modifier.width(20.dp))
                Text("Good News", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Medium)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HomePreview1() {
//    Top() // ← Changed to show the actual Top composable
//}
