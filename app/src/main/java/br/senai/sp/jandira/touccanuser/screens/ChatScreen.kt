package br.senai.sp.jandira.touccanuser.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.MainActivity
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.UserPreferences
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chat(navController: NavHostController, MainActivity: Context) {

    val userPreferences = UserPreferences(MainActivity)
    val userIdFlow = userPreferences.userId.collectAsState(initial = null)

    Surface(modifier = Modifier.background(Color(0xffEBEBEB))) {

        Scaffold(
            containerColor = Color(0xFFEBEBEB),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFEBEBEB)
                    ),
                    title = { Text("") },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(
                                tint = Color.Black,
                                painter = painterResource(R.drawable.seta_voltar),
                                contentDescription = "Seta para a esquerda: Voltar",
                            )
                        }
                    },
                    actions = {
                            IconButton(onClick = {
                                navController.navigate("perfilUsuario/${userIdFlow.value}")
                            }) {
                                Icon(
                                    tint = Color.Black,
                                    painter = painterResource(R.drawable.person),
                                    contentDescription = "Perfil: Ícone de pessoa",
                                )
                            }
                    }
                )
            }
        ){innerpadding ->

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
                        .padding(top = 20.dp, bottom = 20.dp)
                ) {
                    Text(
                        "Mercado Bom Lugar",
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
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
                Column (modifier = Modifier.padding(horizontal = 24.dp)){
                    ElevatedCard(modifier = Modifier.fillMaxHeight(0.85F)
                        .fillMaxWidth().padding(bottom = 20.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        LazyColumn (modifier = Modifier.fillMaxSize().padding(vertical =24.dp, horizontal = 12.dp)) {

                            item {
                                MessageUser()
                            }
                            item {
                                MessageClient()
                            }
                        }
                    }

                    ElevatedCard(modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White)) {
                        Row (modifier = Modifier.fillMaxWidth().padding(horizontal = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween){
                            TextField(
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                value = "",
                                onValueChange = {})
                            Image(painterResource(R.drawable.send),"Enviar mensagem")
                        }

                    }

                }


            }


        }

    }

    
}

@Composable
fun MessageUser() {
    Row (modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp), horizontalArrangement = Arrangement.End
    ){

        Card (
            colors = CardDefaults.cardColors(
                containerColor = Color(0xffFFD2A5)
            ),
            modifier = Modifier.padding(horizontal = 10.dp).widthIn(max = 240.dp)
        ) {
            Text("BlablablablaBblabla",modifier = Modifier.padding(10.dp))
        }
        Card(modifier = Modifier.size(40.dp), shape = RoundedCornerShape(50.dp)){

        }
    }
}
@Composable
fun MessageClient() {
    Row (modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp), horizontalArrangement = Arrangement.Start
    ){
        Card(modifier = Modifier.size(40.dp), shape = RoundedCornerShape(50.dp)){

        }
        Card (
            colors = CardDefaults.cardColors(
                containerColor = Color(0xff9BA9B0)
            ),
            modifier = Modifier.padding(horizontal = 10.dp).widthIn(max = 240.dp)
        ) {
            Text("Blablablabla",modifier = Modifier.padding(10.dp))
        }

    }
}