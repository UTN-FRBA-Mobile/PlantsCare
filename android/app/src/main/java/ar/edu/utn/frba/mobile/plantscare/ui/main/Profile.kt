package ar.edu.utn.frba.mobile.plantscare.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ar.edu.utn.frba.mobile.plantscare.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.ui.theme.colorPrimaryProfile
import ar.edu.utn.frba.mobile.plantscare.ui.theme.colorPrimaryProfile2
import ar.edu.utn.frba.mobile.plantscare.ui.theme.profileCardBackgroundColor
import ar.edu.utn.frba.mobile.plantscare.ui.theme.textColor

@Composable
fun Profile(navController: NavHostController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ProfileContent()
    }
}

@Preview
@Composable
private fun ProfileContent() {
    val text = "Hello, world!"
    val imageModifier = Modifier
        .size(50.dp)
        .background(color = colorPrimaryProfile2)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = "Profile")
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = stringResource(id = R.string.profile_image_content_description),
            modifier = imageModifier
        )
        Text(text = "Name: John Doe")
        Text(text = "Level: 25, (Experience Bar), Plants : 48")
        ProfileCard(text)
        ProfileCard(text)
        ProfileCard(text)
        ProfileCard(text)
    }
}

@Composable
private fun ProfileCard(text: String) {
    Card(
        backgroundColor = profileCardBackgroundColor,
        modifier = Modifier.size(width = 200.dp, height = 30.dp)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = textColor
        )
    }
}