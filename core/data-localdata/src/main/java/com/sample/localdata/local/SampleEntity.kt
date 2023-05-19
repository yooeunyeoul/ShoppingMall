package com.sample.localdata.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "image_entity")
data class SampleEntity(
    @PrimaryKey
    val id: String = "",
    val imageUrl: String,
    val imageType: String,
    val isFavorite: Boolean
) : Serializable

