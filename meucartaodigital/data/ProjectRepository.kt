
package com.example.meucartaodigital.data

import com.example.meucartaodigital.data.local.ProjectDao
import com.example.meucartaodigital.data.local.ProjectEntity
import com.example.meucartaodigital.data.remote.GitHubApiService
import kotlinx.coroutines.flow.Flow

class ProjectRepository(
    private val api: GitHubApiService,
    private val dao: ProjectDao
) {

    fun getProjects(): Flow<List<ProjectEntity>> = dao.getAllProjects()

    suspend fun refreshProjects(user: String) {
        val remoteRepos = api.getUserRepos(user)
        val entities = remoteRepos.map { it.toEntity() }

        dao.clearAll()
        dao.insertAll(entities)
    }
}
