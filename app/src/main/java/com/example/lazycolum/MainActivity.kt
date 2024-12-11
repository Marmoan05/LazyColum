package com.example.lazycolum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PantallaDeTareas()
        }
    }
}

data class Tarea(
    val id: Int,
    val titulo: String,
    var estaCompletada: Boolean
) {
    var estadoCompletado = mutableStateOf(estaCompletada)
}

@Composable
fun PantallaDeTareas() {
    val tareas = remember {
        mutableStateListOf(

            Tarea( 1,"Estudiar para el examen", true),
            Tarea(2,"Llorar porque es el examen de pepe", false),
            Tarea(3,"Cocinar", false),
            Tarea(4,"Ir a la aceitunas",true),
            Tarea(5,"Pasear al perro", false),
            Tarea(6,"Hacer ejercicio",true),
            Tarea(7,"Revisar correos",false),
            Tarea(8,"Planificar la semana",false),
            Tarea(9,"Comprar víveres",true),
            Tarea(10,"Llamar al médico",false),
            Tarea(11,"Jugar videojuegos",true)
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(tareas) { tarea ->
            ItemDeTarea(tarea = tarea) {
                tarea.estaCompletada = !tarea.estaCompletada
            }
        }
    }
}

@Composable
fun ItemDeTarea(tarea: Tarea, alCambiarEstado: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (tarea.estadoCompletado.value) Icons.Filled.Check else Icons.Filled.Close,
            contentDescription = if (tarea.estadoCompletado.value) "Tarea completada" else "Tarea pendiente",
            modifier = Modifier.padding(end = 16.dp),
            tint = if (tarea.estadoCompletado.value) Color.Green else Color.Red
        )

        Text(text = tarea.titulo, modifier = Modifier.weight(1f))

        Button(
            onClick = {
                tarea.estadoCompletado.value = !tarea.estadoCompletado.value
                alCambiarEstado()
            },
            modifier = Modifier.width(200.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (tarea.estadoCompletado.value) Color.Red else Color.Green,
                contentColor = Color.White
            )
        ) {
            Text(text = if (tarea.estadoCompletado.value) "Marcar pendiente" else "Marcar completada")
        }
    }
}