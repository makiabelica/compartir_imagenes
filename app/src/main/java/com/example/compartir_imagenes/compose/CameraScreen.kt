package com.example.compartir_imagenes.compose

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import java.io.File


@Composable
fun CameraScreen(navController: NavController) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // Lanzador para abrir la cámara
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess && imageUri != null) {
            navController.navigate("preview_screen/${Uri.encode(imageUri.toString())}")
        } else {
            Toast.makeText(context, "No se capturó ninguna foto", Toast.LENGTH_SHORT).show()
        }
    }

    // Lanzador para solicitar permisos de cámara
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            val uri = createImageFile(context)
            imageUri = uri
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
        }
    }

    // Lanzador para abrir la galería del dispositivo
    val galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        // Opcional: Manejar cualquier resultado después de cerrar la galería si lo necesitas.
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { cameraPermissionLauncher.launch(Manifest.permission.CAMERA) }) {
            Text(text = "Tomar Foto")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { openGallery(context, galleryLauncher) }) {
            Text(text = "Ver Galería")
        }
    }
}

// Crear un archivo temporal para la imagen y devolver su Uri
fun createImageFile(context: Context): Uri {
    val filename = "IMG_${System.currentTimeMillis()}.jpg"
    val file = File(context.cacheDir, filename)
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        file
    )
}

// Función para abrir la galería del dispositivo
fun openGallery(context: Context, galleryLauncher: androidx.activity.result.ActivityResultLauncher<Intent>) {
    val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    galleryLauncher.launch(intent)
}