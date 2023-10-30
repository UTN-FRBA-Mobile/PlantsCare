package ar.edu.utn.frba.mobile.plantscare.ui.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.utn.frba.mobile.plantscare.model.ProfileData
import ar.edu.utn.frba.mobile.plantscare.network.PlantsCareApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface ProfileUiState {
  data class Success(val profileData: ProfileData) : ProfileUiState
  object Loading : ProfileUiState
  object Error : ProfileUiState
}

class ProfileViewModel : ViewModel() {
  var profileUiState: ProfileUiState by mutableStateOf(ProfileUiState.Loading)
//    private set

  init {
    getProfile()
  }

  private fun getProfile() {
    viewModelScope.launch {
      profileUiState = ProfileUiState.Loading
      profileUiState = try {
        val profileData = PlantsCareApi.profileService.getProfile()

        ProfileUiState.Success(profileData)
      } catch (e: IOException) {
        ProfileUiState.Error
      } catch (e: HttpException) {
        ProfileUiState.Error
      }
    }
  }
}

