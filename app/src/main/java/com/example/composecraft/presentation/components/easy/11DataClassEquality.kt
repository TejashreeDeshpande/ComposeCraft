package com.example.composecraft.presentation.components.easy

data class MapLayer(
    val id: String,
    val name: String,
    val visible: Boolean
)


fun dataClassVisibility(layers: List<MapLayer>, isVisible: Boolean) : List<MapLayer> {
    return layers.filter { it.visible == isVisible }
}