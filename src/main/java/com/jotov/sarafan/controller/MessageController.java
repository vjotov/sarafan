package com.jotov.sarafan.controller;

import com.jotov.sarafan.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {
    private int  counter = 5;
    private List<Map<String,String>> messages = new ArrayList<Map<String,String>>() {{
        add(new HashMap<String,String>() {{ put("id","1"); put("text","First message"); }});
        add(new HashMap<String,String>() {{ put("id","2"); put("text","Second message"); }});
        add(new HashMap<String,String>() {{ put("id","3"); put("text","Third message"); }});
        add(new HashMap<String,String>() {{ put("id","4"); put("text","Fourth message"); }});
    }};

    @GetMapping
    public List<Map<String,String>> list() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String,String> getOne(@PathVariable String id){
        return getMessage(id);
    }

    private Map<String, String> getMessage(@PathVariable String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String,String> create(@RequestBody Map<String,String> message){
        message.put("id" , String.valueOf(counter++));

        messages.add(message);
        return message;
    }
    // fetch("/message/5", { method: 'PUT', headers: {'Content-Type':'application/json'}, body:JSON.stringify({text:'Fifth message updated'})}).then(console.log)

    @PutMapping("{id}")
    public Map<String,String> update(@RequestBody Map<String,String> message, @PathVariable String id) {
        Map<String,String> messageFromDb = getMessage(id);
        messageFromDb.putAll(message);
        messageFromDb.put("id", id);

        return messageFromDb;
    }
    // fetch("/message/5", { method: 'PUT', headers: {'Content-Type':'application/json'}, body:JSON.stringify({id:"5", text:'Fifth message updated'})}).then(console.log)

    @DeleteMapping("{id}")
    public void delete( @PathVariable String id) {
        Map<String,String> messageFromDb = getMessage(id);
        messages.remove(messageFromDb);
    }
    // fetch("/message/4", { method: 'DELETE', headers: {'Content-Type':'application/json'}}).then(console.log)
}
