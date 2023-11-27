package ar.edu.utn.frba.mobile.plantscare.services

import android.util.Log
import ar.edu.utn.frba.mobile.plantscare.model.GuideData
import ar.edu.utn.frba.mobile.plantscare.network.PlantsClient

class GuideViewModel: APIViewModel<GuideData>() {
  private var id: Int = -1
  fun setId(id: Int) {
    if (id != this.id) {
      this.id = id
      Log.i("id al hacer la pegada", "$id")
      this.state = launchViewModel {
        PlantsClient.guides.getGuideById(id)
      }
    }
  }
}