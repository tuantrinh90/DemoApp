package client.yalantis.com.githubclient.model.response

import client.yalantis.com.githubclient.model.Home
import com.google.gson.annotations.SerializedName

data class HomeResponse(
        @SerializedName("count")
        val count: Int,
        @SerializedName("homes")
        val homes: ArrayList<Home>
)