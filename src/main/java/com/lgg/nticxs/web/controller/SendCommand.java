package com.lgg.nticxs.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lgg.nticxs.web.model.SimpleCard;

@Controller
public class SendCommand {

	@GetMapping("home/send/command")
	public String loadPa(Model model){
//		CardDAO carddao=new CardDAO();
//		List<Card> cardss=carddao.retrieve();
//		List<SimpleCard> cards=new ArrayList<>();
//		for(Card aux : cardss){
//			SimpleCard card=new SimpleCard(aux);
//			cards.add(card);
//		}
//		model.addAttribute("cards", cards);
		return "send_command";
	}
	
	@PostMapping("home/send/command")
    public String sendComm(Model model, @RequestParam(name="file_template") MultipartFile file,
    		@RequestParam(name="card") String card, @RequestParam(name="spi", required=false) String spi,
    		@RequestParam(name="kic", required=false) String kic, @RequestParam(name="kid", required=false) String kid) {
//		String fileName = null;
		model.addAttribute("msg1", "metodo post");
        return "send_command";
	}
}
