package br.senai.sp.jandira.touccanuser.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun MeuCartao(navController: NavHostController, idUser: String?, mainActivity: MainActivity) {

    var cartaoState = remember {
        mutableStateOf(true)
    }

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
                    modifier = Modifier.fillMaxWidth().padding(top = 80.dp, bottom = 50.dp)
                ) {
                    Text(
                        "Meu cartão",
                        fontFamily = Inter,
                        fontWeight = FontWeight.ExtraBold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Box(
                        modifier = Modifier
                            .background(Color(MainOrange.value))
                            .height(1.dp)
                            .width(160.dp)
                    )
                    if(cartaoState.value){
                        Text(
                            modifier = Modifier.width(180.dp).padding(4.dp),
                            text = "Você pode ter no máximo UM cartão cadastrado",
                            textAlign = TextAlign.Center,
                            fontFamily = Inter,
                            fontStyle = FontStyle.Italic,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray
                        )

                    }

                }

                if(cartaoState.value){

                    Card (
                        modifier = Modifier.height(60.dp).width(300.dp)
                    ) {
                        Row (
                            modifier = Modifier.fillMaxSize().padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Box(
                                modifier = Modifier.height(20.dp).width(40.dp).clip(RectangleShape)
                            ){
                                Image(painterResource(R.drawable.cliente_pfp),
                                    "",
                                    contentScale = ContentScale.FillBounds)
                            }

                            Column (modifier = Modifier.padding(start= 12.dp)){
                                Text("Apelido · Débito",
                                    fontFamily = Inter,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,)
                                Text("••••7865",
                                    fontFamily = Inter,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Gray
                                )
                            }
                        }


                    }

                    Button(
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MainOrange
                        ),
                        modifier = Modifier.padding(18.dp),
                        onClick = {}
                    ) {
                        Text(
                            "Editar cartão",
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            fontFamily = Inter,
                            fontSize = 18.sp
                        )
                    }

                }else{

                    Image(
                        painter = painterResource(R.drawable.logo_sombra),
                        contentDescription = "",
                        modifier = Modifier.height(200.dp))

                    Text(
                        modifier = Modifier.width(200.dp).padding(vertical = 30.dp),
                        text = "Você não possui nenhum cartão cadastrado...",
                        textAlign = TextAlign.Center,
                        fontFamily = Inter,
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
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
                            "Adicionar cartão",
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            fontFamily = Inter,
                            fontSize = 18.sp
                        )
                    }


                }

            }




        }
    }

}