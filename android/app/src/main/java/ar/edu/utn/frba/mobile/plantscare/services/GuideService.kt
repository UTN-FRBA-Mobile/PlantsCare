package ar.edu.utn.frba.mobile.plantscare.services

import ar.edu.utn.frba.mobile.plantscare.model.Article
import retrofit2.http.GET
import retrofit2.http.Path

interface GuideService {
  @GET("guides/{id}")
  suspend fun getGuideById(@Path("id") id: Int): Article
}