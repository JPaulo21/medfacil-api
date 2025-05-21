package com.api.medfacil.config.firebase.messaging;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FirebaseMessagingService {

    private final FirebaseMessaging firebaseMessaging;

    public void sendPushNotificationByToken(PushNotification pushNotification){
        Notification notification = Notification.builder()
                .setTitle(pushNotification.getTitle())
                .setBody(pushNotification.getBody())
                .build();
        Message message = Message.builder()
                .setToken(pushNotification.getToken())
                .setNotification(notification)
                .build();
        try{
            firebaseMessaging.send(message);
            log.info("Mensagem enviada com sucesso!");
        } catch (FirebaseMessagingException e){
            log.error("Erro ao enviar notificação: {}", e.getMessage());
        }
    }
}
