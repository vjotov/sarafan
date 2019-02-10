package com.jotov.sarafan.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.jotov.sarafan.domain.Message;
import com.jotov.sarafan.domain.Views;
import com.jotov.sarafan.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }


    @GetMapping
    @JsonView(Views.Id.class)
    public List<Message> list() {
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message){
        return message;
    }

    @PostMapping
    public Message create(@RequestBody Message message){
        message.setCreationDate(LocalDateTime.now());
        return messageRepo.save(message);
    }
    // fetch("/message/5", { method: 'PUT', headers: {'Content-Type':'application/json'}, body:JSON.stringify({text:'Fifth message updated'})}).then(console.log)

    @PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message
    ) {
        BeanUtils.copyProperties(message, messageFromDb, "id");

        return messageRepo.save(messageFromDb);
    }
    // fetch("/message/5", { method: 'PUT', headers: {'Content-Type':'application/json'}, body:JSON.stringify({id:"5", text:'Fifth message updated'})}).then(console.log)

    @DeleteMapping("{id}")
    public void delete( @PathVariable("id") Message message) {
        messageRepo.delete(message);
    }
    // fetch("/message/4", { method: 'DELETE', headers: {'Content-Type':'application/json'}}).then(console.log)

    @MessageMapping("/changeMassage")
    @SendTo("/topic/activity")
    public Message change(Message message) {
        return messageRepo.save(message);
    }
}
