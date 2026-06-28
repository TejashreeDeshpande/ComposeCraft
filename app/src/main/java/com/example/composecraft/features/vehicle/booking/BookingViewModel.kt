//package com.example.composecraft.presentation.features.vehicle.booking
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.update
//import kotlinx.coroutines.launch
//
//enum class RideType { WAYMO_ONE, WAYMO_PLUS, WAYMO_WAV }
//enum class BookingStatus { IDLE, LOADING, CONFIRMED, ERROR }
//
//data class RideOption(
//    val type: RideType,
//    val displayName: String,
//    val etaMinutes: Int,
//    val fare: Double,
//    val isAvailable: Boolean = true
//)
//
//data class BookingUiState(
//    val origin: String = "",
//    val destination: String = "",
//    val rideOptions: List<RideOption> = emptyList(),
//    val selectedType: RideType = RideType.WAYMO_ONE,
//    val status: BookingStatus = BookingStatus.IDLE,
//    val errorMessage: String? = null
//) {
//
//}
//class BookingViewModel : ViewModel() {
//
//    private val _uiState = MutableStateFlow(BookingUiState())
//    val uiState: StateFlow<BookingUiState> = _uiState
//
//    init {
//        loadRideOptions()
//    }
//    fun onOriginChanged(origin: String) {
//        _uiState.update { it.copy(origin = origin)}
//        fetchOptions()
//    }
//    fun onDestinationChanged(destination: String) {
//        _uiState.update { it.copy(destination = destination) }
//        fetchOptions()
//    }
//    fun onRideTypeSelected(type: RideType) {
//        _uiState.update { it.copy(selectedType = type) }
//    }
//    fun onBookRide() {
//        val state = _uiState.value
//        if (!state.canBook) return
//
//        viewModelScope.launch {
//            _uiState.update { it.copy(status = BookingStatus.LOADING) }
//            rideRepository.bookRide(
//                origin = state.origin,
//                destination = state.destination,
//                rideType = state.selectedType
//            ).onSuccess {
//                _uiState.update { it.copy(status = BookingStatus.CONFIRMED)}
//            }.onFailure {
//                _uiState.update{
//                    it.copy(
//                         status = BookingStatus.ERROR,
//                        errorMessage = error.message
//                    )
//                }
//            }
//        }
//    }
//    private fun fetchOptions() {
//        viewModelScope.launch {
//            val options = rideRepository.getAvailableRides(
//                origin = _uiState.value.origin,
//                destination = _uiState.value.destination
//            )
//            _uiState.update { it.copy(rideOptions = options)}
//        }
//    }
//
//    private fun loadRideOptions() = fetchOptions()
//}