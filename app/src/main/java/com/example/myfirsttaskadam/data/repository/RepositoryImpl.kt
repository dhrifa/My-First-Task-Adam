package com.example.myfirsttaskadam.data.repository

import com.example.myfirsttaskadam.data.model.people.PeopleModel
import com.example.myfirsttaskadam.data.model.room.RoomModel
import com.example.myfirsttaskadam.data.remote.ApiDetail
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val apiDetail: ApiDetail) : Repository {
    //val isLocalAvailable = true

    override suspend fun getPeople(): Response<PeopleModel> {
//        return if (isLocalAvailable) sendLocalDB() else apiDetail.getPeople()
        return apiDetail.getPeople()
    }

    override suspend fun getRoom(): Response<RoomModel> {
    return apiDetail.getRoom()
    }
}