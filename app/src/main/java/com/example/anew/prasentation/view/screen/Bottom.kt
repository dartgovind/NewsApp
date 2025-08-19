package com.example.anew.prasentation.view.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.anew.navigation.Routes

@Composable
fun Bottom(navHostController: NavHostController){
    Box(modifier = Modifier.fillMaxWidth().height(80.dp)){
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(imageVector = Icons.Default.Search, contentDescription = null, modifier = Modifier.size(35.dp), tint = Color.Gray)
                Spacer(Modifier.width(90.dp))
                Icon(imageVector = Icons.Default.Home, contentDescription = null, modifier = Modifier.size(35.dp).clickable { navHostController.navigate(
                    Routes.HomeScreen)},tint = Color.Gray)
                Spacer(Modifier.width(90.dp))
                Icon(imageVector = Icons.Default.Person, contentDescription = null, modifier = Modifier.size(35.dp).clickable { navHostController.navigate(
                    Routes.User) },tint = Color.Gray)

            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HomePreview3(){
//    HomeScreen()
//}