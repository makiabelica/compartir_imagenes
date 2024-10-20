package com.example.compartir_imagenes.compose

import android.content.Context
import android.net.Uri
import android.provider.MediaStore

fun getImagesFromGallery(context: Context): List<Uri> {
    val imageUris = mutableListOf<Uri>()
    val projection = arrayOf(MediaStore.Images.Media._ID)

    // Consulta para obtener todas las imágenes del dispositivo
    val cursor = context.contentResolver.query(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        projection,
        null,
        null,
        "${MediaStore.Images.Media.DATE_ADDED} DESC" // Orden por fecha de añadido
    )

    cursor?.use {
        val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
        while (it.moveToNext()) {
            val id = it.getLong(idColumn)
            val contentUri = Uri.withAppendedPath(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                id.toString()
            )
            imageUris.add(contentUri) // Agregar cada imagen encontrada a la lista
        }
    }

    return imageUris
}