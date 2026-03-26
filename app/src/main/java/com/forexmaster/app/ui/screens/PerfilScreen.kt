package com.forexmaster.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.forexmaster.app.ui.theme.*

@Composable
fun PerfilScreen(onNavigateToLogin: () -> Unit) {
    var notificaciones by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        // ── Cabecera con avatar ───────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(CubaBlueDark, CubaBlue)
                    )
                )
                .padding(horizontal = 20.dp, vertical = 32.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    Box(
                        modifier = Modifier
                            .size(84.dp)
                            .clip(CircleShape)
                            .background(White.copy(alpha = 0.25f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text  = "CG",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = White
                        )
                    }
                    // Badge editar
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .clip(CircleShape)
                            .background(CubaGold)
                            .align(Alignment.BottomEnd),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Editar",
                            tint = White,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text  = "Carlos García",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
                Text(
                    text  = "carlos.garcia@email.com",
                    fontSize = 13.sp,
                    color = White.copy(alpha = 0.8f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Fila de métricas
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ProfileStat(value = "8",      label = "Recargas")
                    VerticalDivider(
                        modifier = Modifier.height(36.dp),
                        color    = White.copy(alpha = 0.3f)
                    )
                    ProfileStat(value = "$75.50", label = "Enviado")
                    VerticalDivider(
                        modifier = Modifier.height(36.dp),
                        color    = White.copy(alpha = 0.3f)
                    )
                    ProfileStat(value = "4",      label = "Contactos")
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ── Saldo y método de pago ────────────────────────────────
        Card(
            modifier  = Modifier.padding(horizontal = 20.dp),
            shape     = RoundedCornerShape(16.dp),
            colors    = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(CubaBlueContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccountBalanceWallet,
                        contentDescription = null,
                        tint = CubaBlue,
                        modifier = Modifier.size(26.dp)
                    )
                }
                Spacer(modifier = Modifier.width(14.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text  = "Saldo disponible",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text  = "$24.50 USD",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Button(
                    onClick = {},
                    shape  = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CubaBlue),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Text("Añadir", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ── Sección cuenta ────────────────────────────────────────
        SectionTitle("Mi cuenta")
        Spacer(modifier = Modifier.height(8.dp))

        SettingsGroup {
            SettingsItem(
                icon      = Icons.Filled.CreditCard,
                iconBg    = CubaBlueContainer,
                iconColor = CubaBlue,
                title     = "Métodos de pago",
                subtitle  = "2 tarjetas guardadas",
                showArrow = true,
                onClick   = {}
            )
            HorizontalDivider(modifier = Modifier.padding(start = 62.dp))
            SettingsItem(
                icon      = Icons.Filled.Contacts,
                iconBg    = SuccessContainer,
                iconColor = SuccessGreen,
                title     = "Mis contactos",
                subtitle  = "4 contactos en Cuba",
                showArrow = true,
                onClick   = {}
            )
            HorizontalDivider(modifier = Modifier.padding(start = 62.dp))
            SettingsItem(
                icon      = Icons.Filled.History,
                iconBg    = Color(0xFFFFF3CD),
                iconColor = CubaGold,
                title     = "Mis recargas",
                subtitle  = "Ver historial completo",
                showArrow = true,
                onClick   = {}
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ── Sección preferencias ──────────────────────────────────
        SectionTitle("Preferencias")
        Spacer(modifier = Modifier.height(8.dp))

        SettingsGroup {
            // Toggle notificaciones
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(CubaRedContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = null,
                        tint = CubaRed,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Spacer(modifier = Modifier.width(14.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text  = "Notificaciones",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text  = "Alertas de recargas",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Switch(
                    checked         = notificaciones,
                    onCheckedChange = { notificaciones = it },
                    colors          = SwitchDefaults.colors(
                        checkedThumbColor  = White,
                        checkedTrackColor  = CubaBlue
                    )
                )
            }
            HorizontalDivider(modifier = Modifier.padding(start = 62.dp))
            SettingsItem(
                icon      = Icons.Filled.Language,
                iconBg    = MaterialTheme.colorScheme.surfaceVariant,
                iconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                title     = "Idioma",
                subtitle  = "Español",
                showArrow = true,
                onClick   = {}
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ── Sección soporte ───────────────────────────────────────
        SectionTitle("Soporte")
        Spacer(modifier = Modifier.height(8.dp))

        SettingsGroup {
            SettingsItem(
                icon      = Icons.Filled.HelpOutline,
                iconBg    = MaterialTheme.colorScheme.surfaceVariant,
                iconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                title     = "Ayuda y FAQ",
                showArrow = true,
                onClick   = {}
            )
            HorizontalDivider(modifier = Modifier.padding(start = 62.dp))
            SettingsItem(
                icon      = Icons.Filled.ChatBubbleOutline,
                iconBg    = MaterialTheme.colorScheme.surfaceVariant,
                iconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                title     = "Contactar soporte",
                showArrow = true,
                onClick   = {}
            )
            HorizontalDivider(modifier = Modifier.padding(start = 62.dp))
            SettingsItem(
                icon      = Icons.Filled.Star,
                iconBg    = Color(0xFFFFF3CD),
                iconColor = CubaGold,
                title     = "Calificar la app",
                showArrow = true,
                onClick   = {}
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ── Cerrar sesión ─────────────────────────────────────────
        OutlinedButton(
            onClick  = { onNavigateToLogin() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(50.dp),
            shape  = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = CubaRed),
            border = ButtonDefaults.outlinedButtonBorder.copy(
                width = 1.5.dp
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Logout,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Cerrar sesión",
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text     = "CubaRecarga v1.0.0",
            fontSize = 12.sp,
            color    = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ProfileStat(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text  = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = White
        )
        Text(
            text  = label,
            fontSize = 11.sp,
            color = White.copy(alpha = 0.75f)
        )
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text  = title,
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}

@Composable
private fun SettingsGroup(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier  = Modifier.padding(horizontal = 20.dp),
        shape     = RoundedCornerShape(16.dp),
        colors    = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column { content() }
    }
}

@Composable
private fun SettingsItem(
    icon: ImageVector,
    iconBg: Color,
    iconColor: Color,
    title: String,
    subtitle: String = "",
    showArrow: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(iconBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(22.dp)
            )
        }
        Spacer(modifier = Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text  = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
            if (subtitle.isNotEmpty()) {
                Text(
                    text  = subtitle,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        if (showArrow) {
            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
