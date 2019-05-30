package client.yalantis.com.githubclient.api

import client.yalantis.com.githubclient.model.response.HomeResponse
import retrofit2.http.GET
import rx.Observable


interface ApiService {

    @GET(ApiSettings.HOME)
    fun getHome(): Observable<HomeResponse>

}