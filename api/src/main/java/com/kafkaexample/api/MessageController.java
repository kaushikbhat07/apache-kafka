package com.kafkaexample.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/messages/")
public class MessageController {
    @PostMapping("publish")
    public void publish() {

    }
}
