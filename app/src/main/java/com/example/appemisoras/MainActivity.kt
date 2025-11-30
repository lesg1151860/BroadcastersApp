package com.example.appemisoras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.appemisoras.ui.screens.AppNavigation
import com.example.appemisoras.ui.theme.AppEmisorasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppEmisorasTheme {
                AppNavigation()
            }
        }
    }
}
