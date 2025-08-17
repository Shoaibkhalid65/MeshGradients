package com.example.meshgradients.practice

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import com.example.meshgradients.meshGradient
import com.example.meshgradients.ui.theme.Blue400
import com.example.meshgradients.ui.theme.Indigo200
import com.example.meshgradients.ui.theme.Purple500
import com.example.meshgradients.ui.theme.Rose700

@Composable
@Preview(showBackground = true)
fun Sample3(){
    val pointTranslate = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        while (true) {
            pointTranslate.animateTo(.3f, tween(3000))
            pointTranslate.animateTo(-.3f, tween(3000))
        }

    }
    val points by remember {
        derivedStateOf {
            listOf(
                listOf(
                    Offset(0f, 0f) to Rose700,
                    Offset(.33f, 0f) to Rose700,
                    Offset(.66f, 0f) to Rose700,
                    Offset(1f, 0f) to Rose700,
                ),

                listOf(
                    Offset(0f, .33f) to Purple500,
                    Offset(.33f, .33f - pointTranslate.value) to Purple500,
                    Offset(.66f, .33f + pointTranslate.value) to Purple500,
                    Offset(1f, .33f) to Purple500,
                ),

                listOf(
                    Offset(0f, .66f) to Blue400,
                    Offset(.33f, .66f - pointTranslate.value) to Blue400,
                    Offset(.66f, .66f + pointTranslate.value) to Blue400,
                    Offset(1f, .66f) to Blue400,
                ),

                listOf(
                    Offset(0f, 1f) to Indigo200,
                    Offset(.33f, 1f) to Indigo200,
                    Offset(.66f, 1f) to Indigo200,
                    Offset(1f, 1f) to Indigo200,
                ),
            )
        }
    }
    Box(
        Modifier
            .fillMaxSize()
            .meshGradient(
                points = points,
            )
    )
}