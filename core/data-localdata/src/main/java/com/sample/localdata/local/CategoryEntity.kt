package com.sample.localdata.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sample.domain.util.CategoryType
import java.io.Serializable

@Entity(tableName = "category_entity")
data class CategoryEntity(
    @PrimaryKey
    val itemNo: Int,
    val brandName: String,
    val heartCount: Int,
    val imageUrl: String,
    val itemName: String,
    val reviewAveragePoint: Double,
    val reviewCount : Int ,
    val isFavorite: Boolean,
    val categoryType: CategoryType

) : Serializable

