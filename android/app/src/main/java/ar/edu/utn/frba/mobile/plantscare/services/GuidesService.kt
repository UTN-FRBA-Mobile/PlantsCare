package ar.edu.utn.frba.mobile.plantscare.services

import ar.edu.utn.frba.mobile.plantscare.model.Article
import ar.edu.utn.frba.mobile.plantscare.model.GuidesData
import retrofit2.http.GET
import retrofit2.http.Path

interface GuidesService {
    @GET("guides")
    suspend fun getGuides() : List<GuidesData>

    @GET("guides/{id}")
    suspend fun getGuideById(@Path("id") id: Int): Article
}