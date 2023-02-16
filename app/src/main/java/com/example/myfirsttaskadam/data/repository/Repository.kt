package com.example.myfirsttaskadam.data.repository

import com.example.myfirsttaskadam.data.model.people.PeopleModel
import com.example.myfirsttaskadam.data.model.room.RoomModel
import retrofit2.Response

interface Repository {

    suspend fun getPeople(): Response<PeopleModel>

    suspend fun getRoom(): Response<RoomModel>
}