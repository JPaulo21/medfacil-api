package com.api.medfacil.domain.services;

import com.api.medfacil.config.firebase.messaging.FirebaseMessagingService;
import com.api.medfacil.config.firebase.messaging.PushNotification;
import com.api.medfacil.domain.entities.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessagesService {

    private final FirebaseMessagingService firebaseMessagingService;

    public void sendPushNotification(String recipientToken, Message message){
        PushNotification pushNotification = PushNotification.builder()
                .token(recipientToken)
                .title(message.title())
                .body(message.body())
                .build();
        firebaseMessagingService.sendPushNotificationByToken(pushNotification);
    }

}
