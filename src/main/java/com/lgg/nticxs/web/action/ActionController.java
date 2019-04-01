package com.lgg.nticxs.web.action;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ActionController {
//	private CardDAO cardao=new CardDAO();
//	private PresetCommandDAO presetdao= new PresetCommandDAO(); 
//	private CampaignDAO campdao=new CampaignDAO();
//	
//	
//	@GetMapping("home/format/{eid}")
//	public String commands2(Model model, @PathVariable String eid) {
//		Card card = cardao.retrieveByEid(eid);
//		List<CardProfile> profiles=card.getProfiles();
//		for(CardProfile profile: profiles){
//			if(!profile.isFallbackAttribute()){
//				PresetCommand preset=presetdao.retrieveByICCID(profile.getIccid());
//				preset.setAvailable(true);
//				presetdao.update(preset);
//				card.getProfiles().remove(profile);
//				cardao.update(card);
//			}
//		}
//		campdao.settingsCampaing(eid);				
//        return "euiccDelete";
//    }
	
	@GetMapping("home/euiccdelete")
	public String commandsReturn(Model model){
		return "euiccDelete";
	}
}
