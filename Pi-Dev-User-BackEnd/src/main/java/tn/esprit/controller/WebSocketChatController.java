package tn.esprit.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class WebSocketChatController {

    @MessageMapping("/resume")
    @SendTo("/start/initial")
    public String chat(String msg) {
        msg = "hello there";
        System.out.println(msg);
        return msg;
    }
}
