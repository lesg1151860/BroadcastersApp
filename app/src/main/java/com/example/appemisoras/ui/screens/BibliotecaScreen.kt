package com.example.appemisoras.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appemisoras.R
import com.example.appemisoras.data.Station

@Composable
fun BibliotecaScreen(navController: NavController) {
    val favoriteStations = listOf(
        Station(stationNameRes = R.string.station_colegio_x, presentersRes = R.string.presenters_placeholder, logoUrl = "", imageUrl = "", descriptionRes = 0),
        Station(stationNameRes = R.string.station_colegio_x, presentersRes = R.string.presenters_placeholder, logoUrl = "", imageUrl = "", descriptionRes = 0),
        Station(stationNameRes = R.string.station_colegio_x, presentersRes = R.string.presenters_placeholder, logoUrl = "", imageUrl = "", descriptionRes = 0),
        Station(stationNameRes = R.string.station_colegio_x, presentersRes = R.string.presenters_placeholder, logoUrl = "", imageUrl = "", descriptionRes = 0),
        Station(stationNameRes = R.string.station_colegio_x, presentersRes = R.string.presenters_placeholder, logoUrl = "", imageUrl = "", descriptionRes = 0),
        Station(stationNameRes = R.string.station_colegio_x, presentersRes = R.string.presenters_placeholder, logoUrl = "", imageUrl = "", descriptionRes = 0),
        Station(stationNameRes = R.string.station_colegio_x, presentersRes = R.string.presenters_placeholder, logoUrl = "", imageUrl = "", descriptionRes = 0),
        Station(stationNameRes = R.string.station_colegio_x, presentersRes = R.string.presenters_placeholder, logoUrl = "", imageUrl = "", descriptionRes = 0)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 22.dp)
    ) {
        Spacer(modifier = Modifier.height(43.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.favorite_stations_title),
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.W400,
                letterSpacing = 3.sp
            )
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.cd_search),
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.History,
                contentDescription = stringResource(R.string.cd_recents),
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = R.string.recents_title),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.W400,
                letterSpacing = 2.sp
            )
        }
        Spacer(modifier = Modifier.height(17.dp))
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(favoriteStations) { station ->
                FavoriteStationItem(station = station, navController = navController)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(
            onClick = { /* TODO */ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(id = R.string.add_more),
                color = Color.White,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun FavoriteStationItem(station: Station, navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(station.stationNameRes),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "------------",
                color = Color.LightGray,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        IconButton(
            onClick = { navController.navigate(AppScreens.Player.route) },
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
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun BibliotecaScreenPreview() {
    // AppEmisorasTheme {
    //     BibliotecaScreen()
    // }
}
