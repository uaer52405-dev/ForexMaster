package com.forexmaster.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.forexmaster.app.ui.theme.*

private data class Transaccion(
    val numero: String,
    val tipo: String,
    val monto: String,
    val fecha: String,
    val estado: Estado
)

private enum class Estado { COMPLETADA, PENDIENTE, FALLIDA }

@Composable
fun HistorialScreen() {
    var filtroActivo by remember { mutableStateOf("Todas") }
    val filtros = listOf("Todas", "Completadas", "Pendientes", "Fallidas")

    val transacciones = listOf(
        Transaccion("+53 5 123 4567", "Cubacel",   "$10.00", "Hoy, 9:15 AM",       Estado.COMPLETADA),
        Transaccion("+53 5 987 6543", "Nauta",     "$5.00",  "Hoy, 8:02 AM",       Estado.COMPLETADA),
        Transaccion("+53 5 444 2233", "Paquete B", "$15.00", "Ayer, 6:45 PM",      Estado.PENDIENTE),
        Transaccion("+53 5 111 9988", "Cubacel",   "$20.00", "Ayer, 2:10 PM",      Estado.COMPLETADA),
        Transaccion("+53 5 777 3322", "Nauta",     "$3.50",  "23 Mar, 11:30 AM",   Estado.FALLIDA),
        Transaccion("+53 5 555 0011", "Paquete A", "$7.00",  "22 Mar, 4:00 PM",    Estado.COMPLETADA),
        Transaccion("+53 5 234 5678", "Cubacel",   "$10.00", "20 Mar, 9:00 AM",    Estado.COMPLETADA),
        Transaccion("+53 5 876 5432", "Cubacel",   "$5.00",  "18 Mar, 3:30 PM",    Estado.COMPLETADA)
    )

    val transaccionesFiltradas = when (filtroActivo) {
        "Completadas" -> transacciones.filter { it.estado == Estado.COMPLETADA }
        "Pendientes"  -> transacciones.filter { it.estado == Estado.PENDIENTE }
        "Fallidas"    -> transacciones.filter { it.estado == Estado.FALLIDA }
        else          -> transacciones
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // ── Cabecera ──────────────────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(CubaBlueDark, CubaBlue)
                    )
                )
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            Column {
                Text(
                    text = "Historial",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
                Text(
                    text = "${transacciones.size} transacciones",
                    fontSize = 13.sp,
                    color = White.copy(alpha = 0.8f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Resumen rápido
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    MiniStatCard(
                        value   = "$75.50",
                        label   = "Total enviado",
                        color   = White,
                        modifier = Modifier.weight(1f)
                    )
                    MiniStatCard(
                        value   = "8",
                        label   = "Recargas",
                        color   = White,
                        modifier = Modifier.weight(1f)
                    )
                    MiniStatCard(
                        value   = "4",
                        label   = "Contactos",
                        color   = White,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ── Filtros ───────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            filtros.forEach { filtro ->
                val selected = filtroActivo == filtro
                FilterChip(
                    selected = selected,
                    onClick  = { filtroActivo = filtro },
                    label    = { Text(filtro, fontSize = 12.sp) },
                    colors   = FilterChipDefaults.filterChipColors(
                        selectedContainerColor    = CubaBlue,
                        selectedLabelColor        = White,
                        selectedLeadingIconColor  = White
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // ── Lista ─────────────────────────────────────────────────
        if (transaccionesFiltradas.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Filled.SearchOff,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(56.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Sin resultados",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(transaccionesFiltradas) { tx ->
                    TransaccionItem(tx)
                }
                item { Spacer(modifier = Modifier.height(12.dp)) }
            }
        }
    }
}

@Composable
private fun TransaccionItem(tx: Transaccion) {
    val (estadoColor, estadoBg, estadoLabel, icono) = when (tx.estado) {
        Estado.COMPLETADA -> arrayOf(SuccessGreen,   SuccessContainer,           "Completada",  Icons.Filled.CheckCircle)
        Estado.PENDIENTE  -> arrayOf(CubaGold,       Color(0xFFFFF3CD),          "Pendiente",   Icons.Filled.HourglassTop)
        Estado.FALLIDA    -> arrayOf(CubaRed,        CubaRedContainer,           "Fallida",     Icons.Filled.Cancel)
    }

    val tipoIcon: ImageVector = when (tx.tipo) {
        "Nauta"  -> Icons.Filled.Wifi
        else     -> Icons.Filled.PhoneAndroid
    }

    Card(
        shape  = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono tipo
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(CubaBlueContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = tipoIcon,
                    contentDescription = null,
                    tint = CubaBlue,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text  = tx.numero,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text  = "${tx.tipo} · ${tx.fecha}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text  = tx.monto,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = estadoBg as Color
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Icon(
                            imageVector = icono as ImageVector,
                            contentDescription = null,
                            tint = estadoColor as Color,
                            modifier = Modifier.size(11.dp)
                        )
                        Text(
                            text = estadoLabel as String,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            color = estadoColor
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MiniStatCard(
    value: String,
    label: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(White.copy(alpha = 0.15f))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text  = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text  = label,
            fontSize = 11.sp,
            color = color.copy(alpha = 0.8f)
        )
    }
}
