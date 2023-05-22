package com.sample.domain.util

import com.google.gson.annotations.SerializedName

enum class CategoryType {

    @SerializedName("men")
    MEN,

    @SerializedName("women")
    WOMEN,

    @SerializedName("home")
    HOME,

    @SerializedName("tech")
    TECH


}
