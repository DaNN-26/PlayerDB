package com.example.playerdb

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Surface
import com.arkivanov.decompose.defaultComponentContext
import com.example.playerdb.components.root.RealRootComponent
import com.example.playerdb.components.root.RealRootComponent.Companion.ROOT_APP_KEY
import com.example.playerdb.firebase.auth.di.AuthModule
import com.example.playerdb.network.di.NetworkModule
import com.example.playerdb.ui.root.Root
import com.example.playerdb.ui.theme.PlayerDBTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val firebaseAuth = AuthModule.provideFirebase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val googleSignInRepository = AuthModule.provideGoogleSignInRepository(
            firebaseAuth = firebaseAuth,
            context = this
        )
        val sharedPreferences = this.getSharedPreferences(ROOT_APP_KEY, Context.MODE_PRIVATE)

        val googleSignInLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                googleSignInRepository.signInWithGoogle(result)
            }
        }

        val rootComponent = RealRootComponent(
            componentContext = defaultComponentContext(),
            ktorRepository = NetworkModule.providesRepository(
                NetworkModule.provideHttpClient()
            ),
            sharedPreferences = sharedPreferences,
            firebaseAuth = firebaseAuth,
            signInRepository = AuthModule.provideSignInRepository(
                firebaseAuth = firebaseAuth
            ),
            signUpRepository = AuthModule.provideSignUpRepository(
                firebaseAuth = firebaseAuth
            ),
            googleSignInRepository = googleSignInRepository,
            googleSignInLauncher = googleSignInLauncher
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