package client.yalantis.com.githubclient.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Media(
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("marketId")
        val marketId: Int,
        @SerializedName("type")
        val type: Int,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("url")
        val url: String
) : Serializable