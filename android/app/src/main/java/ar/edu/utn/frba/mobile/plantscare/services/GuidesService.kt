package ar.edu.utn.frba.mobile.plantscare.services

import ar.edu.utn.frba.mobile.plantscare.model.GuideData
import retrofit2.http.GET
import retrofit2.http.Path

interface GuidesService {
    @GET("guides")
    suspend fun getGuides() : List<GuideData>

    @GET("guides/{id}")
    suspend fun getGuideById(@Path("id") id: Int): GuideData
}