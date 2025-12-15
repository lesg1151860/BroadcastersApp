package com.example.appemisoras.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.appemisoras.R
import com.example.appemisoras.data.Station
import com.example.appemisoras.ui.theme.AppEmisorasTheme

@Composable
fun SearchScreen(navController: NavController, stationsViewModel: StationsViewModel) {
    val stations by stationsViewModel.stations.collectAsState()
    val isLoading by stationsViewModel.isLoading.collectAsState()
    val error by stationsViewModel.error.collectAsState()

    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.black))
            .padding(horizontal = 22.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(43.dp))
            Text(
                text = stringResource(id = R.string.search_title),
                color = colorResource(id = R.color.white),
                fontSize = 30.sp,
                fontWeight = FontWeight.W400,
                letterSpacing = 3.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text(stringResource(id = R.string.search_placeholder), color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon", tint = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.dark_gray), shape = RoundedCornerShape(10.dp)),
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                )
            )
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = stringResource(id = R.string.found_title),
                color = colorResource(id = R.color.white),
                fontSize = 20.sp,
                fontWeight = FontWeight.W400,
                letterSpacing = 2.sp
            )
            Spacer(modifier = Modifier.height(17.dp))

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else if (error != null) {
                Text(text = error!!, color = Color.Red, modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(stations.filter { it.name.contains(searchQuery, ignoreCase = true) }) { station ->
                        StationSearchItem(station = station, navController = navController, stationsViewModel = stationsViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun StationSearchItem(station: Station, navController: NavController, stationsViewModel: StationsViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { // Navigate on row click
                stationsViewModel.selectStation(station)
                navController.navigate(AppScreens.Player.route)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = station.logoURL),
            contentDescription = station.name,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = station.name,
                color = colorResource(id = R.color.white),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = station.institution, // Using institution field
                color = colorResource(id = R.color.light_gray),
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        IconButton(
            onClick = { 
                stationsViewModel.selectStation(station)
                navController.navigate(AppScreens.Player.route) 
            },
            modifier = Modifier
                .size(48.dp)
                .background(Color.White, CircleShape)
        ) {
            Icon(
                Icons.Default.PlayArrow,
                contentDescription = stringResource(id = R.string.cd_play),
                tint = Color.Black,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                Icons.Default.Add,
                contentDescription = stringResource(id = R.string.cd_add_playlist),
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun SearchScreenPreview() {
    AppEmisorasTheme {
       // SearchScreen()
    }
}
