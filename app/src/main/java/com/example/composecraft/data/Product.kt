package com.example.composecraft.data

data class Product(
    val id: String,
    val name: String,
    val favorite: Boolean = false
)

val mockProducts: List<Product> = listOf(
    Product(
        id = "1",
        name = "iPhone"
    ),
    Product(
        id = "2",
        name = "MacBook Pro"
    ),
    Product(
        id = "3",
        name = "iPad Air"
    ),
    Product(
        id = "4",
        name = "Apple Watch"
    ),
    Product(
        id = "5",
        name = "AirPods Pro"
    ),
    Product(
        id = "6",
        name = "Samsung Galaxy S25"
    ),
    Product(
        id = "7",
        name = "Google Pixel"
    ),
    Product(
        id = "8",
        name = "Surface Laptop"
    ),
    Product(
        id = "9",
        name = "Kindle Paperwhite"
    ),
    Product(
        id = "10",
        name = "PlayStation 5"
    )
)