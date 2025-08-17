package com.example.meshgradients.practice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import com.example.meshgradients.customShapes.CouponSample1
import com.example.meshgradients.meshGradient
import com.example.meshgradients.ui.theme.AquaPastel
import com.example.meshgradients.ui.theme.BlushCoral
import com.example.meshgradients.ui.theme.FreshMint
import com.example.meshgradients.ui.theme.LightSpringGreen
import com.example.meshgradients.ui.theme.MintGreen
import com.example.meshgradients.ui.theme.PastelGreen
import com.example.meshgradients.ui.theme.PeachBeige
import com.example.meshgradients.ui.theme.SoftLemon
import com.example.meshgradients.ui.theme.SoftPink

@Composable
@Preview(showBackground = true)
fun CardsScreen(){
    val colors = listOf(
        MintGreen,
        PastelGreen,
        PeachBeige,
        SoftPink,
        BlushCoral,
        AquaPastel,
        FreshMint,
        SoftLemon,
        LightSpringGreen
    )
    val points=listOf(
        listOf(
            Offset(0f,0f) to colors[0],
            Offset(.5f,0f) to colors[1],
            Offset(1f,0f) to colors[2]
        ),
        listOf(
            Offset(0f,.5f) to colors[3],
            Offset(.5f,.5f) to colors[4],
            Offset(1f,.5f) to colors[5],
        ),
        listOf(
            Offset(0f,1f) to colors[6],
            Offset(.5f,1f) to colors[7],
            Offset(1f,1f) to colors[8],
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .meshGradient(
                points=points
            )
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MeshGradientCardSample2()
        MeshGradientCardSample1()
        CouponSample1()
    }
}