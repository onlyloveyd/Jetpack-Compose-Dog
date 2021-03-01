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

import android.app.Activity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {

    var selectedDog by remember { mutableStateOf<Dog?>(null) }

    val context = LocalContext.current
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current
    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (selectedDog != null) {
                    selectedDog = null
                } else {
                    (context as? Activity)?.finish()
                }
            }
        }
    }

    DisposableEffect(key1 = backDispatcher) {
        backDispatcher.onBackPressedDispatcher.addCallback(backCallback)
        onDispose {
            backCallback.remove()
        }
    }

    Surface(color = MaterialTheme.colors.background) {
        selectedDog?.let { localDog ->
            DogDetails(dog = localDog)
        } ?: run {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Spacer(modifier = Modifier.size(16.dp))
                DogPool.Dogs.forEach {
                    Box(Modifier.padding(8.dp)) {
                        DogView(
                            dog = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedDog = it }
                        )
                    }
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Composable
fun DarkPreview(function: () -> Unit) {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}

@Composable
fun DogView(dog: Dog, modifier: Modifier = Modifier) {
    Card(modifier, shape = RoundedCornerShape(26.dp)) {
        Box {
            Image(
                painterResource(dog.avatar),
                null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                MaterialTheme.colors.onSurface
                            )
                        )
                    )
                    .fillMaxWidth()
                    .padding(26.dp)
            ) {
                Text(
                    text = dog.name,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.surface
                )
            }
        }
    }
}
