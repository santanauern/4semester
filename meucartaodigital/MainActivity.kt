
package com.example.meucartaodigital

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.meucartaodigital.data.ProjectRepository
import com.example.meucartaodigital.data.local.AppDatabase
import com.example.meucartaodigital.data.remote.GitHubApiService
import com.example.meucartaodigital.ui.ProjectsScreen
import com.example.meucartaodigital.ui.ProjectsViewModel
import com.example.meucartaodigital.ui.theme.MyAppTheme
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    private val githubUser = "SEU_USUARIO_GITHUB"

    private val viewModel: ProjectsViewModel by viewModels {
        ProjectsViewModelFactory(
            repository = provideRepository(),
            githubUser = githubUser
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyAppTheme {
                val uiState = viewModel.uiState.collectAsState()

                ProjectsScreen(
                    uiState = uiState.value,
                    onRefresh = { viewModel.refreshFromApi() }
                )
            }
        }
    }

    private fun provideRepository(): ProjectRepository {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(GitHubApiService::class.java)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "projects.db"
        ).fallbackToDestructiveMigration().build()

        return ProjectRepository(api, db.projectDao())
    }
}

class ProjectsViewModelFactory(
    private val repository: ProjectRepository,
    private val githubUser: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProjectsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProjectsViewModel(repository, githubUser) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
