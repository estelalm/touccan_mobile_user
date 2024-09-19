package br.senai.sp.jandira.touccanuser.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.model.User
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun SetPassword(navController: NavHostController, user: User) {

    val linearOrange = Brush.linearGradient(listOf(Color(0xffF07B07), Color(0xffE25401)))
    val mainOrange = 0xffF07B07

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

    Surface (modifier = Modifier.fillMaxSize(), color = Color(0xffEBEBEB)) {
        Column (
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                //height com a curva: 320.dp
                .background(linearOrange)
                .zIndex(-0.6F)
            ){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo_touccan),
                        contentDescription = "",
                        modifier = Modifier
                            .size(300.dp)
                            .offset(y = -10.dp) //com a curva é -20.dp
                    )
                }
            }

            Spacer(modifier = Modifier.height(140.dp))
            Column (modifier = Modifier
                .zIndex(10F)
                .offset(y = 0.dp)
                //com a curva o offset é -50
                .fillMaxWidth()
                .padding(40.dp),
                horizontalAlignment = Alignment.Start) {
                Text(
                    text = "Cadastro",
                    fontFamily = Inter
                )

                //coluna que guarda os inputs de cadastro
                Column (modifier = Modifier.fillMaxWidth()){

                    //input senha
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        value = passwordState.value,
                        placeholder = { Text("Crie uma senha") },
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
                                    .size(20.dp)
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
                            focusedContainerColor = Color(0xffEBEBEB),
                            unfocusedContainerColor = Color(0xffEBEBEB),
                            unfocusedIndicatorColor = Color.Black,
                            focusedIndicatorColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            cursorColor =  Color(mainOrange)
                        ),
                        singleLine = true
                    )
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
                                    .size(20.dp)
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
                            focusedContainerColor = Color(0xffEBEBEB),
                            unfocusedContainerColor = Color(0xffEBEBEB),
                            unfocusedIndicatorColor = Color.Black,
                            focusedIndicatorColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            cursorColor =  Color(mainOrange)
                        ),
                        singleLine = true
                    )


                }
                Spacer(modifier = Modifier.height(140.dp))
                Button(
                    onClick = {

                        if(passwordState.value == ""){

                        }else{
                            if(checkPasswordState.value == passwordState.value ){
                                user.senha = passwordState.value
                                Log.i("Dados a serem enviados:", user.toString())

                                RetrofitFactory().getUserService().saveUser(user).enqueue(object : Callback<User> {
                                    override fun onResponse(p0: Call<User>, response: Response<User>) {
                                            Log.i("resposta", response.toString())
                                    }

                                    override fun onFailure(p0: Call<User>, t: Throwable) {
                                        Log.i("FALHOU", t.message.toString())
                                    }
                                })

                        }
                    }},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .height(46.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(mainOrange)
                    ),
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 5.dp
                    ),
                ) {
                    Text(
                        text = "Cadastrar",
                        color = Color.Black,
                        fontFamily = Inter,
                        fontSize = 18.sp,
                    )
                }
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .clickable { navController.navigate("logIn") },
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "Já possui uma conta?",
                        fontSize = 14.sp,
                        color = Color(0xff515151),
                        fontFamily = Inter,
                        lineHeight = 10.sp
                    )
                    Text(text = "Faça seu login",
                        fontSize = 14.sp,
                        color = Color(mainOrange),
                        fontFamily = Inter,
                        textDecoration = TextDecoration.Underline,
                        lineHeight = 12.sp
                    )
                }

            }

            //parte branca curvada no fundo

//            Canvas(modifier = Modifier
//                .fillMaxSize()
//                .offset(y = 1000.dp, x = 3000.dp)
//                .zIndex(-0.6F)
//            ) {
//                rotate(degrees = 114F) {
//                    drawRoundRect(
//                        color = Color(0xffEBEBEB),
//                        topLeft = Offset(x = size.width / 3F, y = size.height / 3F),
//                        size = size / 0.1F,
//                        cornerRadius = CornerRadius(290f)
//                    )
//                }
//            }

        }
    }
}