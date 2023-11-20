package ar.edu.utn.frba.mobile.plantscare.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.model.Level
import ar.edu.utn.frba.mobile.plantscare.model.ProfileData
import ar.edu.utn.frba.mobile.plantscare.model.Settings
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.APICallState
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.loadScreen
import ar.edu.utn.frba.mobile.plantscare.ui.theme.colorPrimaryProfile2
import ar.edu.utn.frba.mobile.plantscare.ui.theme.profileCardBackgroundColor
import ar.edu.utn.frba.mobile.plantscare.ui.theme.textColor
import ar.edu.utn.frba.mobile.plantscare.ui.theme.textColorLight

@Composable
fun Profile(navController: NavHostController, profileUiState: APICallState<ProfileData>) {
  loadScreen(profileUiState) {
    ResultScreen(it)
  }
}

/**
 * ResultScreen displaying number of photos retrieved.
 */
@Composable
fun ResultScreen(profileData: ProfileData) {
  Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White)
      .padding(32.dp)
  ) {
    ProfileContent(profileData)
  }
}

@Composable
fun Header(name: String, experience: Int) {
  val imageModifier = Modifier.size(70.dp)

  Card(
    backgroundColor = colorPrimaryProfile2,
    modifier = Modifier
      .height(250.dp)
      .fillMaxWidth(),
    elevation = 2.dp,
    shape = RoundedCornerShape(20.dp)
  ) {
    Column(
      verticalArrangement = Arrangement.SpaceEvenly,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = "Name: $name",
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Medium,
        color = textColor
      )
      Image(
        painter = painterResource(id = R.drawable.profile_image),
        contentDescription = stringResource(id = R.string.profile_image_content_description),
        modifier = imageModifier
      )
      Text(
        text = name,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        color = textColor
      )
      Text(
        text = "Level: $experience, (Experience Bar), Plants : 48",
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Medium,
        color = textColor
      )
    }
  }
}

@Composable
fun Body(email: String, location: String, temperatureFormat: String) {
  ProfileBodyInformationCard("Email", email)
  ProfileBodyInformationCard("Location", location)
  ProfileBodyInformationCard("Temperature", temperatureFormat)
}

@Composable
fun Footer() {
  Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    modifier = Modifier.fillMaxWidth()
  ) {
    Button(
      onClick = { /*TODO*/ },
      colors = ButtonDefaults.buttonColors(backgroundColor = profileCardBackgroundColor),
      shape = RoundedCornerShape(20.dp)
    ) {
      Text(
        text = "Account",
        fontSize = 12.sp, // Set the font size for the title
        fontWeight = FontWeight.Bold, // Set font weight for bold text
        color = textColor
      )
    }

    Button(
      onClick = { /*TODO*/ },
      colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimaryProfile2),
      shape = RoundedCornerShape(20.dp)
    ) {
      Text(
        text = "Logout",
        fontSize = 12.sp, // Set the font size for the title
        fontWeight = FontWeight.Bold, // Set font weight for bold text
        color = textColor
      )
    }
  }
}

@Composable
private fun ProfileContent(profileData: ProfileData) {
  val text = "data"
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(20.dp)
  ) {
    Header(profileData.name, profileData.level.experience)
    Body(profileData.email, profileData.settings.location, profileData.settings.temperatureFormat)
    Footer()
//    ProfileCard(text)
  }
}

@Composable
private fun ProfileBodyInformationCard(tag: String, text: String) {
  Card(
    backgroundColor = profileCardBackgroundColor,
    modifier = Modifier
      .height(height = 50.dp)
      .fillMaxWidth(),
    elevation = 2.dp,
    shape = RoundedCornerShape(20.dp)
  ) {
    Row(
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.padding(24.dp, 0.dp)
    ) {
      Text(
        text = tag,
        textAlign = TextAlign.Center,
//        modifier = Modifier,
        fontWeight = FontWeight.SemiBold,
        color = textColor
      )
      Text(
        text = text,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Medium,
        color = textColorLight
      )
    }
  }
}


@Preview
@Composable
fun ResultScreenPreview() {
  val data: ProfileData = ProfileData(
    "John Doe",
    "jhon.doe@gmail.com",
    "asd",
    Level(4),
    Settings("Celsius", "Buenos Aires")
  )
  ResultScreen(data)
}

@Preview
@Composable
fun ProfilePreview() {
  ProfileBodyInformationCard("tag", text = "asdasdasd")
}