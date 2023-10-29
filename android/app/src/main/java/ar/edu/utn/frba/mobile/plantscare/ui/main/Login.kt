package ar.edu.utn.frba.mobile.plantscare.ui.main

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.ui.theme.profileCardBackgroundColor
import ar.edu.utn.frba.mobile.plantscare.ui.theme.textColor

@Composable
fun Login(navController: NavHostController) {
  Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White)
  ) {
    LoginContent(navController)
  }
}

@Composable
fun LoginButton(navController: NavHostController) {
  Button(
    onClick = { navController.navigate("plants")},
    colors = ButtonDefaults.buttonColors(backgroundColor = profileCardBackgroundColor),
    contentPadding = PaddingValues(20.dp),
    shape = CircleShape,
//    modifier = Modifier
//      .height(30.dp)
//      .width(120.dp),
  ) {
    Row(
//      modifier = Modifier.fillMaxWidth(),
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
        text = "Sing in with google",
        fontSize = 20.sp, // Set the font size for the title
        fontWeight = FontWeight.Bold, // Set font weight for bold text
        color = textColor
      )
    }
  }
}

//@Preview
@Composable
fun LoginContent(navController: NavHostController) {
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
    LoginButton(navController)
  }
}
