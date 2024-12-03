package br.senai.sp.jandira.touccanuser.screens

import android.content.Context
import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.model.EmailTouccan
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Suporte(navController: NavHostController, context: Context) {

    var  reporteState = remember{
        mutableStateOf("")
    }

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

            Column(
                modifier = Modifier
                    .padding(innerpadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(modifier = Modifier.fillMaxWidth().padding(24.dp)) { Image(
                    painterResource(R.drawable.seta_voltar), "",
                    modifier = Modifier.height(40.dp).clickable { navController.popBackStack()}) }
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp, bottom = 50.dp)
                ) {
                    Text(
                        "Suporte",
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
                    modifier = Modifier.height(440.dp).width(300.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xA6C7C2C2))
                ){
                    Column (modifier = Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally){
                        Column { Text("E-mail para contato", fontFamily = Inter, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 18.sp)
                            Text("contato.touccan@gmail.com", color = Color.DarkGray, fontFamily = Inter, fontWeight = FontWeight.SemiBold,  fontSize = 16.sp) }
                        Spacer(modifier = Modifier.height(24.dp))
                        Text("Encontrou algum problema? Reporte para nós", fontFamily = Inter, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 14.sp)
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                                .height(160.dp),
                            shape = RectangleShape,
                            textStyle = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = Inter,
                                color = Color.Black
                            ),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White
                            ),
                            value = reporteState.value,
                            onValueChange = {
                                reporteState.value = it
                            }
                        )

                        Button(
                            onClick = {

                                val report = EmailTouccan(
                                    assunto = "Reporte da aplicação Mobile Touccan - Usuário",
                                    corpo = reporteState.value
                                )


                                val sendReport = RetrofitFactory()
                                    .getFeedbackService()
                                    .postEmailReport(report)



                                if (sendReport != null) {
                                    sendReport.enqueue(object: Callback<EmailTouccan> {
                                        override fun onResponse(call: Call<EmailTouccan>, res: Response<EmailTouccan>) {
                                            Log.i("Dados a serem enviados", report.toString())
                                            Log.i("Response: ", res.toString())
                                        }

                                        override fun onFailure(call: Call<EmailTouccan>, t: Throwable) {
                                            Log.i("Falhou:", t.toString())
                                        }
                                    })
                                }

                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MainOrange
                            ),
                            modifier = Modifier.padding(top = 16.dp).height(50.dp)
                        ) {
                            Text("Enviar reporte", fontFamily = Inter, fontWeight = FontWeight.Black, color = Color.Black)
                        }
                    }

                }

            }

        }




    }
}
