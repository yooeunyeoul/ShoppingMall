package com.sample.network.util

sealed class CategoryType(val name:String) {
    object MEN:CategoryType("men")
    object WOMEN:CategoryType("women")
    object HOME:CategoryType("home")
    object TECH:CategoryType("tech")
}