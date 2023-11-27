package ar.edu.utn.frba.mobile.plantscare.services

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ar.edu.utn.frba.mobile.plantscare.live.data.LocationLiveData

class ApplicationViewModel(application: Application): AndroidViewModel(application) {
  private val locationLiveData = LocationLiveData(application)
  fun getLocationLiveData() = locationLiveData
  fun startLocationUpdates() {
    locationLiveData.startLocationUpdates()
  }
}