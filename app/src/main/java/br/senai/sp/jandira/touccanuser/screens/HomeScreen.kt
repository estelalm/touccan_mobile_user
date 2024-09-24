package br.senai.sp.jandira.touccanuser.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.ui.theme.Inter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {



    Scaffold (
        containerColor = Color(0xFFEBEBEB),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFEBEBEB)
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {},
                        modifier = Modifier.height(100.dp).width(170.dp)) {
                        Icon(
                            modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
                            painter = painterResource(R.drawable.logo_touccan),
                            contentDescription = "Desenho de um, com o texto Touccan ao lado, a logo do aplicativo",
                        )
                    }

                },
                title = {
                },
                actions = {
                    Row (horizontalArrangement = Arrangement.End){
                        IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.configuracoes),
                            contentDescription = "Configurações: Ícone de engrenagem",
                        )
                    }
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(R.drawable.carteira),
                                contentDescription = "Configurações: Ícone de engrenagem",
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(R.drawable.person),
                                contentDescription = "Configurações: Ícone de engrenagem",
                            )
                        } }
                }

            )
        },
        bottomBar = {
            BottomAppBar (
                containerColor = Color(0xFFEBEBEB)
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround) {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.historico),
                            contentDescription = "Home: Ícone de casa",
                        )
                    }

                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.chat),
                            contentDescription = "Home: Ícone de casa",
                        )
                    }

                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            modifier = Modifier.size(35.dp),
                            painter = painterResource(R.drawable.notificacao),
                            contentDescription = "Home: Ícone de casa",
                        )
                    }

                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.pesquisa),
                            contentDescription = "Home: Ícone de casa",
                        )
                    }

                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.home),
                            contentDescription = "Home: Ícone de casa",
                        )
                    }
                }

            }
        }
    ) { innerpadding ->
        Column {
            Row (modifier = Modifier.fillMaxWidth()){
                Button(onClick = {}) {

                }
            }
            LazyColumn (contentPadding = innerpadding){
                items(2){
                    AnuncioCard()
                }

            }
        }

    }

}

@Composable
fun AnuncioCard(modifier: Modifier = Modifier) {

    val grayColor = 0xff6D6D6D
    val greenColor = 0xff106B16

    Row (modifier = Modifier.padding(16.dp)){
        Card (
            modifier = Modifier.size(35.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Black
            ),
            shape = RoundedCornerShape(50.dp)
        ){  }
        Column (
            modifier = modifier.padding(vertical = 8.dp, horizontal = 6.dp)
        ){

            Text(
                text = "Empresa 1",
                modifier = Modifier.padding(bottom = 10.dp, start = 4.dp),
                fontFamily = Inter,
                fontWeight = FontWeight.Normal
            )
            Card {
                Row (modifier = Modifier.height(180.dp).fillMaxWidth().background(Color.White)){
                    ElevatedCard (
                        modifier = Modifier.fillMaxHeight().width(10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xffF07B07),
                        ),
                        shape = RectangleShape,
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 20.dp
                        )
                    ){}
                    Column (
                        modifier = Modifier.padding(6.dp).fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceAround
                    ){
                        Text(
                            text = "Assistente Admnistrativo",
                            fontFamily = Inter,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(grayColor)
                        )
                        Text(
                            text = "Trabalho focado em organizar e atender clientes com intuito de disponibilidade hoje!",
                            fontFamily = Inter,
                            fontWeight = FontWeight.Light
                        )
                        Text(
                            text = "Jandira - SP",
                            fontFamily = Inter,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(grayColor)
                        )
                        Row {
                            Text(
                                text = "Dificuldade: ",
                                fontFamily = Inter,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(grayColor)
                            )
                            Text(
                                text = "Baixa",
                                fontFamily = Inter,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(greenColor)
                            )
                        }

                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ){ Button(
                            modifier = Modifier.height(32.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffF07B07)),
                            onClick = {}
                        ) {
                            Text(
                                text ="Candidatar-se",
                                fontFamily = Inter,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 12.sp
                            )
                        } }
                    }
                }
            }

        }
    }

}


@Preview (showSystemUi = true, showBackground = true)
@Composable
private fun HomePreview() {
    Surface (modifier = Modifier.fillMaxSize(), color = Color(0xFFEBEBEB)) {
        Home()
    }

}