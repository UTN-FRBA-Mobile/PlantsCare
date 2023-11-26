package ar.edu.utn.frba.mobile.plantscare.services

import ar.edu.utn.frba.mobile.plantscare.model.GuidesData
import ar.edu.utn.frba.mobile.plantscare.model.WateringData
import ar.edu.utn.frba.mobile.plantscare.network.PlantsClient

class GuidesViewModel(): APIViewModel<List<GuidesData>>() {
    init{
        launchViewModel {
            PlantsClient.guides.getGuides()
        }
    }
}