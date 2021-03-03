package net.rafaeltoledo.sandbox.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StackOverflowApi {

  @GET("2.2/users?order=desc&sort=reputation&site=stackoverflow")
  suspend fun fetchUsers(@Query("page") page: Int = 1): Collection<User>

  @GET("2.2/users/{userId}?site=stackoverflow")
  suspend fun getUser(@Path("userId") userId: Long): Collection<User>
}
