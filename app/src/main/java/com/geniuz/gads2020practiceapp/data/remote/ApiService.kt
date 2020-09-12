package com.geniuz.gads2020practiceapp.data.remote

import com.geniuz.gads2020practiceapp.data.models.HoursRankingItem
import com.geniuz.gads2020practiceapp.data.models.HoursRankingResponse
import com.geniuz.gads2020practiceapp.data.models.SkillIqRankingResponse
import com.geniuz.gads2020practiceapp.data.models.SkillIqResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/api/hours")
    suspend fun hoursRanking() : Response<List<HoursRankingItem>>

    @GET("/api/skilliq")
    suspend fun skillIqRanking(): Response<List<SkillIqResponseItem>>
}