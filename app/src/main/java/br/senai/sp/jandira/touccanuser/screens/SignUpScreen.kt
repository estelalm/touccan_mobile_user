package br.senai.sp.jandira.touccanuser.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.model.User
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale


//formata o número de telefone
fun formatPhone(phone: String): String{
    when {
        phone.length <= 10 -> { //se não tiver 11 dígitos
            phone.replace("(\\d{2})(\\d{4})(\\d+)".toRegex(), "(${1}) ${2}-${3}")
        }
        else -> { //se tiver 11 dígitos
            phone.replace("(\\d{2})(\\d{5})(\\d+)".toRegex(), "(${1}) ${2}-${3}")
        }
    }

    return phone
}

fun formatCep(){

}
fun formatDate (){}

fun validateEmail(email: String): Boolean {
    //regex de email
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
//val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault())
//fun validateAge(date: String): Boolean{
//    val birthLocalDate = LocalDate.parse(date, dateFormatter)
//    val currentDate = LocalDate.now()
//
//    // Calculate the difference in years
//    val age = ChronoUnit.YEARS.between(birthLocalDate, currentDate)
//    return age >= 18
//}

@SuppressLint("SuspiciousIndentation")
@Composable
fun SignUpScreen(navController: NavHostController) {

    val user = User()

    val linearOrange = Brush.linearGradient(listOf(Color(0xffF07B07), Color(0xffE25401)))
    val mainOrange = 0xffF07B07

    var nameState = remember{
        mutableStateOf("")
    }
    var emailState = remember{
        mutableStateOf("")
    }
    var phoneState = remember{
        mutableStateOf("")
    }
    var cpfState = remember{
        mutableStateOf("")
    }
    var birthdateState = remember{
        mutableStateOf("")
    }
    var cepState = remember{
        mutableStateOf("")
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
                Spacer(modifier = Modifier.height(18.dp))
                //coluna que guarda os inputs de cadastro
                Column (modifier = Modifier.fillMaxWidth()){

                    //input nome
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        value = nameState.value,
                        placeholder = { Text("Nome completo") },
                        onValueChange = {

                            if(it.length <=80)
                            nameState.value = it

                                        },
                        leadingIcon = {
                            Image(
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(Alignment.Start),
                                painter = painterResource(id = R.drawable.usuario),
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
                    //input email
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        value = emailState.value,
                        placeholder = { Text("E-mail") },
                        onValueChange = {
                            if(it.length <=100)
                            emailState.value = it
                            if(!validateEmail(it))
                                isErrorState.value = true
                            messageErrorState.value = "Insira um email válido"
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
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        )
                    )
                    //input telefone
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        value = phoneState.value,
                        placeholder = { Text("Telefone") },
                        onValueChange = {

//                            val cleanText = it.filter { it.isDigit() }
                            if (it.length <= 11) {
                                val formatted = formatPhone(it)
                                phoneState.value = formatted
                            }

                                        },
                        leadingIcon = {
                            Image(
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(Alignment.Start),
                                painter = painterResource(id = R.drawable.telefone),
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
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                    //input cpf
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        value = cpfState.value,
                        placeholder = { Text("CPF") },
                        onValueChange = {cpfState.value = it},
                        leadingIcon = {
                            Image(
                                modifier = Modifier
                                    .size(30.dp)
                                    .align(Alignment.Start),
                                painter = painterResource(id = R.drawable.cpf),
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
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                    //input data de nascimento
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        value = birthdateState.value,
                        placeholder = { Text("Data de nascimento") },
                        onValueChange = {

                            birthdateState.value = it
//                            if(!validateAge(it))
//                                isErrorState.value = true
//                            messageErrorState.value = "Você deve estar acima de 18 anos."
                                        },
                        leadingIcon = {
                            Image(
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(Alignment.Start),
                                painter = painterResource(id = R.drawable.calendario),
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
//                        keyboardOptions = KeyboardOptions(
//                            keyboardType = KeyboardType.Number
//                        )
                    )
                    //input cep
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        value = cepState.value,
                        placeholder = { Text("CEP") },
                        onValueChange = {cepState.value = it},
                        leadingIcon = {
                            Image(
                                modifier = Modifier
                                    .size(40.dp)
                                    .align(Alignment.Start),
                                painter = painterResource(id = R.drawable.maps),
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
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )

                }
                Spacer(modifier = Modifier.height(76.dp))
                Button(
                    onClick = {

                        if(nameState.value == "" || emailState.value == "" || phoneState.value == ""
                           || cpfState.value == "" || cepState.value == "" || birthdateState.value == ""){
                                isErrorState.value = true
                        }else{

                            val birthDate = birthdateState.value.split('/').reversed().joinToString("-")
                            user.nome = nameState.value
                            user.email = emailState.value
                            user.telefone = phoneState.value
                            user.cpf = cpfState.value
                            user.cep = cepState.value
                            user.data_nascimento = birthDate

                            val dadosJson = Json.encodeToString(user)

                            navController.navigate("setPassword/${dadosJson}")
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
                        text = "Continuar",
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
                        lineHeight = 12.sp,
                    )
                }

            }

            //parte branca curvada no fundo

            Canvas(modifier = Modifier
                .fillMaxSize()
                .zIndex(-0.6F)
            ) {
                rotate(degrees = 114F) {
                    drawRoundRect(
                        color = Color(0xffEBEBEB),
                        topLeft = Offset(x = size.width / 3F, y = size.height / 3F),
                        size = size / 0.1F,
                        cornerRadius = CornerRadius(290f)
                    )
                }
            }


        }
    }


}
