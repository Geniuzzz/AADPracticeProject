package com.geniuz.gads2020practiceapp.data.remote

import com.geniuz.gads2020practiceapp.data.models.HoursRankingResponse
import com.geniuz.gads2020practiceapp.data.models.SkillIqRankingResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/api/hours")
    suspend fun hoursRanking() : Response<HoursRankingResponse>

    @GET("/api/skilliq")
    suspend fun skillIqRanking(): Response<SkillIqRankingResponse>
}