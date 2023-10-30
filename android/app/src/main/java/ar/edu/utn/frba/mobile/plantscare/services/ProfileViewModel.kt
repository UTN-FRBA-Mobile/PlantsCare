package ar.edu.utn.frba.mobile.plantscare.services

import ar.edu.utn.frba.mobile.plantscare.model.ProfileData
import ar.edu.utn.frba.mobile.plantscare.network.PlantsClient

class ProfileViewModel: APIViewModel<ProfileData>() {
  init {
    launchViewModel {
      PlantsClient.profile.getProfile()
    }
  }
}