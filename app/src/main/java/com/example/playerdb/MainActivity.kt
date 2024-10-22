package com.example.playerdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.arkivanov.decompose.defaultComponentContext
import com.example.playerdb.components.root.RealRootComponent
import com.example.playerdb.network.di.NetworkModule
import com.example.playerdb.ui.root.Root
import com.example.playerdb.ui.theme.PlayerDBTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val rootComponent = RealRootComponent(
            defaultComponentContext(),
            ktorRepository = NetworkModule.providesRepository(
                NetworkModule.provideHttpClient()
            )
        )

        setContent {
            PlayerDBTheme {
                Surface {
                    Root(component = rootComponent)
                }
            }
        }
    }
}