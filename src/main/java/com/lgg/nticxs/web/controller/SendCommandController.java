package com.lgg.nticxs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Created by movasim on 05/12/16.
 */
@Controller
public class SendCommandController {

    @GetMapping("home/send-command")
    public String search(Model model) {
        return "send_command";
    }
}
