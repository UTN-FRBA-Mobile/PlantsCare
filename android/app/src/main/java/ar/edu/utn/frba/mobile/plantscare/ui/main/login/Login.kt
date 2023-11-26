package ar.edu.utn.frba.mobile.plantscare.ui.main.login

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.ui.main.login.signIn.GoogleAuthUiClient
import ar.edu.utn.frba.mobile.plantscare.ui.theme.profileCardBackgroundColor
import ar.edu.utn.frba.mobile.plantscare.ui.theme.textColor
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun Login(navController: NavHostController, auth: GoogleAuthUiClient, context: Context) {
  val context = LocalContext.current

  val loginViewModel = viewModel<LoginScreenViewModel>()
  val launcher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.StartActivityForResult()
  ){
    val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
    try {
      val account = task.getResult(ApiException::class.java)
      val credential = GoogleAuthProvider.getCredential(account.idToken, null)
      loginViewModel.signInWithGoogleCredentials(credential) {
        navController.navigate("plants")
      }
    }catch (e: Exception){
      Log.d("PlantsCare", "GoogleSignIn Failed")
    }
  }

  Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White)
  ) {
    LoginContent(navController, context, launcher)
  }
}

@Composable
fun LoginButton(
  navController: NavHostController,
  context: Context,
  launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
) {
  Button(
    onClick = {
      val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("132857721549-4r77mbmppc0j43t4khr7fn7rkt0919j1.apps.googleusercontent.com")
        .requestEmail()
        .build()
      val googleSignInClient = GoogleSignIn.getClient(context, options)
      launcher.launch(googleSignInClient.signInIntent)

    },
    colors = ButtonDefaults.buttonColors(backgroundColor = profileCardBackgroundColor),
    contentPadding = PaddingValues(20.dp),
    shape = CircleShape
  ) {
    Row(
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Image(
        painter = painterResource(id = R.drawable.devicon_google),
        contentDescription = "Image",
        modifier = Modifier
          .height(18.dp)
          .width(18.dp)
      )
      Spacer(modifier = Modifier.width(10.dp))
      Text(
        text = "Sign in with google",
        fontSize = 20.sp, // Set the font size for the title
        fontWeight = FontWeight.Bold, // Set font weight for bold text
        color = textColor
      )
    }
  }
}

//@Preview
@Composable
fun LoginContent(
  navController: NavHostController,
  context: Context,
  launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      text = "Plants Care",
      fontSize = 50.sp, // Set the font size for the title
      fontWeight = FontWeight.Bold, // Set font weight for bold text
      color = Color.Black
    )
    Image(
      painter = painterResource(id = R.drawable.login_image),
      contentDescription = stringResource(id = R.string.login_image_content_description),
      modifier = Modifier
        .height(500.dp)
        .width(500.dp)
    )
    LoginButton(navController, context, launcher)
  }
}
