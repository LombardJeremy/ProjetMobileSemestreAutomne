package com.grman.geofencing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.grman.geofencing.nav.AppNavigation
import com.grman.geofencing.ui.theme.GeoFencingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoFencingTheme {
                AppNavigation()
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainActivityPreview() {
    GeoFencingTheme {
        AppNavigation()
    }
}