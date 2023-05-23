package com.sample.localdata.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "image_remote_keys_entity")
data class ImageRemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
)
