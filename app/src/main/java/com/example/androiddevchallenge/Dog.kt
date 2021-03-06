/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

val avatars = listOf(
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

fun getRandomString(length: Int): String {
    val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz"
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}
