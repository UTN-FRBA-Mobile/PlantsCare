package ar.edu.utn.frba.mobile.plantscare.services

import android.util.Log
import ar.edu.utn.frba.mobile.plantscare.model.Article
import ar.edu.utn.frba.mobile.plantscare.network.PlantsClient

class GuideViewModel: APIViewModel<Article>() {
  private var id: Int = -1
  fun setId(id: Int) {
    if (id != this.id) {
      this.id = id
      Log.i("id al hacer la pegada", "$id")
      this.state = launchViewModel {
        PlantsClient.guide.getGuideById(id)
      }
    }
  }
}