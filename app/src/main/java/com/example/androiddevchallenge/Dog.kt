package com.example.androiddevchallenge

import kotlin.random.Random

data class Dog(val name: String, val avatar: Int, val desc: String)

object DogPool {
    val Dogs = listOf(
        Dog(getRandomString(6), getRandomAvatar(), getRandomString(100)),
        Dog(getRandomString(6), getRandomAvatar(), getRandomString(100)),
        Dog(getRandomString(6), getRandomAvatar(), getRandomString(100)),
        Dog(getRandomString(6), getRandomAvatar(), getRandomString(100)),
        Dog(getRandomString(6), getRandomAvatar(), getRandomString(100)),
        Dog(getRandomString(6), getRandomAvatar(), getRandomString(100)),
    )
}

fun getRandomString(length: Int): String {
    val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz"
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}

val avatars = listOf<Int>(
    R.drawable.dog_one,
    R.drawable.dog_two,
    R.drawable.dog_three,
    R.drawable.dog_four,
    R.drawable.dog_five,
    R.drawable.dog_six
)

fun getRandomAvatar(): Int {
    return avatars[Random.nextInt(0, 5)]
}