package org.example.project.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import esgrimaapp.composeapp.generated.resources.Res
import esgrimaapp.composeapp.generated.resources.logo_app
import esgrimaapp.composeapp.generated.resources.logo_app_white
import org.example.project.UserRole
import org.example.project.components.CustomOutlinedTextField
import org.jetbrains.compose.resources.painterResource

@Composable
fun LoginScreen(
    onLoginSuccess: (UserRole) -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Image(
                    painter = painterResource(Res.drawable.logo_app),
                    contentDescription = "App logo",
                    modifier = Modifier.size(80.dp)
                )
                Text(
                    text = "Esgrima App",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
                Text(
                    text = "Gestión de competiciones de esgrima",
                    fontSize = 18.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.padding(8.dp))
                LoginCard(
                    onLoginSuccess = onLoginSuccess,
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "© 2026 Esgrima App. Gestión de competiciones de esgrima",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun LoginCard(
    onLoginSuccess: (UserRole) -> Unit,
) {
    var usuario by remember { mutableStateOf("") }
    var passwd by remember { mutableStateOf("") }
    var errorLogin by remember { mutableStateOf(false) }

    ElevatedCard(
        modifier = Modifier
            .widthIn(max = 450.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Iniciar sesión",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Usuario",
                    fontWeight = FontWeight.Bold
                )
                CustomOutlinedTextField(
                    text = usuario,
                    onTextChange = { usuario = it },
                    placeholder = "Tu usuario",
                    icon = Icons.Outlined.Mail
                )
                Text(
                    text = "Contraseña",
                    fontWeight = FontWeight.Bold
                )
                CustomOutlinedTextField(
                    text = passwd,
                    onTextChange = { passwd = it },
                    placeholder = "Tu contraseña",
                    icon = Icons.Outlined.Lock,
                    isPassword = true
                )
                if (errorLogin) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Error al iniciar sesión",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.error
                    )
                } else {
                    Spacer(modifier = Modifier.padding(1.dp))
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (usuario == "admin" && passwd == "admin") {
                            onLoginSuccess(UserRole.ADMIN)
                        } else if (usuario.isNotBlank() && passwd.isNotBlank()) {
                            onLoginSuccess(UserRole.USER)
                        } else {
                            errorLogin = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Acceder")
                }
            }
        }
    }
}