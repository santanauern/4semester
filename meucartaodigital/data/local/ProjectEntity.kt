
package com.example.meucartaodigital.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String?,
    val htmlUrl: String
)
