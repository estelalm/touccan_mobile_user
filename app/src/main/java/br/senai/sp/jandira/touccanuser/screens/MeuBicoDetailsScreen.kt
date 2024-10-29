package br.senai.sp.jandira.touccanuser.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.ui.theme.Inter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeuBicoDetails() {


    val mainOrange = 0xffF07B07


    Scaffold (
        containerColor = Color(0xFFEBEBEB),
        topBar = {
           // br.senai.sp.jandira.touccanuser.utility.TopAppBar(navController)
        },
        bottomBar = {
           // br.senai.sp.jandira.touccanuser.utility.BottomAppBar(navController)
        }
    ) { innerpadding ->

        Column (
            modifier = Modifier.fillMaxSize().padding(innerpadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Card (
                modifier = Modifier.width(300.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0x75D9D9D9)
                )
            ){
                Column (
                    modifier = Modifier.padding(12.dp)
                ){

                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row (verticalAlignment = Alignment.CenterVertically){

                            Icon(painter = painterResource(R.drawable.person), contentDescription = "", tint = Color(0xff7E7E7E))
                            Text("Empresa 1",
                                color = Color(0xff504D4D),
                                fontFamily = Inter,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(start = 6.dp))
                        }
                        Row {
                            Text("Dificuldade: ",
                                color = Color(0xff7E7E7E),
                                fontFamily = Inter,
                                fontWeight = FontWeight.SemiBold)
                            Text("Baixa",
                                color = Color(0xff106B16),
                                fontFamily = Inter,
                                fontWeight = FontWeight.SemiBold)
                        }
                    }
                    Column (
                        modifier = Modifier.padding(start = 12.dp)
                    ){

                        Text("Esse estabelecimento tem o foco de ajudar pessoas a blabla",
                            color = Color(0xff736C6C),
                            fontFamily = Inter,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.padding(vertical = 12.dp))
                        Text("Assistente Administrativo",
                            color = Color(0xff464646),
                            fontFamily = Inter,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(vertical = 12.dp))
                        Text("Trabalho focado em organizar e atender clientes com intuito de disponibilidade hoje!",
                            color = Color(0xff736C6C),
                            fontFamily = Inter,
                            fontWeight = FontWeight.Light)

                        Text("Av. Diniz, 57  Jandira-SP",
                            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                            textAlign = TextAlign.End,
                            color = Color(0xff7E7E7E),
                            fontFamily = Inter,
                            fontWeight = FontWeight.SemiBold)

                        Column (){
                            Text("Início: 08:30",
                                color = Color(0xff464646),
                                fontFamily = Inter,
                                fontWeight = FontWeight.Black)
                            Text("Término: 17:30",
                                color = Color(0xff464646),
                                fontFamily = Inter,
                                fontWeight = FontWeight.Black)
                            Row {
                                Text("Pagamento: ",
                                    color = Color(0xff464646),
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.Black)
                                Text("R$200,00",
                                    color = Color(0xff378420),
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.Black)
                            }
                        }
                    }
                }
            }

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(mainOrange)
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Text("Começar um Bate-papo", fontFamily = Inter, fontWeight = FontWeight.Black)
            }
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(mainOrange)
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Trabalho finalizado", fontFamily = Inter, fontWeight = FontWeight.Black)
            }


        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MeuBicoDetailsPreview() {
    MeuBicoDetails()
}