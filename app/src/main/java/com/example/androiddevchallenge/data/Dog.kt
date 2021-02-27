package com.example.androiddevchallenge.data


data class Dog(
    val id:Int,
    // 0 : Male, 1 : Female
    val gender:Boolean,
    val name:String,
    val short_description:String,
    val description: String,
    val image_url: String,
    val breed: String,
    val age:Int,
)