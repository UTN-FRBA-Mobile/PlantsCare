package ar.edu.utn.frba.mobile.plantscare.services

import ar.edu.utn.frba.mobile.plantscare.model.GuideData
import ar.edu.utn.frba.mobile.plantscare.network.PlantsClient

class GuidesViewModel(): APIViewModel<List<GuideData>>() {
    init{
        launchViewModel {
            PlantsClient.guides.getGuides()
        }
    }
}