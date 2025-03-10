package com.blrp.firebase.ui.views

fun Boolean.toResponse(): String {
    return if (this) "Disponible" else {
        "No disponible"

    }
}