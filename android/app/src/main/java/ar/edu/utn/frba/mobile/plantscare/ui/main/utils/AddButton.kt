package ar.edu.utn.frba.mobile.plantscare.ui.main.utils

import androidx.annotation.StringRes
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ar.edu.utn.frba.mobile.plantscare.ui.theme.LightGreen500Color
import ar.edu.utn.frba.mobile.plantscare.ui.theme.WhiteColor

@Composable
fun AddButton(onClick: () -> Unit, @StringRes textButton: Int){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = WhiteColor, contentColor = LightGreen500Color)
    ) {
        Icon(imageVector = Icons.Default.AddCircle, contentDescription = stringResource(textButton))
        Text(text =  stringResource(textButton))

    }
}

