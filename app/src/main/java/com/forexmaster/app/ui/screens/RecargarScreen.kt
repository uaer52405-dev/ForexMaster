package com.forexmaster.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.forexmaster.app.ui.theme.*

private data class Plan(
    val nombre: String,
    val precio: String,
    val descripcion: String,
    val popular: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecargarScreen() {
    var selectedTab   by remember { mutableIntStateOf(0) }
    var telefono      by remember { mutableStateOf("") }
    var selectedPlan  by remember { mutableStateOf<Plan?>(null) }
    var showConfirm   by remember { mutableStateOf(false) }

    val tabs = listOf("Cubacel", "Nauta", "Paquetes")

    val planesCubacel = listOf(
        Plan("Básica",    "$5.00",  "25 CUC + bono\nválido 30 días"),
        Plan("Estándar",  "$10.00", "60 CUC + bono\nválido 30 días", popular = true),
        Plan("Plus",      "$20.00", "130 CUC + bono\nválido 30 días"),
        Plan("Premium",   "$30.00", "200 CUC + bono\nválido 30 días")
    )
    val planesNauta = listOf(
        Plan("1 hora",   "$1.50",  "Nauta Hogar/WiFi\n1 hora de navegación"),
        Plan("3 horas",  "$3.50",  "Nauta Hogar/WiFi\n3 horas de navegación", popular = true),
        Plan("10 horas", "$10.00", "Nauta Hogar/WiFi\n10 horas de navegación")
    )
    val planesPaquetes = listOf(
        Plan("Pack A",  "$7.00",  "300 MB datos\n+ 60 CUC voz"),
        Plan("Pack B",  "$15.00", "1 GB datos\n+ 130 CUC voz", popular = true),
        Plan("Pack C",  "$25.00", "2.5 GB datos\n+ 200 CUC voz")
    )

    val planesActuales = when (selectedTab) {
        0 -> planesCubacel
        1 -> planesNauta
        else -> planesPaquetes
    }

    if (showConfirm && selectedPlan != null) {
        ConfirmDialog(
            telefono = telefono,
            plan     = selectedPlan!!,
            onDismiss = { showConfirm = false },
            onConfirm = { showConfirm = false; selectedPlan = null; telefono = "" }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
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
                    text = "Nueva Recarga",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
                Text(
                    text = "Envía crédito a Cuba al instante",
                    fontSize = 13.sp,
                    color = White.copy(alpha = 0.8f)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ── Número de teléfono ────────────────────────────────────
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Número en Cuba",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = telefono,
                onValueChange = { if (it.length <= 11) telefono = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                placeholder = {
                    Text(
                        text = "+53 5 XXX XXXX",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                leadingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 12.dp, end = 4.dp)
                    ) {
                        Text(text = "🇨🇺", fontSize = 20.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "+53",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                trailingIcon = {
                    if (telefono.isNotEmpty()) {
                        IconButton(onClick = { telefono = "" }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Borrar",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    } else {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Filled.Contacts,
                                contentDescription = "Contactos",
                                tint = CubaBlue
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor   = CubaBlue,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                )
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ── Tabs de servicio ──────────────────────────────────────
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Tipo de recarga",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                tabs.forEachIndexed { index, tab ->
                    val isSelected = selectedTab == index
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                if (isSelected) CubaBlue
                                else Color.Transparent
                            )
                            .clickable { selectedTab = index; selectedPlan = null }
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = tab,
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) White
                                    else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ── Grid de planes ────────────────────────────────────────
        Text(
            text = "Selecciona un plan",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            planesActuales.chunked(2).forEach { fila ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    fila.forEach { plan ->
                        PlanCard(
                            plan     = plan,
                            selected = selectedPlan == plan,
                            modifier = Modifier.weight(1f),
                            onClick  = { selectedPlan = plan }
                        )
                    }
                    // Celda vacía si la fila tiene número impar
                    if (fila.size == 1) Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // ── Botón confirmar ───────────────────────────────────────
        Button(
            onClick  = { if (telefono.isNotEmpty() && selectedPlan != null) showConfirm = true },
            enabled  = telefono.length >= 7 && selectedPlan != null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(54.dp),
            shape  = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor         = CubaBlue,
                contentColor           = White,
                disabledContainerColor = MaterialTheme.colorScheme.outline,
                disabledContentColor   = MaterialTheme.colorScheme.onSurfaceVariant
            )
        ) {
            Icon(
                imageVector = Icons.Filled.FlashOn,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (selectedPlan != null)
                    "Recargar ${selectedPlan!!.precio}"
                else "Recargar ahora",
                fontSize  = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(28.dp))
    }
}

@Composable
private fun PlanCard(
    plan: Plan,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val borderColor = if (selected) CubaBlue else MaterialTheme.colorScheme.outlineVariant
    val bgColor     = if (selected) CubaBlueContainer else MaterialTheme.colorScheme.surface

    Box(modifier = modifier) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = if (selected) 2.dp else 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(14.dp)
                )
                .clickable { onClick() },
            shape  = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = bgColor),
            elevation = CardDefaults.cardElevation(
                defaultElevation = if (selected) 4.dp else 1.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(14.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = plan.nombre,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (selected) CubaBlueDark
                            else MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = plan.precio,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (selected) CubaBlue else MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = plan.descripcion,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    lineHeight = 15.sp
                )
                if (selected) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = null,
                        tint = CubaBlue,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
        // Badge popular
        if (plan.popular) {
            Surface(
                shape  = RoundedCornerShape(bottomStart = 10.dp, topEnd = 14.dp),
                color  = CubaRed,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Text(
                    text  = "Popular",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = White,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
            }
        }
    }
}

@Composable
private fun ConfirmDialog(
    telefono: String,
    plan: Plan,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(20.dp),
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(CubaBlueContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.FlashOn,
                        contentDescription = null,
                        tint = CubaBlue,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Confirmar recarga",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ConfirmRow("Número", "+53 $telefono")
                ConfirmRow("Plan", plan.nombre)
                ConfirmRow("Monto", plan.precio)
                HorizontalDivider()
                ConfirmRow("Total a pagar", plan.precio, bold = true)
            }
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                modifier = Modifier.fillMaxWidth(),
                shape  = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = CubaBlue)
            ) {
                Text(
                    text = "Confirmar y recargar",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Cancelar",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}

@Composable
private fun ConfirmRow(label: String, value: String, bold: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text  = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text  = value,
            fontSize = 14.sp,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
