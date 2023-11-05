package ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api

import androidx.compose.runtime.Composable

@Composable
fun <G> loadScreen(state: APICallState<G>, successScreen:  @Composable (data: G) -> Unit) {
  when (state) {
    is APICallState.Loading -> LoadingScreen()
    is APICallState.Success<G> -> successScreen(state.data)
    is APICallState.Error -> ErrorScreen()
  }
}