
package com.example.meucartaodigital.data.remote

import com.example.meucartaodigital.data.local.ProjectEntity
import com.google.gson.annotations.SerializedName

data class GitHubRepoDto(
    val id: Long,
    val name: String,
    val description: String?,
    @SerializedName("html_url")
    val htmlUrl: String
) {
    fun toEntity(): ProjectEntity =
        ProjectEntity(
            id = id,
            name = name,
            description = description,
            htmlUrl = htmlUrl
        )
}
