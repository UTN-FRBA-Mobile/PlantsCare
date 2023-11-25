package ar.edu.utn.frba.mobile.plantscare.services

import ar.edu.utn.frba.mobile.plantscare.model.PlantInfo
import ar.edu.utn.frba.mobile.plantscare.network.PlantsClient

class MyPlantsViewModel: APIViewModel<List<PlantInfo>>() {
  init {
    launchViewModel {
      PlantsClient.myPlant.getPlants()
    }
  }
}