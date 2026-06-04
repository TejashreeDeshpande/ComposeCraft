package com.example.composecraft.presentation.components.easy

//Null safety
//Write a function that accepts a nullable String and returns its length, or 0 if null.
// Then use let, ?: and !! appropriately in three variants.

fun nullSafety1(name: String?): Int {
    name?.let {
        return name.length
    }
    return 0
}

fun nullSafety2(name: String?): Int {
    return name?.length ?: 0
}

fun nullSafety3(name: String?): Int {
    if (name != null) {
        return name.length
    } else {
        return 0
    }
}