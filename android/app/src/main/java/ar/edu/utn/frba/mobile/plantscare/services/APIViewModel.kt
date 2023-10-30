package ar.edu.utn.frba.mobile.plantscare.services

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.APICallState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

open class APIViewModel<G> : ViewModel() {
  var state: APICallState<G> by mutableStateOf(APICallState.Loading(null))

  fun launchViewModel(apiCall: suspend () -> G): APICallState<G> {
    viewModelScope.launch {
      state = APICallState.Loading(null)
      state = try {
        val profileData = apiCall()

        APICallState.Success(profileData)
      } catch (e: IOException) {
        APICallState.Error(e.message)
      } catch (e: HttpException) {
        APICallState.Error(e.message)
      }
      }
    return state
  }
}

/*
private fun getProfile() {
  launchViewModel{
    PlantsClient.profile.getProfile()
  }
}

 */