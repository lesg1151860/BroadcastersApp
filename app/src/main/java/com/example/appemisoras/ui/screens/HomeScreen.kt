package com.example.appemisoras.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun HomeScreen(navController: NavController, stationsViewModel: StationsViewModel = viewModel()) {
    val stations by stationsViewModel.stations.collectAsState()
    val isLoading by stationsViewModel.isLoading.collectAsState()
    val error by stationsViewModel.error.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.black))
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (error != null) {
            Text(text = error!!, color = Color.Red, modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 80.dp), // Padding for the bottom nav bar
                verticalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                item {
                    Column {
                        Spacer(modifier = Modifier.height(43.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 22.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(id = R.string.recent_stations),
                                color = colorResource(id = R.color.white),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W400,
                                letterSpacing = 2.sp
                            )
                            Text(
                                text = stringResource(id = R.string.search_similar),
                                color = colorResource(id = R.color.light_gray),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W400,
                                letterSpacing = 1.6.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(17.dp))
                        Box(
                            modifier = Modifier
                                .padding(start = 22.dp)
                                .width(100.dp)
                                .height(90.dp)
                                .background(
                                    color = colorResource(id = R.color.white).copy(alpha = 0.2f),
                                    shape = RoundedCornerShape(10.dp)
                                )
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = stringResource(id = R.string.calasanz_school),
                                    color = colorResource(id = R.color.white),
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.W400,
                                    letterSpacing = 1.5.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(39.dp))
                        Text(
                            text = stringResource(id = R.string.tune_in_now),
                            color = colorResource(id = R.color.white),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.W400,
                            letterSpacing = 3.sp,
                            modifier = Modifier.padding(start = 22.dp)
                        )
                    }
                }

                items(stations) { station ->
                    StationCard(station = station, navController = navController, stationsViewModel = stationsViewModel)
                }
            }
        }
    }
}


@Composable
fun StationCard(station: Station, navController: NavController, stationsViewModel: StationsViewModel) {
    Box(
        modifier = Modifier
            .padding(horizontal = 22.dp)
            .fillMaxWidth()
            .height(413.dp)
            .background(color = colorResource(id = R.color.dark_gray), shape = RoundedCornerShape(20.dp))
            .clickable { 
                stationsViewModel.selectStation(station)
                navController.navigate(AppScreens.Player.route) 
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(100.dp, 90.dp)
                        .background(colorResource(id = R.color.white), shape = RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(station.logoURL),
                        contentDescription = null,
                        modifier = Modifier.size(63.dp, 78.dp)
                    )
                }
                Spacer(modifier = Modifier.width(14.dp))
                Column {
                    Text(
                        text = station.name,
                        color = colorResource(id = R.color.white),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                    Text(
                        text = station.institution, // Using institution from Station
                        color = colorResource(id = R.color.light_gray),
                        fontSize = 16.sp,
                        letterSpacing = 1.6.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = rememberAsyncImagePainter(station.bannerURL),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(146.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = station.description,
                color = colorResource(id = R.color.light_gray),
                fontSize = 16.sp,
                letterSpacing = 1.6.sp,
                textAlign = TextAlign.Justify
            )

            // The play and chart icons are positioned absolutely in the html.
            // Replicating that exact layout with the given html is complex.
            // For now, I'll put them in a row.
             Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                 Text(
                    text = stringResource(id = R.string.icon_add),
                    color = colorResource(id = R.color.white),
                    fontSize = 70.sp,
                    fontWeight = FontWeight.W400,
                    letterSpacing = 7.sp,
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = rememberAsyncImagePainter("https://placehold.co/44x44"),
                    contentDescription = stringResource(id = R.string.cd_play),
                    modifier = Modifier
                        .size(44.dp)
                        .clickable { 
                            stationsViewModel.selectStation(station)
                            navController.navigate(AppScreens.Player.route) 
                        }
                )
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = rememberAsyncImagePainter("https://placehold.co/64x64"),
                    contentDescription = stringResource(id = R.string.cd_chart),
                    modifier = Modifier.size(64.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun DefaultPreview() {
    AppEmisorasTheme {
        // Since HomeScreen now needs a NavController and a ViewModel, we can't preview it directly.
    }
}
