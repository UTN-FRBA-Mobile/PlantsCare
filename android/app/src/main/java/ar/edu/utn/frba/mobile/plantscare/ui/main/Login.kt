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
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.utn.frba.mobile.plantscare.R

@Composable
fun Login() {
  Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White)
  ) {
    LoginContent()
  }
}

@Composable
fun LoginButton() {
  Button(
    onClick = { "" },
    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
    contentPadding = PaddingValues(16.dp),
    shape = CircleShape,
    modifier = Modifier
      .height(30.dp)
      .width(120.dp),
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
          .height(10.dp)
          .width(10.dp)
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text(text = "Sing in with google")
    }
  }
}

@Preview
@Composable
fun LoginContent() {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(text = "Plants Care")
    Image(
      painter = painterResource(id = R.drawable.login_image),
      contentDescription = stringResource(id = R.string.login_image_content_description)
    )
    LoginButton()
  }
}
