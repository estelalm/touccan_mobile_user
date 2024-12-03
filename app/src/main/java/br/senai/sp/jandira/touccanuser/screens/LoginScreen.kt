package br.senai.sp.jandira.touccanuser.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import br.senai.sp.jandira.touccanuser.UserPreferences
import br.senai.sp.jandira.touccanuser.model.Login
import br.senai.sp.jandira.touccanuser.model.LoginResult
import br.senai.sp.jandira.touccanuser.model.UserId
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.checkerframework.checker.units.qual.t
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException


@Composable
fun Login (navController: NavHostController, context: Context, userPreferences: UserPreferences) {

    val userPreferences = UserPreferences(context)
    val userIdFlow = userPreferences.userId.collectAsState(initial = 0)

    val scope = rememberCoroutineScope()

    val linearOrange = Brush.linearGradient(listOf(Color(0xffF07B07), Color(0xffE25401)))
    val mainOrange = 0xffF07B07

    var passwordState = remember{
        mutableStateOf("")
    }
    var emailState = remember{
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

    Surface (modifier = Modifier.fillMaxSize(), color = Color(0xffEBEBEB)) {
        Column (
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
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
//            Canvas(modifier = Modifier
//                .fillMaxSize()
//                .offset(y = 1980.dp, x = 5180.dp)
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


            Spacer(modifier = Modifier.height(100.dp))
            Column (modifier = Modifier
                .zIndex(10F)
                .offset(y = 0.dp)
                //com a curva o offset é -50
                .fillMaxWidth()
                .padding(40.dp),
                horizontalAlignment = Alignment.Start) {


                Text(
                    text = "Login",
                    fontFamily = Inter
                )

                //coluna que guarda os inputs de login
                Column (modifier = Modifier.fillMaxWidth()){

                    //input email
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        value = emailState.value,
                        placeholder = { Text("E-mail") },
                        onValueChange = {

                            emailState.value = it
                            isErrorState.value = false
                        },
                        leadingIcon = {
                            Image(
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(Alignment.Start),
                                painter = painterResource(id = R.drawable.email),
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
                        value = passwordState.value,
                        placeholder = { Text("Senha") },
                        onValueChange = {
                            passwordState.value = it
                            isErrorState.value = false},
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
                Spacer(modifier = Modifier.height(10.dp))
                if(isErrorState.value){
                    Text(messageErrorState.value, color = Color.Red)
                }

                Spacer(modifier = Modifier.height(140.dp))
                Button(
                    onClick = {
                        val login = Login(
                            email = emailState.value,
                            senha = passwordState.value
                        )

                        if(login.senha != "" && login.email != ""){
                            RetrofitFactory().getUserService().loginUser(login).enqueue(object : Callback<LoginResult>{
                                override fun onResponse(p0: Call<LoginResult>, res: Response<LoginResult>) {
                                    Log.i("Response:", res.body().toString())
                                    val userJson = res.body()
                                        ?.let { Json.encodeToString(it.usuario) }

                                    val idUser = res.body()!!.usuario.id

                                    if(res.body() != null){
                                        val userId = res.body()!!.usuario.id
                                        if(userId != null)
                                            scope.launch {
                                                userPreferences.saveUserId(userId)
                                            }
                                    }


                                    if(res.isSuccessful){
                                        navController.navigate("home/${idUser}")
                                    }else{
                                        isErrorState.value = true
                                        messageErrorState.value = "Email ou senha incorretos"
                                    }
                                }
                                override fun onFailure(p0: Call<LoginResult>, res: Throwable) {
                                    if (res is SocketTimeoutException) {
                                        // Tratar erro de timeout
                                        Toast.makeText(context, "A requisição demorou muito, por favor tente novamente.", Toast.LENGTH_SHORT).show()
                                    }
                                    Log.i("Falhou:", res.toString())
                                }
                            })
                        }else{
                            isErrorState.value = true
                            messageErrorState.value = "Todos os campos devem ser preenchidos"
                        }

                        },
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
                        text = "Entrar",
                        color = Color.Black,
                        fontFamily = Inter,
                        fontSize = 18.sp,
                    )
                }
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .clickable { navController.navigate("signUp") },
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "Não possui uma conta?",
                        fontSize = 14.sp,
                        color = Color(0xff515151),
                        fontFamily = Inter,
                        lineHeight = 10.sp
                    )
                    Text(text = "Faça seu cadastro",
                        fontSize = 14.sp,
                        color = Color(mainOrange),
                        fontFamily = Inter,
                        textDecoration = TextDecoration.Underline,
                        lineHeight = 12.sp
                    )
                }

                Text("Esqueceu a senha? Clique aqui para redefinir",
                    fontSize = 14.sp,
                    color = Color(mainOrange),
                    fontFamily = Inter,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.fillMaxWidth().clickable { navController.navigate("") }
                )

            }

            //parte branca curvada no fundo



        }
    }
}


