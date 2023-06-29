package com.example.biblioteca.Messaging

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Aquí puedes manejar la recepción de mensajes
    }

    override fun onNewToken(token: String) {
        // Aquí puedes manejar la obtención de un nuevo token
    }
}
