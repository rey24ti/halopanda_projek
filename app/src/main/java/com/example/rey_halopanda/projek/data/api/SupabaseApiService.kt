package com.example.rey_halopanda.projek.data.api

import com.example.rey_halopanda.projek.data.model.NoteModel
import com.example.rey_halopanda.projek.data.model.TaskModel
import com.example.rey_halopanda.projek.data.model.UserModel
import retrofit2.Response
import retrofit2.http.*

interface SupabaseApiService {

    // === REGISTER (USERS) ===
    @Headers("Content-Profile: mobile", "Prefer: return=representation")
    @POST("users")
    suspend fun registerUser(@Body user: UserModel): Response<List<UserModel>>

    // === CRUD 1: NOTES ===
    @Headers("Accept-Profile: mobile")
    @GET("notes")
    suspend fun getNotes(): List<NoteModel>

    @Headers("Content-Profile: mobile", "Prefer: return=representation")
    @POST("notes")
    suspend fun createNote(@Body note: NoteModel): Response<List<NoteModel>>

    @Headers("Content-Profile: mobile")
    @PATCH("notes")
    suspend fun updateNote(@Query("id") idFilter: String, @Body note: NoteModel): Response<Unit>

    @Headers("Content-Profile: mobile")
    @DELETE("notes")
    suspend fun deleteNote(@Query("id") idFilter: String): Response<Unit>

    // === CRUD 2: TASKS ===
    @Headers("Accept-Profile: mobile")
    @GET("tasks")
    suspend fun getTasks(): List<TaskModel>

    @Headers("Content-Profile: mobile", "Prefer: return=representation")
    @POST("tasks")
    suspend fun createTask(@Body task: TaskModel): Response<List<TaskModel>>

    @Headers("Content-Profile: mobile")
    @PATCH("tasks")
    suspend fun updateTask(@Query("id") idFilter: String, @Body task: TaskModel): Response<Unit>

    @Headers("Content-Profile: mobile")
    @DELETE("tasks")
    suspend fun deleteTask(@Query("id") idFilter: String): Response<Unit>
}