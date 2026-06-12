package com.example.composecraft.presentation.features.disastercommandcenter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.Incident
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.IncidentType
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.KpiModel
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.Team
import com.example.composecraft.presentation.features.disastercommandcenter.presentation.usercases.CommandCenterUseCases
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CommandCenterUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val kpis: List<KpiModel> = emptyList(),
    val incidents: List<Incident> = emptyList(),
    val selectedIncidentType: IncidentType? = null,
    val teams: List<Team> = emptyList(),
    val selectedIncident: Incident? = null
)

class CommandCenterViewModel(private val useCases: CommandCenterUseCases) : ViewModel() {

    private val _state = MutableStateFlow(
        CommandCenterUiState(
            isLoading = true
        )
    )
    val state = _state.asStateFlow()

    init {
        loadDashboard()
    }

    private fun loadDashboard() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }
            try {
                val kpiDeferred = async {
                    useCases.getKpis()
                }
                val incidentDeferred = async {
                    useCases.getActiveIncidents()
                }
                val teamsDeferred = async {
                    useCases.getTeams()
                }
                _state.update {
                    it.copy(
                        kpis = kpiDeferred.await(),
                        incidents = incidentDeferred.await(),
                        teams = teamsDeferred.await(),
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message
                    )
                }
            }
        }
    }

    fun onIncidentTypeSelected(
        incidentType: IncidentType?
    ) {
        loadIncidents(incidentType)
    }

    private fun loadIncidents(incidentType: IncidentType?) {
        viewModelScope.launch {
            val filterIncidents =
                useCases.getActiveIncidents(
                    selectedType = incidentType
                )
            _state.update {
                it.copy(
                    selectedIncidentType = incidentType,
                    incidents = filterIncidents
                )
            }
        }
    }

    fun selectIncident(
        incident: Incident
    ) {
        _state.update {
            it.copy(
                selectedIncident = incident
            )
        }
    }

    fun clearSelectedIncident() {
        _state.update {
            it.copy(
                selectedIncident = null
            )
        }
    }

    fun refresh() {
        loadDashboard()
    }
}
