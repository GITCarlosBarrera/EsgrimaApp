package org.example.project.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomOutlinedTextField(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    icon: ImageVector,
    isPassword: Boolean = false,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier,
        placeholder = { Text(text = placeholder) },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Ocultar" else "Mostrar"
                    )
                }
            }
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.LightGray,
            unfocusedContainerColor = Color.White,
            unfocusedPlaceholderColor = Color.LightGray,
            unfocusedLeadingIconColor = Color.Gray,
            focusedContainerColor = Color.White,
            unfocusedTrailingIconColor = Color.Gray
        )
    )
}

@Composable
fun ElevatedButtonCard(
    title: String,
    subtitle: String,
    icon: Painter,
    iconColor: Color,
    onClick: () -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 18.dp).widthIn(min = 500.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start)
        ) {
            Surface(
                modifier = Modifier.size(60.dp),
                color = iconColor.copy(alpha = 0.5f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Image(
                    modifier = Modifier.padding(6.dp),
                    painter = icon,
                    contentDescription = null,
                )
            }

            Column {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                if (subtitle.isNotBlank()) {
                    Text(
                        text = subtitle,
                        fontSize = 20.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun BackButton(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onBack,
        modifier = modifier.padding(16.dp).size(56.dp),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2F)
        )
    ) {
        Icon(
            imageVector = Icons.Default.ChevronLeft,
            contentDescription = "Volver",
            modifier = Modifier.size(32.dp)
        )
    }
}