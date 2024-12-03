package br.senai.sp.jandira.touccanuser.screens

import android.content.Context
import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.UserPreferences
import br.senai.sp.jandira.touccanuser.model.ResultUserProfile
import br.senai.sp.jandira.touccanuser.model.UserPerfil
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Conta(navController: NavHostController, context: Context) {

    val userPreferences = UserPreferences(context)
    val userIdFlow = userPreferences.userId.collectAsState(initial = 0)
    val scope = rememberCoroutineScope()

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

            var perfilUsuario = remember {
                mutableStateOf(UserPerfil())
            }
            val userPreferences = UserPreferences(context)
            val userIdFlow = userPreferences.userId.collectAsState(initial = 0)

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



            Column(
                modifier = Modifier
                    .padding(innerpadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
                    Image(painterResource(R.drawable.seta_voltar), "",
                    modifier = Modifier.height(40.dp).clickable { navController.popBackStack() }) }
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 34.dp, bottom = 50.dp)
                ) {
                    Text(
                        "Informações da conta",
                        fontFamily = Inter,
                        color = Color.Black,
                        fontWeight = FontWeight.Black,
                        fontStyle = FontStyle.Italic,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Box(
                        modifier = Modifier
                            .background(Color(MainOrange.value))
                            .height(1.dp)
                            .width(170.dp)
                    )
                }


                Card (
                    modifier = Modifier.height(400.dp).width(300.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xA6C7C2C2))
                ){

                    val telefoneFormat = perfilUsuario.value.telefone.replace(Regex("(\\d{2})(\\d{5})(\\d{4})"), "($1)$2-$3")
                    val cep = perfilUsuario.value.cep.replace(Regex("(\\d{5})(\\d{3})"), "$1-$2")
                    Column (modifier = Modifier.fillMaxSize().padding(24.dp)){
                        Text("Nome do Usuário", fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 18.sp,color = Color.Black)
                        Text(perfilUsuario.value.nome, fontFamily = Inter, color = Color.Black, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(24.dp))
                        Text("Celular", fontFamily = Inter, fontWeight = FontWeight.Bold,color = Color.Black, fontSize = 18.sp)
                        Text(telefoneFormat, fontFamily = Inter, color = Color.Black, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(24.dp))
                        Text("E-mail", fontFamily = Inter, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 18.sp)
                        Text(perfilUsuario.value.email, fontFamily = Inter, color = Color.Black, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(24.dp))
                        Text("CEP", fontFamily = Inter, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 18.sp)
                        Text(cep, fontFamily = Inter, fontSize = 16.sp, color = Color.Black)
                        Spacer(modifier = Modifier.height(70.dp))


                        Text("Sair", fontFamily = Inter, fontWeight = FontWeight.Bold, color = Color.Red, fontSize = 16.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()
                            .clickable{
                                scope.launch {
                                    userPreferences.saveUserId(0)
                                }
                                navController.navigate("logIn")
                            })

                    }

                }

            }

        }




    }
}
