package ar.edu.utn.frba.mobile.plantscare.services

import ar.edu.utn.frba.mobile.plantscare.model.PlantInfo
import ar.edu.utn.frba.mobile.plantscare.network.PlantsClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class NewPlantViewModel: APIViewModel<PlantInfo>() {

    private var name: String = ""

    fun sendImage(imageFile: File) {
        if(name != imageFile.name) {
            this.name = imageFile.name
            val imagePart = MultipartBody.Part.createFormData(
                "file",
                imageFile.name,
                RequestBody.create("image/jpeg".toMediaTypeOrNull(), imageFile)
            )
            this.state = launchViewModel {
                PlantsClient.myPlant.createPlant(imagePart)
            }
        }
    }
}