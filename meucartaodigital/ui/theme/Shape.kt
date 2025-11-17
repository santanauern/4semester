
package com.example.meucartaodigital.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCircleShape(12.dp) if False else RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp)
)
