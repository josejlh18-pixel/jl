package com.jornalera.v3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jornalera.v3.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { JornaleraTheme { JornaleraApp() } }
    }
}

private enum class Destination(val label: String, val icon: ImageVector) {
    TODAY("Hoje", Icons.Default.Home),
    HISTORY("Histórico", Icons.AutoMirrored.Filled.ReceiptLong),
    PROFILE("Perfil", Icons.Default.Person)
}

@Composable
fun JornaleraApp() {
    var destination by rememberSaveable { mutableStateOf(Destination.TODAY) }
    var checkedIn by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                Destination.entries.forEach { item ->
                    NavigationBarItem(
                        selected = destination == item,
                        onClick = { destination = item },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        colors = NavigationBarItemDefaults.colors(indicatorColor = Mint)
                    )
                }
            }
        }
    ) { padding ->
        when (destination) {
            Destination.TODAY -> TodayScreen(Modifier.padding(padding), checkedIn) { checkedIn = !checkedIn }
            Destination.HISTORY -> HistoryScreen(Modifier.padding(padding))
            Destination.PROFILE -> ProfileScreen(Modifier.padding(padding))
        }
    }
}

@Composable
private fun TodayScreen(modifier: Modifier, checkedIn: Boolean, onCheckIn: () -> Unit) {
    LazyColumn(modifier = modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.weight(1f)) {
                    Text("Bom dia, Marina", style = MaterialTheme.typography.headlineSmall)
                    Text("Quarta-feira, 30 de setembro", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.outline)
                }
                Box(Modifier.size(44.dp).clip(CircleShape).background(Moss), contentAlignment = Alignment.Center) {
                    Text("M", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
        item { EarningsCard() }
        item {
            Text("Sua jornada", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(10.dp))
            ShiftCard(checkedIn, onCheckIn)
        }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text("Próximos trabalhos", style = MaterialTheme.typography.titleLarge)
                Text("Ver agenda", color = Moss, style = MaterialTheme.typography.labelLarge)
            }
            Spacer(Modifier.height(10.dp))
            JobRow("Amanhã", "Feira da Vila", "08:00 — 16:00", "R$ 150")
            Spacer(Modifier.height(8.dp))
            JobRow("Sex, 02", "Casa da Célia", "09:00 — 15:00", "R$ 135")
        }
    }
}

@Composable private fun EarningsCard() {
    Card(colors = CardDefaults.cardColors(containerColor = Moss), shape = RoundedCornerShape(24.dp)) {
        Column(Modifier.padding(22.dp)) {
            Text("GANHOS DE SETEMBRO", color = Mint, style = MaterialTheme.typography.labelLarge)
            Spacer(Modifier.height(8.dp))
            Text("R$ 2.430,00", color = Color.White, style = MaterialTheme.typography.displaySmall)
            Spacer(Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.TrendingUp, null, tint = Mint, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(6.dp))
                Text("12% a mais que agosto", color = Mint, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable private fun ShiftCard(checkedIn: Boolean, onCheckIn: () -> Unit) {
    Card(shape = RoundedCornerShape(24.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
        Column(Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(42.dp).clip(CircleShape).background(Sky), contentAlignment = Alignment.Center) { Icon(Icons.Default.LocationOn, null, tint = Moss) }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("Casa da Dona Rita", style = MaterialTheme.typography.titleMedium)
                    Text("Rua das Flores, 218", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.outline)
                }
                AssistChip(onClick = {}, label = { Text("Hoje") })
            }
            Spacer(Modifier.height(20.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
            Spacer(Modifier.height(16.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Detail("Horário", "08:00 — 16:00")
                Detail("Valor", "R$ 156,00", TextAlign.End)
            }
            Spacer(Modifier.height(20.dp))
            Button(onClick = onCheckIn, modifier = Modifier.fillMaxWidth().height(52.dp), shape = RoundedCornerShape(16.dp), colors = ButtonDefaults.buttonColors(containerColor = if (checkedIn) Clay else Moss)) {
                Icon(if (checkedIn) Icons.Default.CheckCircle else Icons.Default.PlayArrow, null)
                Spacer(Modifier.width(8.dp))
                Text(if (checkedIn) "Jornada iniciada" else "Iniciar jornada")
            }
        }
    }
}

@Composable private fun Detail(label: String, value: String, align: TextAlign = TextAlign.Start) {
    Column(horizontalAlignment = if (align == TextAlign.End) Alignment.End else Alignment.Start) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.outline)
        Text(value, style = MaterialTheme.typography.titleMedium, textAlign = align)
    }
}

@Composable private fun JobRow(day: String, place: String, time: String, value: String) {
    Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(18.dp)).background(MaterialTheme.colorScheme.surface).padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(day, style = MaterialTheme.typography.labelLarge, color = Moss, modifier = Modifier.width(58.dp))
        Column(Modifier.weight(1f)) { Text(place, style = MaterialTheme.typography.titleMedium); Text(time, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.outline) }
        Text(value, style = MaterialTheme.typography.labelLarge)
    }
}

@Composable private fun HistoryScreen(modifier: Modifier) {
    LazyColumn(modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        item { Text("Histórico", style = MaterialTheme.typography.displaySmall); Text("Tudo o que você já conquistou.", color = MaterialTheme.colorScheme.outline) }
        item { Card(shape = RoundedCornerShape(22.dp), colors = CardDefaults.cardColors(containerColor = Mint)) { Row(Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) { Icon(Icons.Default.AccountBalanceWallet, null, tint = Moss); Spacer(Modifier.width(12.dp)); Column { Text("R$ 8.712,00", style = MaterialTheme.typography.headlineSmall); Text("recebidos este ano") } } } }
        items(listOf("29 set · Casa da Célia" to "R$ 135,00", "27 set · Feira da Vila" to "R$ 150,00", "25 set · Casa da Dona Rita" to "R$ 156,00")) { (job, value) -> JobRow("Concluído", job, "Pagamento confirmado", value) }
    }
}

@Composable private fun ProfileScreen(modifier: Modifier) {
    Column(modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
        Text("Meu perfil", style = MaterialTheme.typography.displaySmall)
        Row(verticalAlignment = Alignment.CenterVertically) { Box(Modifier.size(64.dp).clip(CircleShape).background(Moss), contentAlignment = Alignment.Center) { Text("M", color = Color.White, style = MaterialTheme.typography.titleLarge) }; Spacer(Modifier.width(14.dp)); Column { Text("Marina Silva", style = MaterialTheme.typography.titleLarge); Text("Profissional verificada", color = Moss) } }
        listOf(Icons.Default.Badge to "Dados profissionais", Icons.Default.Notifications to "Notificações", Icons.Default.HelpOutline to "Ajuda e suporte").forEach { (icon, label) -> Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(MaterialTheme.colorScheme.surface).clickable { }.padding(18.dp), verticalAlignment = Alignment.CenterVertically) { Icon(icon, null, tint = Moss); Spacer(Modifier.width(14.dp)); Text(label, Modifier.weight(1f), style = MaterialTheme.typography.titleMedium); Icon(Icons.Default.ChevronRight, null, tint = MaterialTheme.colorScheme.outline) } }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable private fun JornaleraPreview() { JornaleraTheme { JornaleraApp() } }
