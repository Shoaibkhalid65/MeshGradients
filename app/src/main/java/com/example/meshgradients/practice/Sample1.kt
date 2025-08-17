package com.example.meshgradients.practice

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.VertexMode
import androidx.compose.ui.graphics.Vertices
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meshgradients.ui.theme.Emerald500
import com.example.meshgradients.ui.theme.Red500
import com.example.meshgradients.ui.theme.Teal500
import com.example.meshgradients.ui.theme.Yellow500


@Composable
@Preview(showBackground = true)
fun Sample1(){
    Canvas(
        modifier = Modifier.size(100.dp)
    ) {
        drawIntoCanvas { canvas ->
            val points=listOf(
                Offset(0f,0f),
                Offset(size.width,0f),
                Offset(0f,size.height),
                Offset(size.width,size.height)
            )
            val colors=listOf(
                Red500,
                Yellow500,
                Teal500,
                Emerald500
            )
            canvas.drawVertices(
                vertices = Vertices(
                    vertexMode = VertexMode.Triangles,
                    positions = points,
                    textureCoordinates = points,
                    colors = colors,
                    indices = listOf(0,1,3,0,2,3)
                ),
                blendMode = BlendMode.Dst,
                paint = Paint()
            )
        }
    }
}