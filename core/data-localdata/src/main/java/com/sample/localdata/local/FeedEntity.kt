package com.sample.localdata.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "feed_entity")
data class FeedEntity(
    @PrimaryKey
    val feedNo: Int=0,
    val feedContents: String,
    val feedTitle: String,
    val imageUrl: String
) : Serializable

