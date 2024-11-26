package br.senai.sp.jandira.touccanuser.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.MainActivity
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navController: NavHostController, mainActivity: MainActivity) {

    Surface(modifier = Modifier.background(Color(0xffEBEBEB))) {
        Scaffold(
            containerColor = Color(0xFFEBEBEB),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFEBEBEB)
                    ),
                    navigationIcon = {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .height(100.dp)
                                .width(170.dp)
                        ) {
                            Icon(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 10.dp),
                                painter = painterResource(R.drawable.logo_touccan),
                                contentDescription = "Desenho de um, com o texto Touccan ao lado, a logo do aplicativo",
                            )
                        }

                    },
                    title = {
                    },
                    actions = {
                        Row(horizontalArrangement = Arrangement.End) {
                            IconButton(onClick = {}) {
                                Icon(
                                    painter = painterResource(R.drawable.configuracoes),
                                    contentDescription = "Configurações: Ícone de engrenagem",
                                )
                            }
                            IconButton(onClick = {}) {
                                Icon(
                                    painter = painterResource(R.drawable.carteira),
                                    contentDescription = "Carteira: Ícone de carteira",
                                )
                            }
                            IconButton(onClick = {

                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.person),
                                    contentDescription = "Perfil: Ícone de pessoa",
                                )
                            }
                        }
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = Color(0xFFEBEBEB),
                    content = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {


                            IconButton(
                                onClick = {}
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.home),
                                    contentDescription = "Home: Ícone de casa",
                                )
                            }
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
                                    modifier = Modifier.size(35.dp),
                                    painter = painterResource(R.drawable.notificacao),
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
                                    painter = painterResource(R.drawable.pesquisa),
                                    contentDescription = "Home: Ícone de casa",
                                )
                            }
                        }
                    }
                )
            }
        ) { innerpadding ->


            Column(
                modifier = Modifier
                    .padding(innerpadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 50.dp)
                ) {
                    Text(
                        "Cofrinho",
                        fontFamily = Inter,
                        fontWeight = FontWeight.Black,
                        fontStyle = FontStyle.Italic,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Box(
                        modifier = Modifier
                            .background(Color(MainOrange.value))
                            .height(1.dp)
                            .width(160.dp)
                    )
                }

                ElevatedCard (
                    modifier = Modifier.clickable {

                    }.padding(horizontal = 18.dp, vertical = 16.dp),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 3.dp
                    )){
                    Row (modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth()
                        .background(Color.White)){
                        Card (modifier = Modifier.fillMaxHeight().width(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MainOrange),
                            shape = RectangleShape
                        ){}
                        Row (verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth().padding(start = 12.dp, end = 4.dp)){
                            Image(painterResource(R.drawable.person), "Descricao",
                                modifier = Modifier.size(46.dp))
                            Column (
                                modifier = Modifier
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                                    .fillMaxHeight()
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.Center
                            ){
                                Text("Informações da conta", fontFamily = Inter,
                                    color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                Text("Veja as informações da conta, como telefone e endereço de e-mail", fontFamily = Inter,
                                    color = Color.Gray, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                            }
                        }

                    }

                }

                ElevatedCard (
                    modifier = Modifier.clickable {

                    }.padding(horizontal = 18.dp, vertical = 16.dp),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 3.dp
                    )){
                    Row (modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth()
                        .background(Color.White)){
                        Card (modifier = Modifier.fillMaxHeight().width(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MainOrange),
                            shape = RectangleShape
                        ){}
                        Row (verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth().padding(start = 12.dp, end = 4.dp)){
                            Image(painterResource(R.drawable.senha), "Descricao",
                                modifier = Modifier.size(46.dp))
                            Column (
                                modifier = Modifier
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                                    .fillMaxHeight()
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.Center
                            ){
                                Text("Segurança", fontFamily = Inter,
                                    color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                Text("Gerencie a segurana da sua conta", fontFamily = Inter,
                                    color = Color.Gray, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                            }
                        }
                    }
                }

                ElevatedCard (
                    modifier = Modifier.clickable {

                    }.padding(horizontal = 18.dp, vertical = 16.dp),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 3.dp
                    )){
                    Row (modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth()
                        .background(Color.White)){
                        Card (modifier = Modifier.fillMaxHeight().width(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MainOrange),
                            shape = RectangleShape
                        ){}
                        Row (verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth().padding(start = 12.dp, end = 4.dp)){
                            Image(painterResource(R.drawable.headphone), "Descricao",
                                modifier = Modifier.size(40.dp))
                            Column (
                                modifier = Modifier
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                                    .fillMaxHeight()
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.Center
                            ){
                                Text("Suporte", fontFamily = Inter,
                                    color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                Text("Entre em contato conosco de qualquer lugar", fontFamily = Inter,
                                    color = Color.Gray, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                            }
                        }

                    }

                }

                ElevatedCard (
                    modifier = Modifier.clickable {

                    }.padding(horizontal = 18.dp, vertical = 16.dp),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 3.dp
                    )){
                    Row (modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth()
                        .background(Color.White)){
                        Card (modifier = Modifier.fillMaxHeight().width(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MainOrange),
                            shape = RectangleShape
                        ){}
                        Row (verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth().padding(start = 12.dp, end = 4.dp)){
                            Image(painterResource(R.drawable.logo_preto), "Descricao",
                                modifier = Modifier.size(60.dp))
                            Column (
                                modifier = Modifier
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                                    .fillMaxHeight()
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.Center
                            ){
                                Text("Sobre nós", fontFamily = Inter,
                                    color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                Text("Veja algumas informações sobre nossa comunidade", fontFamily = Inter,
                                    color = Color.Gray, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                            }
                        }

                    }

                }


                //fim dos cards
            }

        }




    }
}