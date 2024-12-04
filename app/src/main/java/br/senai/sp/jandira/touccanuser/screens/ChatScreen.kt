package br.senai.sp.jandira.touccanuser.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.UserPreferences
import br.senai.sp.jandira.touccanuser.model.ChatMessage
import br.senai.sp.jandira.touccanuser.model.ClientePerfil
import br.senai.sp.jandira.touccanuser.model.ResultClientProfile
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange
import com.google.firebase.database.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chat(navController: NavHostController, clientId: String?, MainActivity: Context) {

    val userPreferences = UserPreferences(MainActivity)
    val userIdFlow = userPreferences.userId.collectAsState(initial = 0)

    val messagesState = remember { mutableStateOf(listOf<ChatMessage>()) }
    val textState = remember { mutableStateOf("") }
    val database = FirebaseDatabase.getInstance()
    val chatId = "C${clientId}_U${userIdFlow.value.toString()}" // Defina o ID do chat
    val messagesRef = database.getReference("chats").child(chatId).child("conversa")

    // Listener para atualizar as mensagens
    messagesRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val messages = mutableListOf<ChatMessage>()
            snapshot.children.forEach { child ->
                val message = child.getValue(ChatMessage::class.java)
                if (message != null) {
                    messages.add(message)
                }
            }
            messagesState.value = messages
        }

        override fun onCancelled(error: DatabaseError) {
            Log.e("FirebaseError", error.message)
        }
    })
    val id = clientId?.toInt()
    val chatClientState = remember{
        mutableStateOf(ClientePerfil())
    }

    val callClienteChat = RetrofitFactory()
        .getClientService()
        .getClientById(id!!)
    callClienteChat.enqueue(object : Callback<ResultClientProfile> {
        override fun onResponse(p0: Call<ResultClientProfile>, p1: Response<ResultClientProfile>) {
            Log.i("response TelaC", p1.body()!!.toString())
            chatClientState.value = p1.body()!!.cliente
        }

        override fun onFailure(p0: Call<ResultClientProfile>, p1: Throwable) {
            Log.i("Falhou!!!", p1.toString())
        }
    })

    Surface(modifier = Modifier.background(Color(0xffEBEBEB))) {

        Scaffold(
            containerColor = Color(0xFFEBEBEB),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFEBEBEB)),
                    title = { Text("") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                tint = Color.Black,
                                painter = painterResource(R.drawable.seta_voltar),
                                contentDescription = "Seta para a esquerda: Voltar",
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            navController.navigate("perfilUsuario/${userIdFlow.value}")
                        }) {
                            Icon(
                                tint = Color.Black,
                                painter = painterResource(R.drawable.person),
                                contentDescription = "Perfil: Ícone de pessoa",
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp)
                ) {
                    Text(
                        chatClientState.value.nome_fantasia,
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Box(
                        modifier = Modifier
                            .background(Color(MainOrange.value))
                            .height(1.dp)
                            .width(160.dp)
                    )
                }
                Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxHeight(0.85F)
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 24.dp, horizontal = 12.dp)
                        ) {
                            items(messagesState.value.size) { index ->
                                val message = messagesState.value[index]
                                val isUserMessage = message.id_usuario != null
                                if (isUserMessage) {
                                    MessageUser(message.texto)
                                } else {
                                    MessageClient(message.texto)
                                }
                            }
                        }
                    }

                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            TextField(
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                value = textState.value,
                                onValueChange = { textState.value = it },
                                modifier = Modifier.weight(1f),
                                placeholder = { Text("Digite uma mensagem...") }
                            )
                            IconButton(
                                onClick = {
                                    val messageText = textState.value
                                    if (messageText.isNotEmpty()) {
                                        val newMessage = ChatMessage(
                                            texto = messageText,
                                            id_usuario = userIdFlow.value.toString(),
                                            timestamp = System.currentTimeMillis().toString(),
                                            tipo = "enviada"
                                        )
                                        messagesRef.push().setValue(newMessage)
                                        textState.value = ""
                                    }
                                },
                                enabled = textState.value.isNotEmpty() // Desativa o botão se o texto estiver vazio
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.send),
                                    contentDescription = "Enviar mensagem"
                                )
                            }
                            }
                        }
                    }
                }
            }
        }
    }


@Composable
fun MessageUser(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xffFFD2A5)
            ),
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .widthIn(max = 240.dp)
        ) {
            Text(text, modifier = Modifier.padding(10.dp))
        }
        Card(modifier = Modifier.size(40.dp), shape = RoundedCornerShape(50.dp)) {
            // Imagem de avatar do usuário (se necessário)
        }
    }
}

@Composable
fun MessageClient(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Card(modifier = Modifier.size(40.dp), shape = RoundedCornerShape(50.dp)) {
            // Imagem de avatar do cliente (se necessário)
        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xff9BA9B0)
            ),
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .widthIn(max = 240.dp)
        ) {
            Text(text, modifier = Modifier.padding(10.dp))
        }
    }
}
