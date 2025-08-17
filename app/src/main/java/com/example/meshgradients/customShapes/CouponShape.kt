package com.example.meshgradients.customShapes

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.times

class CouponShape private constructor( val arcRadius: Dp?,val  numberOfArcs: Int?,val spaceHeight: Dp?): Shape{

    constructor(arcRadius: Dp):this(arcRadius=arcRadius,numberOfArcs=null,spaceHeight=null)
    constructor(numberOfArcs: Int,spaceHeight: Dp):this( arcRadius = null,numberOfArcs=numberOfArcs,spaceHeight=spaceHeight)

    private fun calculateNumberOfArcs(
        heightPx: Float,
        arcRadius: Dp,
        spaceHeight: Dp,
        density: Density
    ): Int {
        val individualHeightPx = with(density) { (2 * arcRadius + spaceHeight).toPx() }
        return (heightPx / individualHeightPx).toInt()
    }

    private fun calculateArcRadius(
        heightPx: Float,
        numberOfArcs: Int,
        spaceHeight: Dp,
        density: Density
    ): Dp {
        val totalSpaceHeight = spaceHeight * numberOfArcs
        val totalArcs = 2 * numberOfArcs
        val remainingHeightPx = heightPx - with(density) { totalSpaceHeight.toPx() }
        val arcRadiusPx = remainingHeightPx / totalArcs
        return with(density) { arcRadiusPx.toDp() }
    }

    private fun calculateSpaceHeight(height:Dp,numberOfArcs: Int,arcRadius: Dp):Dp{
        val arcsHeight=arcRadius*numberOfArcs*2
        val remainingHeight=height-arcsHeight
        return remainingHeight/numberOfArcs
    }

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {

        val width=size.width
        val height=size.height

        val heightDp = with(density) { size.height.toDp() }

        val arcs: Int
        val radius: Dp
        val space: Dp

        if (arcRadius != null) {
            radius = arcRadius
            val defaultSpace = radius * (4 / 3f)
            arcs = calculateNumberOfArcs(size.height, radius, defaultSpace, density)
            space = calculateSpaceHeight(heightDp, arcs, radius)

        } else {
            arcs = numberOfArcs!!
            space = spaceHeight!!
            radius = calculateArcRadius(size.height, arcs, space, density)
        }
        val radiusPx=with(density){radius.toPx()}
        val spaceHeightPx=with(density){space.toPx()}
        val step = 2 * radiusPx + spaceHeightPx
        val path = Path().apply {
            arcs.let {
                if (it > 0) {
                    arcTo(
                        rect = Rect(
                            center = Offset(0f, 0f),
                            radius = radiusPx
                        ),
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    if(it>1)
                        repeat(it - 1) { index ->
                            arcTo(
                                rect =  Rect(
                                    center = Offset(0f, (index + 1) * step),
                                    radius = radiusPx
                                ),
                                startAngleDegrees = 270f,
                                sweepAngleDegrees = 180f,
                                forceMoveTo = false
                            )
                        }
                    arcTo(
                        rect = Rect(
                            center = Offset(0f, height),
                            radius = radiusPx
                        ),
                        startAngleDegrees = 270f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false

                    )
                    arcTo(
                        rect = Rect(
                            center = Offset(width, height),
                            radius = radiusPx
                        ),
                        startAngleDegrees = 180f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    if(it>1)
                        repeat(it - 1) { index ->
                            arcTo(
                                rect = Rect(
                                    center = Offset(width, (index + 1) * step),
                                    radius = radiusPx
                                ),
                                startAngleDegrees = 90f,
                                sweepAngleDegrees = 180f,
                                forceMoveTo = false
                            )
                        }
                    arcTo(
                        rect = Rect(
                            center = Offset(width, 0f),
                            radius = radiusPx
                        ),
                        startAngleDegrees = 90f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false

                    )
                    close()
                }else{
                    addRect(
                        rect = Rect(left = 0f, top = 0f, right = width, bottom = height)
                    )
                }
            }
        }
        return Outline.Generic(path)
    }
}