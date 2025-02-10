package net.rafaeltoledo.sandbox.ui.screen.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import net.rafaeltoledo.sandbox.data.StackOverflowApi
import net.rafaeltoledo.sandbox.data.User
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

class RepositoriesViewModel : ViewModel() {

  private val api: StackOverflowApi = initApi()

  private val _state = MutableStateFlow(RepositoriesState(isLoading = true))
  val state: StateFlow<RepositoriesState>
    get() = _state

  init {
    viewModelScope.launch {
      val users = api.fetchUsers()
      _state.value = RepositoriesState(users = users.items)
    }
  }

  private fun initApi(): StackOverflowApi {
    val client = OkHttpClient.Builder()
      .addInterceptor(
        HttpLoggingInterceptor()
          .setLevel(HttpLoggingInterceptor.Level.BODY),
      )
      .build()

    val json = Json {
      ignoreUnknownKeys = true
      explicitNulls = false
    }

    val retrofit = Retrofit.Builder()
      .client(client)
      .baseUrl("https://api.stackexchange.com")
      .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
      .build()

    return retrofit.create()
  }
}

data class RepositoriesState(
  val isLoading: Boolean = false,
  val users: List<User> = emptyList(),
)
