package br.senai.sp.jandira.touccanuser.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.model.LoginResult
import br.senai.sp.jandira.touccanuser.model.UserCard
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastrarCartao() {

    var numeroCartaoState = remember{
        mutableStateOf("")
    }
    var validadeState = remember{
        mutableStateOf("")
    }

    var cvvState = remember{
        mutableStateOf("")
    }
    var titularState = remember{
        mutableStateOf("")
    }
    var cpfState = remember{
        mutableStateOf("")
    }

    var apelidoState = remember{
        mutableStateOf("")
    }

    Surface(){
        Scaffold(
            containerColor = Color(0xffebebeb),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFEBEBEB)
                    ),
                    navigationIcon = {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .height(100.dp)
                                .width(170.dp)
                        ) {
                            Icon(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 10.dp),
                                painter = painterResource(R.drawable.logo_touccan),
                                contentDescription = "Desenho de um, com o texto Touccan ao lado, a logo do aplicativo",
                            )
                        }

                    },
                    title = {
                    },
                    actions = {
                        Row(horizontalArrangement = Arrangement.End) {
                            IconButton(onClick = {}) {
                                Icon(
                                    painter = painterResource(R.drawable.configuracoes),
                                    contentDescription = "Configurações: Ícone de engrenagem",
                                )
                            }
                            IconButton(onClick = {}) {
                                Icon(
                                    painter = painterResource(R.drawable.carteira),
                                    contentDescription = "Carteira: Ícone de carteira",
                                )
                            }
                            IconButton(onClick = {

                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.person),
                                    contentDescription = "Perfil: Ícone de pessoa",
                                )
                            }
                        }
                    }

                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = Color(0xFFEBEBEB)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {


                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.home),
                                contentDescription = "Home: Ícone de casa",
                            )
                        }
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.historico),
                                contentDescription = "Home: Ícone de casa",
                            )
                        }
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                modifier = Modifier.size(35.dp),
                                painter = painterResource(R.drawable.notificacao),
                                contentDescription = "Home: Ícone de casa",
                            )
                        }

                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.chat),
                                contentDescription = "Home: Ícone de casa",
                            )
                        }

                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.pesquisa),
                                contentDescription = "Home: Ícone de casa",
                            )
                        }
                    }

                }
            }
        ){ it ->
            Column(
                modifier = Modifier.fillMaxSize().padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth().padding(top = 80.dp, bottom = 50.dp)
                ) {
                    Text(
                        "Adicionar cartão",
                        fontFamily = Inter,
                        fontWeight = FontWeight.ExtraBold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Box(
                        modifier = Modifier
                            .background(Color(MainOrange.value))
                            .height(1.dp)
                            .width(200.dp)
                    )
                }

                Card (modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp)){
                    Row (modifier = Modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .background(Color.White)){
                        Card (
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MainOrange,
                            ),
                            shape = RectangleShape
                        ){}
                        Column (
                            modifier = Modifier
                                .padding(horizontal = 24.dp, vertical = 12.dp)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceAround
                        ){

                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                text = "DÉBITO",
                                fontFamily = Inter,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                            )

                            OutlinedTextField(
                                modifier = Modifier.height(50.dp).fillMaxWidth(),
                                value = numeroCartaoState.value,
                                onValueChange = {
                                    numeroCartaoState.value = it
                                },
                                shape = RectangleShape,
                                placeholder = {
                                    Text(
                                        modifier = Modifier.fillMaxHeight(),
                                        text = "Número do cartão",
                                        fontFamily = Inter,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 15.sp,
                                        color = Color(0xff7D7D7D)
                                    )
                                }
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){

                                OutlinedTextField(
                                    modifier = Modifier.height(50.dp).width(150.dp),
                                    value = validadeState.value,
                                    onValueChange = {
                                        validadeState.value = it
                                    },
                                    shape = RectangleShape,
                                    placeholder = {
                                        Text(
                                            modifier = Modifier.fillMaxHeight(),
                                            text = "Validade",
                                            fontFamily = Inter,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 16.sp,
                                            color = Color(0xff7D7D7D)
                                        )
                                    }
                                )

                                OutlinedTextField(
                                    modifier = Modifier.height(50.dp).width(120.dp),
                                    value = cvvState.value,
                                    onValueChange = {
                                        cvvState.value = it
                                    },
                                    shape = RectangleShape,
                                    placeholder = {
                                        Text(
                                            modifier = Modifier.fillMaxHeight(),
                                            text = "CVV",
                                            fontFamily = Inter,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 16.sp,
                                            color = Color(0xff7D7D7D)
                                        )
                                    }
                                )
                            }

                            OutlinedTextField(
                                modifier = Modifier.height(50.dp).fillMaxWidth(),
                                value = titularState.value,
                                onValueChange = {
                                    titularState.value = it
                                },
                                shape = RectangleShape,
                                placeholder = {
                                    Text(
                                        modifier = Modifier.fillMaxHeight(),
                                        text = "Nome do titular",
                                        fontFamily = Inter,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 16.sp,
                                        color = Color(0xff7D7D7D)
                                    )
                                }
                            )


                            OutlinedTextField(
                                modifier = Modifier.height(50.dp).fillMaxWidth(),
                                value = cpfState.value,
                                onValueChange = {
                                    cpfState.value = it
                                },
                                shape = RectangleShape,
                                placeholder = {
                                    Text(
                                        modifier = Modifier.fillMaxHeight(),
                                        text = "CPF",
                                        fontFamily = Inter,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 16.sp,
                                        color = Color(0xff7D7D7D)
                                    )
                                }
                            )

                            OutlinedTextField(
                                modifier = Modifier.height(50.dp).fillMaxWidth(),
                                value = "",
                                onValueChange = {},
                                shape = RectangleShape,
                                placeholder = {
                                    Text(
                                        modifier = Modifier.fillMaxHeight(),
                                        text = "Apelido do cartão (opcional)",
                                        fontFamily = Inter,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 16.sp,
                                        color = Color(0xff7D7D7D)
                                    )
                                }
                            )


                        }
                    }
                }

                Button(
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MainOrange
                    ),
                    modifier = Modifier.padding(12.dp),
                    onClick = {


                        val cartao = UserCard(
                            numero = numeroCartaoState.value.toInt(),
                            validade= validadeState.value,
                            cvv= cvvState.value.toInt(),
                            nome_titular = titularState.value,
                            cpf = cpfState.value.toLong(),
                            apelido= apelidoState.value,
                            id_usuario = 2
                        )
                        RetrofitFactory().getCartaoService().postCartao(cartao).enqueue(object :
                            Callback<UserCard> {
                            override fun onResponse(p0: Call<UserCard>, res: Response<UserCard>) {
                                Log.i("Response:", res.body().toString())
                            }
                            override fun onFailure(p0: Call<UserCard>, res: Throwable) {
                                Log.i("Falhou:", res.toString())
                            }
                        })

                    }
                ) {
                    Text(
                        "Salvar",
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontFamily = Inter,
                        fontSize = 18.sp
                    )
                }

            }
        }

    }
}

@Preview
@Composable
private fun CadastrarCardPrev() {
    CadastrarCartao()
}