package br.senai.sp.jandira.touccanuser.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.MainActivity
import br.senai.sp.jandira.touccanuser.model.Bico
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Notifications(navController: NavHostController, mainActivity: MainActivity) {

    var notificationList = remember {
        mutableStateOf(listOf<Bico>())
    }
    var isLoadingState = remember {
        mutableStateOf(true)
    }
    var errorState = remember {
        mutableStateOf(false)
    }



    Scaffold(
        containerColor = Color(0xFFEBEBEB),
        topBar = {
           // br.senai.sp.jandira.touccanuser.utility.TopAppBar(navController)
        },
        bottomBar = {
            //br.senai.sp.jandira.touccanuser.utility.BottomAppBar(navController)
        }
    ) { innerpadding ->

        Column(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column (horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Notificações",
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 20.sp
                )
                Box(
                    modifier = Modifier
                        .background(Color(MainOrange.value))
                        .height(1.dp)
                        .width(200.dp)

                )
            }

            LazyColumn (modifier = Modifier.padding(top = 24.dp).fillMaxSize()) {
                items(5){
                    NotificationCard()
                }

            }

        }

    }
}

@Composable
fun NotificationCard() {


            ElevatedCard (modifier = Modifier.clickable { }.padding(horizontal = 24.dp, vertical = 8.dp),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 8.dp
                )){
                Row (modifier = Modifier
                    .height(90.dp)
                    .fillMaxWidth()
                    .background(Color.White)){
                    Card (
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MainOrange,
                        ),
                        shape = RectangleShape
                    ){}
                    Row (verticalAlignment = Alignment.CenterVertically){

                        Icon(Icons.Default.Warning, contentDescription = "", modifier = Modifier.padding(start = 20.dp, end = 12.dp).size(40.dp))
                        Column (
                            modifier = Modifier
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ){
                            Text("Você recebeu uma denúncia.",
                                    fontFamily = Inter,
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.Bold)
                            Text("Algum conteúdo explicando a denúncia, clique para ver o conteúdo")
                        }
                    }

                }
            }



}
