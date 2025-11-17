
package com.example.meucartaodigital.ui

import com.example.meucartaodigital.data.local.ProjectEntity

data class ProjectUiState(
    val isLoading: Boolean = false,
    val projects: List<ProjectEntity> = emptyList(),
    val errorMessage: String? = null
)
