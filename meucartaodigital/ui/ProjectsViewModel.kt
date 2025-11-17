
package com.example.meucartaodigital.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meucartaodigital.data.ProjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProjectsViewModel(
    private val repository: ProjectRepository,
    private val githubUser: String
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProjectUiState())
    val uiState: StateFlow<ProjectUiState> = _uiState.asStateFlow()

    init {
        observeProjects()
        refreshFromApi()
    }

    private fun observeProjects() {
        viewModelScope.launch {
            repository.getProjects().collectLatest { list ->
                _uiState.value = _uiState.value.copy(
                    projects = list,
                    isLoading = false,
                    errorMessage = null
                )
            }
        }
    }

    fun refreshFromApi() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                repository.refreshProjects(githubUser)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Erro ao carregar dados"
                )
            }
        }
    }
}
