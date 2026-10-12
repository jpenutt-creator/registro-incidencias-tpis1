package sv.edu.utec.etps1.registroincidencias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sv.edu.utec.etps1.registroincidencias.ui.theme.RegistroIncidenciasTheme
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RegistroIncidenciasTheme {
                RegistroIncidenciasApp()
            }
        }
    }
}

@Composable
fun RegistroIncidenciasApp() {

    val titulo = remember { mutableStateOf("") }
    val descripcion = remember { mutableStateOf("") }
    val mensaje = remember { mutableStateOf("Aún no hay reporte creado") }
    val prioridad = remember { mutableStateOf("Sin prioridad") }
    val context = LocalContext.current

    val sensorManager = remember {
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    val accelerometer = remember {
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    val x = remember { mutableStateOf(0f) }
    val y = remember { mutableStateOf(0f) }
    val z = remember { mutableStateOf(0f) }
    DisposableEffect(accelerometer) {

        val listener = object : SensorEventListener {

            override fun onSensorChanged(event: SensorEvent) {
                x.value = event.values[0]
                y.value = event.values[1]
                z.value = event.values[2]
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            }
        }

        if (accelerometer != null) {
            sensorManager.registerListener(
                listener,
                accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            text = "Registro de incidencias",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Ingresa los datos básicos para preparar un reporte."
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = titulo.value,
            onValueChange = { titulo.value = it },
            label = { Text("Título de la incidencia") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = descripcion.value,
            onValueChange = { descripcion.value = it },
            label = { Text("Descripción breve") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    prioridad.value = "Alta"
                }
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Prioridad alta",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Toca para seleccionar esta prioridad"
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Prioridad seleccionada: ${prioridad.value}"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Acelerómetro",
                    style = MaterialTheme.typography.titleMedium
                )

                if (accelerometer != null) {
                    Text("X: ${x.value}")
                    Text("Y: ${y.value}")
                    Text("Z: ${z.value}")
                } else {
                    Text("Acelerómetro no disponible en este dispositivo")
                }
            }
        }

        Button(
            onClick = {
                mensaje.value = "Reporte preparado: ${titulo.value}"
            }
        ) {
            Text("Crear reporte")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Estado",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = mensaje.value
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistroIncidenciasPreview() {
    RegistroIncidenciasTheme {
        RegistroIncidenciasApp()
    }
}