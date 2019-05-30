package client.yalantis.com.githubclient.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Home(
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("deviceId")
        val deviceId: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("marketId")
        val marketId: Int,
        @SerializedName("media")
        val media: Media,
        @SerializedName("mediaId")
        val mediaId: Int,
        @SerializedName("order")
        val order: Int,
        @SerializedName("orientation")
        val orientation: Int,
        @SerializedName("time")
        val time: Int,
        @SerializedName("updated_at")
        val updatedAt: String
) : Serializable