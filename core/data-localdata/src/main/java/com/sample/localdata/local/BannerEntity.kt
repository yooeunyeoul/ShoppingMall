package com.sample.localdata.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "banner_entity")
data class BannerEntity(
    @PrimaryKey
    val bannerNo: Int,
    val imageUrl: String
) : Serializable

