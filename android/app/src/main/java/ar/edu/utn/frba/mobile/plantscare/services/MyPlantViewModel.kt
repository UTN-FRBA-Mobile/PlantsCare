package ar.edu.utn.frba.mobile.plantscare.services

import android.util.Log
import ar.edu.utn.frba.mobile.plantscare.model.PlantInfo
import ar.edu.utn.frba.mobile.plantscare.network.PlantsClient

class MyPlantViewModel: APIViewModel<PlantInfo>() {

    private var id: Int = -1
    fun setId(id: Int) {
        if (id != this.id) {
            this.id = id
            Log.i("id al hacer la pegada", "$id")
            this.state = launchViewModel {
                PlantsClient.myPlant.getPlantById(id)
            }
        }
    }
}