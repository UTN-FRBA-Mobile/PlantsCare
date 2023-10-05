package ar.edu.utn.frba.mobile.plantscare.ui.main.services

import ar.edu.utn.frba.mobile.plantscare.ui.main.model.PlantInfo

object PlantsService {
//    private val client = OkHttpClient()
    private val defaultPlant = PlantInfo(1,
    "Amapola",
    "https://png.pngtree.com/png-clipart/20210329/ourlarge/pngtree-happy-plant-png-image_3141339.jpg",
    "small",
    "interior",
    "high",
    "easy",
    2,
    "Pothos is an evergreen plant with thick, waxy, green, heart-shaped leaves with splashes of yellow",
    listOf("https://c8.alamy.com/compes/kyndht/cartoon-flor-feliz-en-una-olla-kyndht.jpg",
        "https://thumbs.dreamstime.com/z/l%C3%ADnea-fina-satisfecha-hoja-icono-de-la-planta-del-emoji-emoticon-sonriente-ramita-verde-kawaii-feliz-cara-contenta-155114164.jpg",
        "https://cdn.pixabay.com/photo/2022/01/08/11/06/plant-6923699_1280.png"),
    mapOf(Pair("January - March", 1),
        Pair("April - May" , 3),
        Pair("June - August", 5),
        Pair("September - October" , 4),
        Pair("November - December" , 2)));

    fun getPlantInfo(plantId: Int): PlantInfo {
//        val url = "https://mi-test.com/getPlant/$plantId"
//        val request = Request.Builder()
//            .url(url)
//            .build()
//        try {
//            val response = client.newCall(request).execute()
//            if (response.isSuccessful) {
//                val responseBody = response.body?.string()
//                val gson = Gson()
//                return gson.fromJson(responseBody, Plant::class.java)
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }

        return defaultPlant
    }
}