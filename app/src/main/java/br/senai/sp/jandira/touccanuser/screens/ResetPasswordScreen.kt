package br.senai.sp.jandira.touccanuser.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.MainActivity
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.model.UserPerfil
import br.senai.sp.jandira.touccanuser.model.UserSenha
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ResetPassword(navController: NavHostController, mainActivity: MainActivity, idUser: Int) {
    var passwordState = remember{
        mutableStateOf("")
    }
    var checkPasswordState = remember{
        mutableStateOf("")
    }
    var showPasswordState = remember{
        mutableStateOf(false)
    }

    var isErrorState = remember {
        mutableStateOf(false)
    }
    var messageErrorState = remember {
        mutableStateOf("")
    }

    fun validatePassword(password: String): Boolean{
        val regex = """^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*()_+{}\[\]:;"'<>,.?/\\|`~]).{8,}$""".toRegex()
        return regex.matches(password)
    }
    Surface (modifier = Modifier.background(Color(0xffEBEBEB))){
        
        

        Column (
            modifier = Modifier.fillMaxSize().padding(24.dp),
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
                modifier = Modifier.height(300.dp).width(300.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(modifier = Modifier.padding(vertical = 24.dp, horizontal = 20.dp)){
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 36.dp),
                        text = "Crie uma nova senha",
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontFamily = Inter,
                        fontSize = 22.sp
                    )
                    Column (modifier = Modifier.fillMaxWidth().padding(horizontal = 6.dp)){

                        //input senha
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                            value = passwordState.value,
                            placeholder = { Text("Nova senha") },
                            onValueChange = {

                                passwordState.value = it
                                if(!validatePassword(it))
                                    isErrorState.value = true
                                messageErrorState.value = "A senha deve conter no mínimo 1 letra maíuscula, 1 número, 1 caractere especial (@, !, $, etc) e 8 dígitos"

                            },
                            visualTransformation =
                            if (showPasswordState.value) VisualTransformation.None
                            else PasswordVisualTransformation(),
                            leadingIcon = {
                                Image(
                                    modifier = Modifier
                                        .size(26.dp)
                                        .align(Alignment.Start),
                                    painter = painterResource(id = R.drawable.senha),
                                    contentDescription = "",
                                    contentScale = ContentScale.Fit)
                            },
                            trailingIcon = {
                                Image(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .align(Alignment.Start)
                                        .clickable {
                                            showPasswordState.value = !showPasswordState.value
                                        },
                                    painter = painterResource(id = R.drawable.olho),
                                    contentDescription = "",
                                    contentScale = ContentScale.Fit)
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                unfocusedIndicatorColor = Color.Black,
                                focusedIndicatorColor = Color.Black,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black,
                                cursorColor =  MainOrange
                            ),
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        //input confirmar senha
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                            value = checkPasswordState.value,
                            placeholder = { Text("Confirme a senha") },
                            onValueChange = {checkPasswordState.value = it},
                            visualTransformation =
                            if (showPasswordState.value) VisualTransformation.None
                            else PasswordVisualTransformation(),
                            leadingIcon = {
                                Image(
                                    modifier = Modifier
                                        .size(26.dp)
                                        .align(Alignment.Start),
                                    painter = painterResource(id = R.drawable.senha),
                                    contentDescription = "",
                                    contentScale = ContentScale.Fit)
                            },
                            trailingIcon = {
                                Image(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .align(Alignment.Start)
                                        .clickable {
                                            showPasswordState.value = !showPasswordState.value
                                        },
                                    painter = painterResource(id = R.drawable.olho),
                                    contentDescription = "",
                                    contentScale = ContentScale.Fit)
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                unfocusedIndicatorColor = Color.Black,
                                focusedIndicatorColor = Color.Black,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black,
                                cursorColor =  MainOrange
                            ),
                            singleLine = true
                        )


                    }

                }
            }
            Spacer(modifier = Modifier.height(100.dp))
            Button(
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MainOrange
                ),
                modifier = Modifier.padding(12.dp).width(200.dp),
                onClick = {

                    val novaSenha = UserSenha(
                        senha = passwordState.value
                    )

                    val callUserPerfil = RetrofitFactory()
                        .getUserService()
                        .updateUserSenha(novaSenha, idUser)

                    callUserPerfil.enqueue(object : Callback<UserSenha> {
                        override fun onResponse(p0: Call<UserSenha>, res: Response<UserSenha>) {
                            if(res.isSuccessful){
                                Toast.makeText(mainActivity, "Senha alterada, redirecionando para a página de login...", Toast.LENGTH_SHORT).show()
                                navController.navigate("login")
                            }
                            Log.i("response edit", res.toString())
                        }

                        override fun onFailure(p0: Call<UserSenha>, t: Throwable) {
                            Log.i("Falhou!!!", t.toString())
                        }
                    })

                }
            ) {
                Text(
                    "Atualizar",
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontFamily = Inter,
                    fontSize = 20.sp
                )
            }

        }

    }

}
