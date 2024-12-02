package br.senai.sp.jandira.touccanuser

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "Novo token gerado: $token")

        // Salvar o token no banco de dados
        saveTokenToDatabase(token)
    }

    private fun saveTokenToDatabase(token: String) {
        // Inicialize o UserPreferences usando o applicationContext
        val userPreferences = UserPreferences(applicationContext)

        // Usar runBlocking para obter o ID do usuário de forma síncrona
        val userId = runBlocking {
            userPreferences.userId.firstOrNull()
        }

        if (userId != null && userId != 0) {
            // Acessar o Firestore
            val db = FirebaseFirestore.getInstance()

            // Buscar o documento baseado no ID do usuário
            val userRef = db.collection("users").document(userId.toString())

            // Atualizar o campo `token` com o novo valor
            userRef.update("token", token)
                .addOnSuccessListener {
                    Log.d("FCM", "Token atualizado com sucesso para o usuário $userId")
                }
                .addOnFailureListener { e ->
                    Log.e("FCM", "Erro ao atualizar token no Firestore", e)
                }
        } else {
            Log.w("FCM", "Usuário não encontrado no UserPreferences. Token não foi salvo.")
        }
    }
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)


        val title = remoteMessage.notification?.title
        val body = remoteMessage.notification?.body
        val channelId = "HEADS_UP_NOTIFICATIONS"

        // Verifica a versão do Android para criar o canal de notificação
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "MyNotification",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }

        // Cria a notificação
        val notification = Notification.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSmallIcon(android.R.drawable.ic_dialog_info) // Ícone da notificação

        // Mostra a notificação
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notification.build())

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        NotificationManagerCompat.from(this).notify(1, notification.build())
    }

    

}

