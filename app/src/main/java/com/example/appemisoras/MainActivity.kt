package com.example.appemisoras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.appemisoras.ui.theme.AppEmisorasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppEmisorasTheme {
                BroadcastersScreen()
            }
        }
    }
}

@Composable
fun BroadcastersScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(43.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Emisoras Recientes",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400,
                    letterSpacing = 2.sp
                )
                Text(
                    text = "Buscar parecidas",
                    color = Color(0xFFADADAD),
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
                        color = Color.White.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Colegio Calasanz",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W400,
                        letterSpacing = 1.5.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(39.dp))
            Text(
                text = "Sintonizar ahora",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.W400,
                letterSpacing = 3.sp,
                modifier = Modifier.padding(start = 22.dp)
            )
            Spacer(modifier = Modifier.height(17.dp))

            // First Station Card
            StationCard(
                logoUrl = "https://placehold.co/63x78",
                stationName = "I.E. Sagrado Corazón de Jesus",
                presenters = "Locutores",
                imageUrl = "https://placehold.co/365x146"
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Second Station Card
            StationCard(
                logoUrl = "https://placehold.co/53x85",
                stationName = "Escuela Normal Superior María Auxiliadora",
                presenters = "Locutores",
                imageUrl = "https://placehold.co/365x146"
            )
        }

        // Bottom Navigation Bar
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(65.dp)
                .background(Color.Black)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://placehold.co/51x51"),
                    contentDescription = "Inicio",
                    modifier = Modifier.size(51.dp)
                )
                Image(
                    painter = rememberAsyncImagePainter("https://placehold.co/51x51"),
                    contentDescription = "Buscar",
                    modifier = Modifier.size(51.dp)
                )
                Image(
                    painter = rememberAsyncImagePainter("https://placehold.co/91x51"),
                    contentDescription = "Biblioteca",
                    modifier = Modifier.size(width = 91.dp, height = 51.dp)
                )
                Image(
                    painter = rememberAsyncImagePainter("https://placehold.co/90x51"),
                    contentDescription = "Tu cuenta",
                    modifier = Modifier.size(width = 90.dp, height = 51.dp)
                )
            }
        }
    }
}


@Composable
fun StationCard(logoUrl: String, stationName: String, presenters: String, imageUrl: String) {
    Box(
        modifier = Modifier
            .padding(horizontal = 22.dp)
            .fillMaxWidth()
            .height(413.dp)
            .background(color = Color(0xFF353535), shape = RoundedCornerShape(20.dp))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(100.dp, 90.dp)
                        .background(Color.White, shape = RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(logoUrl),
                        contentDescription = null,
                        modifier = Modifier.size(63.dp, 78.dp)
                    )
                }
                Spacer(modifier = Modifier.width(14.dp))
                Column {
                    Text(
                        text = stationName,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                    Text(
                        text = presenters,
                        color = Color(0xFFADADAD),
                        fontSize = 16.sp,
                        letterSpacing = 1.6.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(146.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce eget nulla id sapien suscipit eleifend.",
                color = Color(0xFFADADAD),
                fontSize = 16.sp,
                letterSpacing = 1.6.sp,
                textAlign = TextAlign.Justify
            )

            // The play and chart icons are positioned absolutely in the html.
            // Replicating that exact layout with the given html is complex.
            // For now, I'''ll put them in a row.
             Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                 Text(
                    text = "+",
                    color = Color.White,
                    fontSize = 70.sp,
                    fontWeight = FontWeight.W400,
                    letterSpacing = 7.sp,
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = rememberAsyncImagePainter("https://placehold.co/44x44"),
                    contentDescription = "Play",
                    modifier = Modifier.size(44.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = rememberAsyncImagePainter("https://placehold.co/64x64"),
                    contentDescription = "Chart",
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
        BroadcastersScreen()
    }
}
