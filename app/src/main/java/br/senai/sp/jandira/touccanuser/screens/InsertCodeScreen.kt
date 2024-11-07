package br.senai.sp.jandira.touccanuser.screens

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange

@Composable
fun InsertCode() {

    Surface (modifier = Modifier.background(Color(0xffEBEBEB))){

        Column (
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(R.drawable.logo_touccan),
                contentDescription = "Logo da touccan",
                modifier = Modifier.height(150.dp),
                contentScale = ContentScale.FillHeight
            )
            Spacer(modifier = Modifier.height(50.dp))
            ElevatedCard(
                modifier = Modifier.height(300.dp).width(300.dp)
            ) {
                Column(modifier = Modifier.padding(vertical = 24.dp, horizontal = 20.dp)){
                    Text(
                        text = "Digite o código enviado em seu e-mail",
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontFamily = Inter,
                        fontSize = 22.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                "Não recebeu o código?",
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                fontFamily = Inter,
                fontSize = 16.sp
            )
            Button(
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MainOrange
                ),
                modifier = Modifier.padding(12.dp),
                onClick = {}
            ) {
                Text(
                    "Enviar novamente",
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontFamily = Inter,
                    fontSize = 18.sp
                )
            }

        }

    }

}


@Preview (showSystemUi = true, showBackground = true)
@Composable
private fun InsertCodePrev() {
    InsertCode()
}