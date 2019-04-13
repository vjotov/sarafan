package com.jotov.sarafan.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.jotov.sarafan.domain.Message;
import com.jotov.sarafan.domain.User;
import com.jotov.sarafan.domain.Views;
import com.jotov.sarafan.dto.MessagePageDto;
import com.jotov.sarafan.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("message")
public class MessageController {
    public static final int MESSAGES_PER_PAGE = 3;

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    @JsonView(Views.FullMessage.class)
    public MessagePageDto list(
            @AuthenticationPrincipal User user,
            @PageableDefault(size = MESSAGES_PER_PAGE, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return messageService.findForUser(pageable, user);
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message){
        return message;
    }

    @PostMapping
    public Message create(
            @RequestBody Message message,
            @AuthenticationPrincipal User user
    ) throws IOException {
        //Message message = new Message();
        //message.setText(messageText);
        return messageService.create(message, user);
    }
    // fetch("/message/5", { method: 'PUT', headers: {'Content-Type':'application/json'}, body:JSON.stringify({text:'Fifth message updated'})}).then(console.log)

    @PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message
    ) throws IOException {
        return messageService.update(messageFromDb, message);
    }
    // fetch("/message/5", { method: 'PUT', headers: {'Content-Type':'application/json'}, body:JSON.stringify({id:"5", text:'Fifth message updated'})}).then(console.log)

    @DeleteMapping("{id}")
    public void delete( @PathVariable("id") Message message) {
        messageService.delete(message);
    }



}
