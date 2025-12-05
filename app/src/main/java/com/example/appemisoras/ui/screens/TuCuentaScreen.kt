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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appemisoras.ui.theme.AppEmisorasTheme

data class UserProfile(
    val firstName: String,
    val lastName: String,
    val school: String,
    val phone: String,
    val email: String
)

@Composable
fun TuCuentaScreen() {
    var userProfile by remember {
        mutableStateOf(
            UserProfile(
                firstName = "Juanito",
                lastName = "Trump",
                school = "I.E. Santo Domingo",
                phone = "3001234567",
                email = "juanito@email.com"
            )
        )
    }
    var isEditing by remember { mutableStateOf(false) }

    if (isEditing) {
        AccountEditScreen(
            userProfile = userProfile,
            onUpdateClick = { updatedProfile ->
                userProfile = updatedProfile
                isEditing = false
            },
            onBackClick = { isEditing = false }
        )
    } else {
        AccountDisplayScreen(
            userProfile = userProfile,
            onEditClick = { isEditing = true }
        )
    }
}

@Composable
fun AccountDisplayScreen(userProfile: UserProfile, onEditClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(43.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(60.dp))
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "${userProfile.firstName} ${userProfile.lastName}",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.W400
        )
        Text(
            text = userProfile.school,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.W400
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onEditClick,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(text = "Editar", color = Color.Black, fontSize = 16.sp)
            }
            Button(
                onClick = { /* TODO: Delete action */ },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text(text = "Eliminar", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun AccountEditScreen(
    userProfile: UserProfile,
    onUpdateClick: (UserProfile) -> Unit,
    onBackClick: () -> Unit
) {
    var nombre by remember { mutableStateOf(userProfile.firstName) }
    var apellido by remember { mutableStateOf(userProfile.lastName) }
    var celular by remember { mutableStateOf(userProfile.phone) }
    var correo by remember { mutableStateOf(userProfile.email) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 30.dp)
    ) {
        Spacer(modifier = Modifier.height(43.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(Color.DarkGray)
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.PhotoCamera,
                contentDescription = "Change Photo",
                tint = Color.LightGray,
                modifier = Modifier.size(60.dp)
            )
        }
        Spacer(modifier = Modifier.height(40.dp))

        // Form fields
        Text("Nombre", color = Color.White, fontSize = 16.sp)
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.DarkGray,
                unfocusedContainerColor = Color.DarkGray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Apellido", color = Color.White, fontSize = 16.sp)
        TextField(
            value = apellido,
            onValueChange = { apellido = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.DarkGray,
                unfocusedContainerColor = Color.DarkGray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Celular", color = Color.White, fontSize = 16.sp)
        TextField(
            value = celular,
            onValueChange = { celular = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.DarkGray,
                unfocusedContainerColor = Color.DarkGray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Correo", color = Color.White, fontSize = 16.sp)
        TextField(
            value = correo,
            onValueChange = { correo = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.DarkGray,
                unfocusedContainerColor = Color.DarkGray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                val updatedProfile = userProfile.copy(
                    firstName = nombre,
                    lastName = apellido,
                    phone = celular,
                    email = correo
                )
                onUpdateClick(updatedProfile)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Text("Actualizar", color = Color.Black, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(100.dp)) // To push the button up from the nav bar
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun AccountDisplayScreenPreview() {
    AppEmisorasTheme {
        AccountDisplayScreen(
            userProfile = UserProfile("Juanito", "Trump", "I.E. Santo Domingo", "300", "a@a.com"),
            onEditClick = {}
        )
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun AccountEditScreenPreview() {
    AppEmisorasTheme {
        AccountEditScreen(
             userProfile = UserProfile("Juanito", "Trump", "I.E. Santo Domingo", "300", "a@a.com"),
             onUpdateClick = {},
             onBackClick = {}
        )
    }
}
