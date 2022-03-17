package com.sample.domain.model

import java.io.Serializable
import java.util.*

data class Ads(
    val ads: List<Ad>
) : Serializable

data class Ad(
    val uid: String,
    val name: String,
    val price: String,
    val createdAt: Date?,
    val imageUrls: List<String>,
    val imageUrlsThumbnails: List<String>,
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Ad

        if (uid != other.uid) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uid.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        result = 31 * result + imageUrls.hashCode()
        result = 31 * result + imageUrlsThumbnails.hashCode()
        return result
    }
}

