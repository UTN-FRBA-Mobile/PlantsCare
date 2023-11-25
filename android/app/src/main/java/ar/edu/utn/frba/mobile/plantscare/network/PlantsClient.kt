package ar.edu.utn.frba.mobile.plantscare.network

import ar.edu.utn.frba.mobile.plantscare.services.GuidesService
import ar.edu.utn.frba.mobile.plantscare.services.MyPlantService
import ar.edu.utn.frba.mobile.plantscare.services.ProfileService
import ar.edu.utn.frba.mobile.plantscare.services.WateringService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PlantsClient {
  private const val BASE_URL = "https://iivxz2d2gg.execute-api.us-east-1.amazonaws.com"

  val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  val profile : ProfileService by lazy {
    retrofit.create(ProfileService::class.java)
  }

  val myPlant: MyPlantService by lazy {
    retrofit.create(MyPlantService::class.java)
  }

  val watering: WateringService by lazy {
    retrofit.create(WateringService::class.java)
  }

  val guides: GuidesService by lazy {
    retrofit.create(GuidesService::class.java)
  }
}
