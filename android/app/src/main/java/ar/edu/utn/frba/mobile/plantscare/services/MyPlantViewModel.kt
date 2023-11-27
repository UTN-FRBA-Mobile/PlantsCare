package ar.edu.utn.frba.mobile.plantscare.services

import android.util.Log
import ar.edu.utn.frba.mobile.plantscare.model.PlantInfo

class MyPlantViewModel: APIViewModel<PlantInfo>() {

    private var id: Int = -1
    fun setId(id: Int, client: suspend (id: Int) -> PlantInfo) {
        if (id != this.id) {
            this.id = id
            Log.i("id al hacer la pegada", "$id")
            this.state = launchViewModel {
                client(id)
            }
        }
    }
}