package com.api.medfacil.config.firebase.messaging;

import lombok.Builder;
import lombok.Data;

import java.util.Map;


@Data
@Builder
public class PushNotification {

    private String token;
    private String title;
    private String body;
    private Map<String, String> data;

}
