package ar.edu.utn.frba.mobile.plantscare.services

import ar.edu.utn.frba.mobile.plantscare.model.WateringData
import ar.edu.utn.frba.mobile.plantscare.network.PlantsClient

class WateringViewModel(): APIViewModel<WateringData>() {
    init{
        launchViewModel {
            PlantsClient.watering.getWatering()
        }
    }
}