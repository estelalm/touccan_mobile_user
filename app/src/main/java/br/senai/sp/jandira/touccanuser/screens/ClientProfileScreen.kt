package br.senai.sp.jandira.touccanuser.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.touccanuser.R

@Composable
fun ClientProfile(){
    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFEBEBEB)) {
        Column (
            modifier = Modifier.fillMaxWidth()
        ) {
            Row (
                modifier = Modifier.fillMaxWidth()
                    .height(100.dp)
                    .padding(top = 4.dp, start = 14.dp, end = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(R.drawable.seta_voltar),
                    contentDescription = "",
                    modifier = Modifier.width(30.dp)
                )
                Image(
                    painter = painterResource(R.drawable.logo_touccan),
                    contentDescription = "",
                    modifier = Modifier.size(height = 86.dp, width = 150.dp),
                    contentScale = ContentScale.FillBounds
                )
            }
            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Card(
                    modifier = Modifier.size(170.dp),
                    shape = CircleShape,
                    border = BorderStroke(5.dp, Color(0xffF07B07)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 9.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.cliente_pfp),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(Modifier.height(10.dp))
                Text(
                    "Mercado Bom Lugar",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                    )
                Spacer(Modifier.height(30.dp))
                Row (
                    modifier = Modifier.fillMaxSize(),
                        //.padding(top = 4.dp, start = 14.dp, end = 14.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(Color.Transparent)
                        ) {
                        Text(
                            "Sobre nós",
                            color = Color.Black
                        )
                    }
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(Color.Transparent)
                    ) {
                        Text(
                            "Feedback",
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SobreNos(){
    OutlinedTextField(value = "teste", onValueChange = {}, enabled = false)
    OutlinedTextField(value = "teste", onValueChange = {}, enabled = false)
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ClientProfilePreview(){
    Surface (modifier = Modifier.fillMaxSize(), color = Color(0xFFEBEBEB)) {
      ClientProfile()
    }
}
