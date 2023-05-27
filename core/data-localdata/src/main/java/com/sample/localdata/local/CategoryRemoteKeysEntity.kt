package com.sample.localdata.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sample.common_utils.utils.CategoryType


@Entity(tableName = "category_remote_keys_entity")
data class CategoryRemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val categoryType: CategoryType
)
