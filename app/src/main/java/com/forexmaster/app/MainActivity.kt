package com.forexmaster.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.forexmaster.app.ui.theme.ForexMasterTheme
import com.forexmaster.app.navigation.ForexMasterNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ForexMasterTheme {
                ForexMasterNavHost()
            }
        }
    }
}
