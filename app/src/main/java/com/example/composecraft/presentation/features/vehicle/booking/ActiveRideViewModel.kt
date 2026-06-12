//package com.example.composecraft.presentation.features.vehicle.booking
//
//import androidx.lifecycle.SavedStateHandle
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.flow.update
//import kotlinx.coroutines.launch
//
//enum class RideStatus { DISPATCHED, ARRIVING, IN_PROGRESS, COMPLETED, CANCELLED }
//
//data class VehicleLocation(
//    val lat: Double,
//    val lng: Double,
//    val bearing: Float
//)
//data class ActiveRideUiState(
//    val rideId: String = "",
//    val origin: String = "",
//    val destination: String = "",
//    val etaMinutes: Int = 0,
//    val progressFraction: Float = 0f,
//    val vehicleLocation: VehicleLocation? = null,
//    val fare: Double = 0.0,
//    val distanceMiles: Double = 0.0,
//    val rideStatus: RideStatus = RideStatus.DISPATCHED,
//    val isCancelling: Boolean = false
//)
//
//sealed class ActiveRideEvent {
//    data class NavigateToRating(val rideId: String): ActiveRideEvent()
//    data class ShowError(val message: String): ActiveRideEvent()
//    object ShareEtaLink: ActiveRideEvent()
//}
//class ActiveRideViewModel(
//    savedStateHandle: SavedStateHandle,
//    private val rideRepository: RideRepository
//): ViewModel() {
//    private val rideId: String = savedStateHandle["rideId"] ?: error["rideId required"]
//
//    private val _uiState = MutableStateFlow(ActiveRideUiState())
//    val uiState: StateFlow<ActiveRideUiState> = _uiState
//
//    init {
//        observeRideUpdates()
//        observeVehicleLocation()
//    }
//
//    private fun observeRideUpdates() {
//        viewModelScope.launch {
//            rideRepository.rideUpdates(rideId).collect { update ->
//                _uiState.update {
//                    it.copy(
//                        etaMinutes = update.etaMinutes,
//                        progressFraction = update.progressFraction,
//                    )
//                }
//                if (update.status == RideStatus.COMPLETED) {
//                    _events.send()
//                }
//            }
//        }
//    }
//}