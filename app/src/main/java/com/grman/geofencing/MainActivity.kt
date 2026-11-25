package com.grman.geofencing

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grman.geofencing.nav.AppRoot
import com.grman.geofencing.ui.theme.GeoFencingTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.grman.geofencing.data.zone.Zone
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from


// Supabase
val supabase = createSupabaseClient(
    supabaseUrl = BuildConfig.SUPABASE_URL,
    supabaseKey = BuildConfig.SUPABASE_PUBLISHABLE_KEY
) {
    install(Postgrest)
    install(Auth)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoFencingTheme {
                AppRoot()
                //ZonesListTest();
            }
        }
    }
}

@Composable
fun ZonesListTest() {
    var zones by remember { mutableStateOf<List<Zone>>(listOf()) }
    var error by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            try {
                zones = supabase.from("zones")
                    .select().decodeList<Zone>()
            } catch (e: Exception) {
                error = e.message
            }
        }
    }
    if (error != null) {
        Text(
            "Error loading zones: $error",
            modifier = Modifier.padding(8.dp),
        )
        return
    }
    LazyColumn {
        items(
            zones,
            key = { zone -> zone.id },
        ) { zone ->
            Text(
                zone.name,
                modifier = Modifier.padding(8.dp),
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainActivityPreview() {
    GeoFencingTheme {
        AppRoot()
    }
}