package ar.edu.utn.frba.mobile.plantscare.services

import ar.edu.utn.frba.mobile.plantscare.model.PlantInfo
import ar.edu.utn.frba.mobile.plantscare.network.PlantsClient

class MyPlantViewModel(): APIViewModel<PlantInfo>() {
    init{
        launchViewModel {
            PlantsClient.myPlant.getPlantById(1)
        }
    }
}