package org.example.project.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AccountCircle
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
import esgrimaapp.composeapp.generated.resources.esgrima_app_logo
import org.jetbrains.compose.resources.painterResource

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateLogin: () -> Unit
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
                    painter = painterResource(Res.drawable.esgrima_app_logo),
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
                RegisterCard(
                    onRegisterSuccess = onRegisterSuccess,
                    onNavigateLogin = onNavigateLogin
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
fun RegisterCard(
    onRegisterSuccess: () -> Unit,
    onNavigateLogin: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var passwd by remember { mutableStateOf("") }
    var confirmarPasswd by remember { mutableStateOf("") }
    var errorRegister by remember { mutableStateOf(false) }

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
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
                    text = "Registrate",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Nombre",
                    fontWeight = FontWeight.Bold
                )
                CustomOutlinedTextField(
                    text = nombre,
                    onTextChange = { nombre = it },
                    placeholder = "Tu nombre",
                    icon = Icons.Outlined.AccountCircle
                )
                Text(
                    text = "Email",
                    fontWeight = FontWeight.Bold
                )
                CustomOutlinedTextField(
                    text = email,
                    onTextChange = { email = it },
                    placeholder = "Tu email",
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
                Text(
                    text = "Confirmar contraseña",
                    fontWeight = FontWeight.Bold
                )
                CustomOutlinedTextField(
                    text = confirmarPasswd,
                    onTextChange = { confirmarPasswd = it },
                    placeholder = "Confirma tu contraseña",
                    icon = Icons.Outlined.Lock,
                    isPassword = true
                )

                if (errorRegister) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Error al registrarse",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (nombre.isNotBlank() && email.isNotBlank() && passwd.isNotBlank() && confirmarPasswd.isNotBlank()) {
                            if (passwd == confirmarPasswd) {
                                onRegisterSuccess()
                            } else {
                                errorRegister = true
                            }
                        } else {
                            errorRegister = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Acceder")
                }

                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.LightGray
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "¿Ya tienes cuenta?",
                        color = Color.LightGray
                    )
                    Text(
                        text = "Inicia sesión aquí",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable { onNavigateLogin() }
                    )
                }
            }
        }
    }
}