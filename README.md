# RegistroIncidencias

Aplicación Android desarrollada como parte de la asignatura Técnicas de Producción Industrial de Software I.

## Propósito

La aplicación permite registrar incidencias relacionadas con equipos, infraestructura o servicios.

## Herramientas utilizadas

* Android Studio
* Kotlin
* Jetpack Compose
* Git
* GitHub

## Estado actual

Actualmente la aplicación cuenta con una pantalla inicial para el registro de incidencias.

Incluye:

* Título de la aplicación.
* Campos para ingresar información.
* Botón principal para crear un reporte.
* Área de estado inicial.

Aún falta implementar el funcionamiento completo del registro y almacenamiento de incidencias.

## Segunda fase

La aplicación RegistroIncidencias ahora permite:

* Ingresar el título de una incidencia.
* Ingresar una descripción breve.
* Mantener los datos introducidos mediante estado.
* Presionar el botón "Crear reporte".
* Mostrar una retroalimentación visible con el título ingresado.

Se utilizaron remember y mutableStateOf para manejar el estado básico de la interfaz.

Aún falta implementar el almacenamiento permanente de los reportes.

## Cómo abrir el proyecto

1. Clonar o descargar el repositorio.
2. Abrir Android Studio.
3. Seleccionar **Open**.
4. Elegir la carpeta del proyecto.
5. Esperar a que Gradle termine la sincronización.
6. Ejecutar la aplicación en un emulador o dispositivo Android.

## Avance Semana 10

Se mejoró la aplicación RegistroIncidencias incorporando:

* Configuración de teclado contextual en el campo de título.
* Uso de KeyboardCapitalization.Sentences.
* Uso de ImeAction.Next.
* Interacción táctil mediante una tarjeta clicable para seleccionar prioridad alta.
* Retroalimentación visible de la prioridad seleccionada.
* Ejecución comprobada en emulador.

## Autor

Josué Penutt  
Universidad Tecnológica de El Salvador  
Técnicas de Producción Industrial de Software I

## Avance Semana 11

Se integró una primera lectura de sensores en la aplicación RegistroIncidencias.

* Sensor utilizado: acelerómetro.
* Se muestran las lecturas de los ejes X, Y y Z.
* Se utilizó SensorManager y SensorEventListener.
* El listener se controla mediante DisposableEffect.
* Si el acelerómetro no está disponible, la aplicación muestra un mensaje informativo.
* La lectura fue probada en el emulador modificando la posición del dispositivo y verificando que los valores X, Y y Z cambian.



\## Avance Semana 12



Se agregó una interpretación de las lecturas del acelerómetro dentro de RegistroIncidencias.



\- Sensor utilizado: acelerómetro.

\- Se muestran valores X, Y y Z.

\- La aplicación interpreta el movimiento del dispositivo.

\- Puede mostrar "Dispositivo estable" o "Movimiento detectado".

\- Se utiliza DisposableEffect para controlar el sensor.

\- El listener se libera mediante unregisterListener.

\- Las pruebas se realizaron con los sensores virtuales del emulador de Android Studio.

