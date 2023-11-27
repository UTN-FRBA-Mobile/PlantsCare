package ar.edu.utn.frba.mobile.plantscare.ui.main.login.signIn

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.ui.theme.profileCardBackgroundColor
import ar.edu.utn.frba.mobile.plantscare.ui.theme.textColor

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }
/*
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = onSignInClick) {
            Text(text = "Sign in")
        }
    }
*/

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LoginContent(onSignInClick)
    }
}

@Composable
fun LoginContent(onSignInClick: () -> Unit) {
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
        LoginButton(onSignInClick)
    }
}

@Composable
fun LoginButton(
    onSignInClick: () -> Unit
) {
    Button(
        onClick = onSignInClick,
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