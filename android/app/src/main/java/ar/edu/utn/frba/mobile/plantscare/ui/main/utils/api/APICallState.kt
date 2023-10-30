package ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api

sealed interface APICallState<G> {
  data class Success<G>(val data: G) : APICallState<G>
  data class Loading<G>(val data: String?) : APICallState<G>
  data class Error<G>(val data: String?) : APICallState<G>

}