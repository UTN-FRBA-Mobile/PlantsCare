package ar.edu.utn.frba.mobile.plantscare.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
  ) {
    ProfileContent(profileData)
  }
}

@Composable
fun Header(name: String, experience: Int) {
  val imageModifier = Modifier
    .size(70.dp)
    .background(color = colorPrimaryProfile2)

  Text(text = "Profile")
  Image(
    painter = painterResource(id = R.drawable.profile_image),
    contentDescription = stringResource(id = R.string.profile_image_content_description),
    modifier = imageModifier
  )
  Text(text = "Name: $name")
  Text(text = "Level: $experience, (Experience Bar), Plants : 48")
}

@Composable
fun Body(email: String, location: String, temperatureFormat: String) {
  ProfileBodyInformationCard("Email: $email")
  ProfileBodyInformationCard("Location: $location")
  ProfileBodyInformationCard("Temperature: $temperatureFormat")
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
//    ProfileCard(text)
  }
}

@Composable
private fun ProfileBodyInformationCard(text: String) {
  Card(
    backgroundColor = profileCardBackgroundColor,
    modifier = Modifier.size(width = 320.dp, height = 50.dp),
    elevation = 2.dp
  ) {
    Column(
      modifier = Modifier.padding(16.dp),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = text,
        textAlign = TextAlign.Center,
        color = textColor
      )
    }
  }
}

@Preview
@Composable
fun ResultScreenPreview() {
  val data: ProfileData = ProfileData(
    "asd",
    "asd",
    "asd",
    Level(4),
    Settings("asd", "asd",)
  )
  ResultScreen(data)
}

@Preview
@Composable
fun ProfilePreview() {
  ProfileBodyInformationCard(text = "asdasdasd")
}