package br.senai.sp.jandira.touccanuser.screens

import android.content.Context
import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.UserPreferences
import br.senai.sp.jandira.touccanuser.model.EmailReset
import br.senai.sp.jandira.touccanuser.model.EmailTouccan
import br.senai.sp.jandira.touccanuser.model.ResultUserProfile
import br.senai.sp.jandira.touccanuser.model.ResultUserProfileList
import br.senai.sp.jandira.touccanuser.model.TokenRes
import br.senai.sp.jandira.touccanuser.model.UserPerfil
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun InsertCode(navController: NavHostController, context: Context, emailUser: String) {

    Surface(modifier = Modifier.background(Color(0xffEBEBEB))) {


        val email = EmailReset(
            email = emailUser
        )

        var tokenState = remember { mutableStateOf(0) }
        var codeState = remember { mutableStateOf("") }
        var errorState = remember { mutableStateOf(false) }

        val sendToken = RetrofitFactory()
            .getUserService()
            .sendToken(email)

        sendToken.enqueue(object : Callback<TokenRes> {
            override fun onResponse(call: Call<TokenRes>, res: Response<TokenRes>) {
                Log.i("Response", res.body().toString())
                if (res.isSuccessful && res.body() != null) {
                    tokenState.value = res.body()!!.token ?: 0
                } else {
                    Log.i("Response Error", "Token inválido ou não recebido")
                }
            }

            override fun onFailure(call: Call<TokenRes>, t: Throwable) {
                Log.i("Falhou:", t.toString())
            }
        })

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
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
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                ),
                modifier = Modifier
                    .height(300.dp)
                    .width(300.dp)
            ) {
                Column(modifier = Modifier.padding(vertical = 24.dp, horizontal = 20.dp)) {
                    Text(
                        text = "Digite o código enviado em seu e-mail",
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontFamily = Inter,
                        fontSize = 22.sp
                    )

                    var userList = remember{
                        mutableStateOf(listOf<UserPerfil>())
                    }
                    var idUser = remember{
                        mutableStateOf(0)
                    }
                    Spacer(modifier = Modifier.height(50.dp))
                    OutlinedTextField(
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        ),
                        modifier = Modifier
                            .height(70.dp)
                            .fillMaxWidth(),
                        value = codeState.value,
                        trailingIcon = {
                            IconButton(onClick = {
                                if (tokenState.value.toString() == codeState.value) {


                                    val callUserPerfil = RetrofitFactory()
                                        .getUserService()
                                        .getUsers()

                                    callUserPerfil.enqueue(object : Callback<ResultUserProfileList> {
                                        override fun onResponse(
                                            p0: Call<ResultUserProfileList>,
                                            res: Response<ResultUserProfileList>
                                        ) {
                                            val userResult = res.body()?.usuario
                                            if (userResult != null) {
                                                // Filtrar o usuário pelo email
                                                val usuarioEncontrado = userResult.find { it.email == emailUser }
                                                if (usuarioEncontrado != null) {
                                                    idUser.value = usuarioEncontrado.id
                                                    Log.i("Usuário encontrado", "ID: ${idUser.value}, Email: ${usuarioEncontrado.email}")

                                                    navController.navigate("resetPassword/${idUser.value}")
                                                } else {
                                                    Log.i("Usuário não encontrado", "Nenhum usuário com o email $emailUser foi encontrado.")
                                                }
                                            } else {
                                                Log.i("Resposta vazia", "Nenhum dado foi retornado.")
                                            }
                                        }

                                        override fun onFailure(
                                            p0: Call<ResultUserProfileList>,
                                            t: Throwable
                                        ) {
                                            Log.i("Falhou!!!", t.toString())
                                        }
                                    })




                                } else {
                                    errorState.value = true
                                }
                            }) {
                                Icon(Icons.Outlined.Email, "")
                            }
                        },
                        onValueChange = {
                            codeState.value = it
                        },
                        shape = RectangleShape,
                        placeholder = {
                            Text(
                                modifier = Modifier.fillMaxHeight(),
                                text = "Código aqui",
                                fontFamily = Inter,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp,
                                color = Color(0xff7D7D7D)
                            )
                        }
                    )

                    if (errorState.value) {
                        Text(
                            "Código inválido",
                            color = Color.Red,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
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
                onClick = {
                    val resendToken = RetrofitFactory()
                        .getUserService()
                        .sendToken(email)

                    resendToken.enqueue(object : Callback<TokenRes> {
                        override fun onResponse(call: Call<TokenRes>, res: Response<TokenRes>) {
                            if (res.isSuccessful && res.body() != null) {
                                tokenState.value = res.body()!!.token ?: 0
                            } else {
                                Log.i("Response Error", "Token inválido ou não recebido")
                            }
                        }

                        override fun onFailure(call: Call<TokenRes>, t: Throwable) {
                            Log.i("Falhou:", t.toString())
                        }
                    })
                }
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

