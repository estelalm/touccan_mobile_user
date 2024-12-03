package br.senai.sp.jandira.touccanuser.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.UserPreferences
import br.senai.sp.jandira.touccanuser.model.LoginResult
import br.senai.sp.jandira.touccanuser.model.ResultUserProfile
import br.senai.sp.jandira.touccanuser.model.UserPerfil
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun validatePassword(password: String): Boolean{
    val regex = """^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*()_+{}\[\]:;"'<>,.?/\\|`~]).{8,}$""".toRegex()
    return regex.matches(password)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Seguranca(navController: NavHostController, context: Context) {

    Surface(modifier = Modifier.background(Color(0xffEBEBEB))) {
        Scaffold(
            containerColor = Color(0xFFEBEBEB),
            topBar = {
                br.senai.sp.jandira.touccanuser.utility.TopAppBar(navController, context)
            },
            bottomBar = {
                br.senai.sp.jandira.touccanuser.utility.BottomAppBar(navController, context)
            }
        ) { innerpadding ->

            val perfilUsuario = remember { mutableStateOf(UserPerfil()) }
            var isErrorState = remember {
                mutableStateOf(false)
            }
            var messageErrorState = remember {
                mutableStateOf("")
            }


            val userPreferences = UserPreferences(context)
            val userIdFlow = userPreferences.userId.collectAsState(initial = 0)

            Column(
                modifier = Modifier
                    .padding(innerpadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
                    Image(
                        painterResource(R.drawable.seta_voltar), "",
                        modifier = Modifier.height(40.dp).clickable { navController.popBackStack() }
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp, bottom = 50.dp)
                ) {
                    Text(
                        "Segurança",
                        fontFamily = Inter,
                        fontWeight = FontWeight.Black,
                        fontStyle = FontStyle.Italic,
                        color = Color.Black,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Box(
                        modifier = Modifier
                            .background(Color(MainOrange.value))
                            .height(1.dp)
                            .width(170.dp)
                    )
                    Text(
                        "Altere a senha a qualquer momento",
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                var atualState = remember {
                    mutableStateOf("")
                }
                var novaSenhaState = remember {
                    mutableStateOf("")
                }
                var confirmarState = remember {
                    mutableStateOf("")
                }


                Card(
                    modifier = Modifier.height(360.dp).width(300.dp).padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xA6C7C2C2))
                ) {
                    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
                        Text(
                            "Senha atual",
                            fontFamily = Inter,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        TextField(value = atualState.value, onValueChange = {atualState.value = it},
                            colors = TextFieldDefaults.colors(
                                disabledContainerColor = Color.Transparent,
                                focusedContainerColor  = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                cursorColor = MainOrange,
                                focusedIndicatorColor = Color.Black,
                                unfocusedIndicatorColor = Color.Black,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            "Nova senha",
                            color = Color.Black,
                            fontFamily = Inter,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        TextField(value = novaSenhaState.value, onValueChange = {
                            novaSenhaState.value = it
                            if(!validatePassword(it))
                                isErrorState.value = true
                            messageErrorState.value = "A senha deve conter no mínimo 1 letra maíuscula, 1 número, 1 caractere especial (@, !, $, etc) e 8 dígitos"

                        },
                            colors = TextFieldDefaults.colors(
                                disabledContainerColor = Color.Transparent,
                                focusedContainerColor  = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Black,
                                unfocusedIndicatorColor = Color.Black,
                                cursorColor = MainOrange,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            "Confirmar senha",
                            color = Color.Black,
                            fontFamily = Inter,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        TextField(value = confirmarState.value, onValueChange = {confirmarState.value = it},
                            colors = TextFieldDefaults.colors(
                                disabledContainerColor = Color.Transparent,
                                focusedContainerColor  = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Black,
                                unfocusedIndicatorColor = Color.Black,
                                cursorColor = MainOrange,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            )
                        )

                    }

                }

                Button(
                    onClick = {

                        val callUserPerfil = userIdFlow.value?.let { userId ->
                            RetrofitFactory()
                                .getUserService()
                                .getUserById(userId)
                        }

                        callUserPerfil?.enqueue(object : Callback<ResultUserProfile> {
                            override fun onResponse(call: Call<ResultUserProfile>, response: Response<ResultUserProfile>) {
                                if (response.isSuccessful) {
                                    val responseBody = response.body()
                                    if (responseBody != null) {
                                        Log.i("response perfil usuario", responseBody.toString())
                                        perfilUsuario.value = responseBody.usuario
                                    } else {
                                        Log.w("response perfil usuario", "Corpo da resposta é nulo")
                                    }
                                } else {
                                    Log.w("response perfil usuario", "Resposta não foi bem-sucedida: ${response.errorBody()?.string()}")
                                }
                            }

                            override fun onFailure(call: Call<ResultUserProfile>, t: Throwable) {
                                Log.e("Falhou!!!", t.message.orEmpty())
                            }
                        })

                        if (novaSenhaState.value == "") {
                            Toast.makeText(
                                context,
                                "Todos os valores devem ser preenchidos",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            if (perfilUsuario.value.senha == atualState.value) {

                                if (confirmarState.value == novaSenhaState.value) {

                                    perfilUsuario.value.senha = novaSenhaState.value
                                    val callUserPerfil = userIdFlow.value?.let {
                                        RetrofitFactory()
                                            .getUserService()
                                            .updateUser(perfilUsuario.value, it)
                                    }

                                    if (callUserPerfil != null) {
                                        callUserPerfil.enqueue(object : Callback<UserPerfil> {
                                            override fun onResponse(
                                                call: Call<UserPerfil>,
                                                res: Response<UserPerfil>
                                            ) {
                                                Log.i("response atualizar", res.toString())
                                            }

                                            override fun onFailure(
                                                p0: Call<UserPerfil>,
                                                t: Throwable
                                            ) {
                                                Log.i("Falhou!!!", t.toString())
                                            }
                                        })
                                    }
                                }

                            }
                        }
                    },
                    modifier = Modifier
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MainOrange
                    )
                ) {
                    Text(
                        text = "Atualizar senha",
                        color = Color.White,
                        fontFamily = Inter,
                        fontSize = 18.sp,
                    )
                }
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "Esqueceu sua senha?",
                        fontSize = 14.sp,
                        color = Color(0xff515151),
                        fontFamily = Inter,
                        lineHeight = 10.sp
                    )
                }

            }


        }
    }
}
