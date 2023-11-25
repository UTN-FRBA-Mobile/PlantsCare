package ar.edu.utn.frba.mobile.plantscare.services

import ar.edu.utn.frba.mobile.plantscare.model.GuidesData
import retrofit2.http.GET

interface GuidesService {
    @GET("guides")
    suspend fun getGuides() : List<GuidesData>
}