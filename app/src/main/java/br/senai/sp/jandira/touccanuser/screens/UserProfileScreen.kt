package br.senai.sp.jandira.touccanuser.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.MainActivity
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.model.ResultClientProfile
import br.senai.sp.jandira.touccanuser.model.ResultUserProfile
import br.senai.sp.jandira.touccanuser.model.UserPerfil
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun UserProfile(navController: NavHostController, usuarioId: String, mainActivity: MainActivity) {
    var perfilUsuario = remember{
        mutableStateOf(UserPerfil())
    }
    val idUsuario = usuarioId.toInt()
    val callUserPerfil = RetrofitFactory()
        .getUserService()
        .getUserById(idUsuario)

    callUserPerfil.enqueue(object: Callback<ResultUserProfile> {
        override fun onResponse(p0: Call<ResultUserProfile>, p1: Response<ResultUserProfile>) {
            Log.i("response TelaC", p1.body()!!.toString())
            perfilUsuario.value = p1.body()!!.usuario
        }

        override fun onFailure(p0: Call<ResultUserProfile>, p1: Throwable) {
            Log.i("Falhou!!!", p1.toString())
        }
    })

    var dataDate = perfilUsuario.value.data_nascimento



    var sobreNosState = remember{
        mutableStateOf(false)
    }

    var feedbackState = remember{
        mutableStateOf(true)
    }
    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFEBEBEB)) {
        Column (
            modifier = Modifier.fillMaxWidth()
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(top = 4.dp, start = 14.dp, end = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(R.drawable.seta_voltar),
                    contentDescription = "",
                    modifier = Modifier
                        .width(30.dp)
                        .clickable { navController.popBackStack() }
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
            ) {
                Card(
                    modifier = Modifier.size(170.dp),
                    shape = CircleShape,
                    border = BorderStroke(5.dp, Color(0xffF07B07)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 9.dp)
                ) {
                    //|| perfilUsuario.value.foto == null
                    AsyncImage(
                        model = "",
                        if (perfilUsuario.value.foto == "") "https://i.pinimg.com/564x/1e/f8/15/1ef8156889dba99417ff2b3a6d99988d.jpg"
                        else perfilUsuario.value.foto,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(Modifier.height(10.dp))
                Text(
                    text = perfilUsuario.value.nome,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
                Spacer(Modifier.height(30.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    //.padding(top = 4.dp, start = 14.dp, end = 14.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick = {
                            feedbackState.value = false
                            sobreNosState.value = true
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Sobre mim",
                                textAlign = TextAlign.Center,
                                fontFamily = Inter,
                                fontStyle = if (sobreNosState.value) {
                                    FontStyle.Italic
                                } else {
                                    FontStyle.Normal
                                },
                                fontWeight = FontWeight.SemiBold,
                                color = if (sobreNosState.value) {
                                    Color.Black
                                } else {
                                    Color(0xffC6C5C5)
                                }
                            )
                            if (sobreNosState.value) {
                                Box(
                                    modifier = Modifier
                                        .background(Color(0xffF07B07))
                                        .height(1.dp)
                                        .width(100.dp)

                                )
                            }
                        }
                    }
                    Button(
                        onClick = {
                            feedbackState.value = true
                            sobreNosState.value = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Feedback",
                                textAlign = TextAlign.Center,
                                fontFamily = Inter,
                                fontStyle = if (feedbackState.value) {
                                    FontStyle.Italic
                                } else {
                                    FontStyle.Normal
                                },
                                fontWeight = FontWeight.SemiBold,
                                color = if (feedbackState.value) {
                                    Color.Black
                                } else {
                                    Color(0xffC6C5C5)
                                }
                            )
                            if (feedbackState.value) {
                                Box(
                                    modifier = Modifier
                                        .background(Color(0xffF07B07))
                                        .height(1.dp)
                                        .width(100.dp)

                                )
                            }
                        }
                    }

                }
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (sobreNosState.value)
                        UserInfo()
                    else HistoryUser()
                }
            }
        }
    }
}

@Composable
fun UserInfo(){

    var editState = remember {
        mutableStateOf(false)
    }

    var formacaoState = remember{
        mutableStateOf("Ensino médio completo")
    }
    var bioState = remember{
        mutableStateOf("Maria é  Autonoma , com experiência em Redes Sociais. Formada em tecnologia pelo senai, ela se destaca em comunicaçao.")
    }
    var habilidadeState = remember{
        mutableStateOf("Comunicativa, bom trabalho em equipe e resiliente")
    }
    var disponibilidadeState = remember{
        mutableStateOf("Disponível agora")
    }

    Column (
        modifier = Modifier.padding(vertical = 24.dp, horizontal = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Row (modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 2.dp).fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically){
                Text("Formação: ",
                    fontFamily = Inter,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold)
                TextField(
                    value = formacaoState.value,
                    onValueChange = {},
                    enabled = editState.value,
                    textStyle = TextStyle(
                        fontFamily = Inter
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Row (modifier = Modifier.padding(horizontal = 10.dp).fillMaxSize()){
                Text("Biografia: ",
                    fontFamily = Inter,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 16.dp))
                TextField(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    value = bioState.value,
                    onValueChange = {},
                    enabled = editState.value,
                    textStyle = TextStyle(
                        fontFamily = Inter
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(65.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Row (modifier = Modifier.padding(horizontal = 10.dp).fillMaxSize()){
                Text("Habilidades: ",
                    fontFamily = Inter,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(vertical = 16.dp))
                TextField(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    value = habilidadeState.value,
                    onValueChange = {},
                    enabled = editState.value,
                    textStyle = TextStyle(
                        fontFamily = Inter
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Row (modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 2.dp).fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically){
                Text("Disponibilidade: ",
                    fontFamily = Inter,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold)
                TextField(
                    value = disponibilidadeState.value,
                    onValueChange = {},
                    enabled = editState.value,
                    textStyle = TextStyle(
                        fontFamily = Inter
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MainOrange
            ),
            modifier = Modifier.padding(12.dp).width(200.dp),
            onClick = {}
        ) {
            Text(
                "Editar currículo",
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                fontFamily = Inter,
                fontSize = 16.sp
            )
        }

    }


}

@Composable
fun HistoryUser(){

    LazyColumn {
        items(3){
            ElevatedCard (modifier = Modifier.clickable { }
                .padding(horizontal = 18.dp, vertical = 8.dp),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 3.dp
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
                    Column (
                        modifier = Modifier
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ){
                        Text("Empresa 1 - Assistente Admnistrativo.",
                            fontFamily = Inter,
                            fontWeight = FontWeight.Bold)
                        Text("Ótimo trabalho")
                        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp)){
                            val stars = 3
                            for (i in 1..5) {
                                if(i <= stars) Icon(Icons.Filled.Star,contentDescription = "", tint = Color(0xFFFFBC06))
                                else
                                    Icon(Icons.Filled.Star,contentDescription = "", tint = Color(0xFF504D4D))
                            }

                        }
                    }

                }
            }
        }
    }

}

@Preview (showBackground = true)
@Composable
private fun UserInfoPrev() {

    Surface (modifier = Modifier.padding(24.dp).height(600.dp), color = Color.DarkGray){
        HistoryUser()
    }

}