package br.senai.sp.jandira.touccanuser.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
                                text = "Sobre nós",
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
                    if (sobreNosState.value) UserInfo()
                    else HistoryUser()
                }
            }
        }
    }
}

@Composable
fun UserInfo(){
    OutlinedTextField(value = "Sobre Nós", onValueChange = {}, enabled = false)
    OutlinedTextField(value = "Sobre Nós", onValueChange = {}, enabled = false)
}

@Composable
fun HistoryUser(){
    OutlinedTextField(value = "Feedback", onValueChange = {}, enabled = false)
    OutlinedTextField(value = "Feedback", onValueChange = {}, enabled = false)
}