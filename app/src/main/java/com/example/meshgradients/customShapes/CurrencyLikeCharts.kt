package com.example.meshgradients.customShapes

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview(showBackground = true)
fun PerformanceChartByPath(){
    val list=listOf(10f,20f,6f,11f,34f,36f,12f,67f,23f,1f,11f)
    Canvas(
        modifier = Modifier.fillMaxWidth().heightIn(300.dp)
    ) {
        if(list.size<2) return@Canvas

        val max=list.max()
        val min=list.min()
        val lines=list.size-1

        val widthPerStep=size.width/lines
        val height=size.height

        val lineColor=if(list.last()>list.first()) Color.Green else Color.Red

        val percentages= list.map { (it-min)/(max-min)}

        val path= Path().apply {
            moveTo(0f,height*(1-percentages.first()))
            for(i in 1 until list.size){
                lineTo(widthPerStep*i,height*(1-percentages[i]))
            }
        }
        drawPath(
            color = lineColor,
            path=path,
            style = Stroke(
                width=3f
            )
        )
    }
}


@Composable
@Preview(showBackground = true)
fun PerformanceCurvedChartByPath(){
    val list=listOf(10f,20f,6f,11f,34f,36f,12f,67f,23f,1f,5f)
    val infiniteTransition= rememberInfiniteTransition()
    val animatedPhase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2989f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val measurer= rememberTextMeasurer()
    Canvas(
        modifier = Modifier.fillMaxWidth().heightIn(300.dp)
    ) {
        if(list.size<2) return@Canvas

        val max=list.max()
        val min=list.min()
        val lines=list.size-1

        val widthPerStep=size.width/lines
        val height=size.height

        val lineColor=if(list.last()>list.first()) Color.Green else Color.Red

        val percentages= list.map { (it-min)/(max-min)}

        val path= Path().apply {
            moveTo(0f,height*(1-percentages.first()))
            for(i in 0 until list.lastIndex){
                val x1=widthPerStep*i
                val y1=height*(1-percentages[i])
                val x2= widthPerStep*(i+1)
                val y2=height*(1-percentages[i+1])
                val controlX=(x1+x2)/2
                cubicTo(
                    controlX,y1,
                    controlX,y2,
                    x2,y2
                )
            }
        }
        val pathMeasure= PathMeasure()
        pathMeasure.setPath(path,false)
        drawText(
            measurer,
            text= "${pathMeasure.length}"
        )
        drawPath(
            color = lineColor,
            path=path,
            style = Stroke(
                width=3f,
                pathEffect = PathEffect.dashPathEffect(
                    floatArrayOf(pathMeasure.length,pathMeasure.length),
                    animatedPhase
                )
            )
        )
    }
}