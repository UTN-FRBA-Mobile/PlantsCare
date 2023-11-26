package ar.edu.utn.frba.mobile.plantscare

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import ar.edu.utn.frba.mobile.plantscare.ui.main.login.signIn.GoogleAuthUiClient
import ar.edu.utn.frba.mobile.plantscare.ui.main.navigation.AppScaffold
import com.google.android.gms.auth.api.identity.Identity

class MainActivity : ComponentActivity() {

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(googleAuthUiClient, applicationContext)
        }
    }
}


@Composable
private fun App(googleAuthUiClient: GoogleAuthUiClient, applicationContext: Context) {
    val navController = rememberNavController()
    AppScaffold(navController = navController, auth = googleAuthUiClient, context = applicationContext)
}

