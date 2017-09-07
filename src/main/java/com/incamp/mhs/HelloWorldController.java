package com.incamp.mhs;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/")
    public Message getHelloWorld(){
        return new Message("sss");
    }

}

@Data
class Message{
    private String content;

    public Message(String content) {
        this.content = content;
    }
}