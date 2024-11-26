package br.senai.sp.jandira.touccanuser.screens


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.MainActivity
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.UserPreferences
import br.senai.sp.jandira.touccanuser.model.AvaliacaoRes
import br.senai.sp.jandira.touccanuser.model.AvaliacaoUser
import br.senai.sp.jandira.touccanuser.model.DenunciaRes
import br.senai.sp.jandira.touccanuser.model.DenunciaUser
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Denuncia(
    navController: NavHostController,
    bicoId: String,
    mainActivity: MainActivity,
    clienteId: String?
) {


    val userPreferences = UserPreferences(mainActivity)
    val userIdFlow = userPreferences.userId.collectAsState(initial = null)

    var isLoadingState = remember{
        mutableStateOf(true)
    }
    var errorState = remember {
        mutableStateOf(false)
    }
    var commentState = remember{
        mutableStateOf("")
    }


    Scaffold (
        containerColor = Color(0xFFEBEBEB),
        topBar = {
            //br.senai.sp.jandira.touccanuser.utility.TopAppBar(navController)
        },
        bottomBar = {
            //br.senai.sp.jandira.touccanuser.utility.BottomAppBar(navController)
        }
    ) { innerpadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        "Seta voltar",
                        modifier = Modifier.height(24.dp)
                    )
                }


            }
            if (!isLoadingState.value) {
                CircularProgressIndicator(color = MainOrange)
            } else {
                Spacer(modifier = Modifier.height(50.dp))
                Card(
                    modifier = Modifier.padding(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0x75D9D9D9)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {


                        Column(
                            modifier = Modifier.fillMaxWidth().padding(12.dp)
                        ) {

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Filled.Warning, "",
                                    tint = Color(0xff9B0707),
                                    modifier = Modifier.size(40.dp).padding(end = 6.dp))
                                Text(
                                    "Faça sua denúncia ",
                                    color = Color.Black,
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp
                                )

                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {

                                Icon(
                                    painter = painterResource(R.drawable.person),
                                    contentDescription = "",
                                    tint = Color(0xff7E7E7E)
                                )
                                Text(
                                    "Empresa 1 - Assistente Admnistrativo",
                                    color = Color(0xff504D4D),
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.padding(start = 6.dp).fillMaxWidth()
                                )
                            }
                        }
                        Column(
                            modifier = Modifier.padding(start = 12.dp)
                        ) {

                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        "Descrição da denúncia:",
                                        fontFamily = Inter,
                                        fontSize = 16.sp
                                    )

                                }
                                OutlinedTextField(
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)
                                        .height(120.dp),
                                    shape = RectangleShape,
                                    textStyle = TextStyle(
                                        fontSize = 12.sp,
                                        fontFamily = Inter
                                    ),
                                    colors = TextFieldDefaults.colors(
                                        unfocusedContainerColor = Color.White,
                                        focusedContainerColor = Color.White
                                    ),
                                    value = commentState.value,
                                    onValueChange = {
                                        commentState.value = it
                                    }
                                )
                            }


                        }
                    }



                }

                Button(
                    onClick = {

                        val denuncia = userIdFlow.value?.let {
                            clienteId?.let { it1 ->
                                DenunciaUser(
                                    id_cliente = it1.toInt(),
                                    id_usuario = it,
                                    id_bico = bicoId.toInt(),
                                    denuncia = commentState.value
                                )
                            }
                        }

                        val sendDenuncia = denuncia?.let {
                            RetrofitFactory()
                                .getFeedbackService()
                                .saveUserDenuncia(it)
                        }


                        if (sendDenuncia != null) {
                            sendDenuncia.enqueue(object: Callback<DenunciaRes> {
                                override fun onResponse(call: Call<DenunciaRes>, res: Response<DenunciaRes>) {
                                    Log.i("Dados a serem enviados", denuncia.toString())
                                    Log.i("Response: ", res.toString())
                                }

                                override fun onFailure(call: Call<DenunciaRes>, t: Throwable) {
                                    Log.i("Falhou:", t.toString())
                                }
                            })
                        }

                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MainOrange
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.padding(top = 24.dp)
                ) {
                    Text("Enviar", fontFamily = Inter, fontWeight = FontWeight.Black)
                }
            }


        }

    }
}


