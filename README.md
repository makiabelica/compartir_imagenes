## Descripción del Proyecto

Este proyecto es una aplicación móvil creada en Kotlin utilizando Jetpack Compose para la interfaz de usuario. La aplicación permite:

1. Tomar fotos utilizando la cámara del dispositivo.
2. Guardar las fotos capturadas y visualizarlas en la galería del dispositivo.
3. Acceder a la galería nativa del sistema para ver las imágenes.
4. Compartir las fotos capturadas con otras aplicaciones mediante un Intent.
5. El proyecto utiliza la API Upside Down Cake (Android 14), teniendo en cuenta los permisos modernos y las nuevas prácticas recomendadas para versiones recientes de Android

### Instrucciones para Ejecutar el Proyecto

**Paso 1: Clonar o Descargar el Proyecto**
Clona el repositorio o descarga los archivos del proyecto en tu máquina local.

```
git clone <url del repo>

```

**Paso 2: Abrir el Proyecto en Android Studio**

1. Abre Android Studio.
2. Selecciona "Open Project" y navega a la carpeta donde clonaste o descargaste el proyecto.
3. Asegúrate de que tu Android Studio esté configurado para utilizar la API 34 (Android Upside Down Cake).

**Paso 3: Configurar un Emulador o un Dispositivo Físico**

- Asegúrate de que tu emulador o dispositivo físico esté ejecutando Android 14 (API 34) o superior.
- Si usas un dispositivo físico, habilita el modo de desarrollador y activa depuración USB.

**Paso 4: Ejecutar el Proyecto**

1. Haz clic en el botón Run en Android Studio o usa el atajo Shift + F10.
2. Elige tu dispositivo emulado o físico.
3. Espera a que la aplicación se instale y abra.

### Permisos Requeridos

Para garantizar que la aplicación funcione correctamente, debes gestionar los siguientes permisos:

Cámara:Necesario para tomar fotos con el dispositivo.

```
<uses-permission android:name="android.permission.CAMERA" />

```

Para versiones de Android 13 (API 33) y superiores, se debe usar el permiso READ_MEDIA_IMAGES para acceder a las imágenes.

```
<uses-permission android:name="android.permission.READ_MEDIA_IMAGES" />

```

Necesario para leer imágenes en versiones más antiguas de Android.

```
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

```

Al iniciar la aplicación, se solicitan los permisos de cámara y acceso a la galería cuando el usuario interactúa con las funcionalidades correspondientes.
La aplicación usa ``ActivityResultContracts.RequestPermission`` para gestionar los permisos en tiempo de ejecución.
