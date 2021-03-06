package com.geniuz.gads2020practiceapp.data.remote

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SubmissionApiService {

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    fun submitForm(
        @Field("entry.1824927963")  email : String,
        @Field("entry.1877115667")  name: String,
        @Field("entry.2006916086")  lastName: String,
        @Field("entry.284483984")  link : String
    ) : Call<Void>
}