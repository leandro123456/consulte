package com.lgg.nticxs.web.controller;

import com.lgg.nticxs.web.helper.TailLogReader;

import com.lgg.nticxs.web.utils.Settings;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


/**
 * Created by movasim on 21/11/16.
 */
@Controller
public class LogsController {

    @GetMapping("home/view-logs")
    public String logs(Model model) {
        return "view_logs";
    }

//    @GetMapping("home/view-logs/getLogOffset")
//    public @ResponseBody
//    String getLogOffset( @RequestParam("name") String name) {
//
//        String jsonObject = "{ \"success\": \"false\" , \"text\": \"Unknown error: 30\"}";
//        if (name!= null) {
//            if (name.equalsIgnoreCase("SMSC")) {
//                TailLogReader tlr = new TailLogReader(Settings.getInstance().getPath_logCcm());
//                long offset = tlr.findEnd();
//                jsonObject = "{ \"success\": \"false\" , \"text\": \"" + offset + "\"}";
//            } else
//            if (name.equalsIgnoreCase("TLS")) {
//                TailLogReader tlr = new TailLogReader(Settings.getInstance().getPath_logDp());
//                long offset = tlr.findEnd();
//                jsonObject = "{ \"success\": \"false\" , \"text\": \"" + offset + "\"}";
//            } else
//            if (name.equalsIgnoreCase("WEB")) {
//                TailLogReader tlr = new TailLogReader(Settings.getInstance().getPath_logSr());
//                long offset = tlr.findEnd();
//                jsonObject = "{ \"success\": \"false\" , \"text\": \"" + offset + "\"}";
//            }
//        }
//
//       // model.addAttribute("json", jsonObject);
//        return jsonObject;
//    }

//    @GetMapping("home/view-logs/getLogRegion")
//    public @ResponseBody
//    String getLogRegion( @RequestParam("name") String name,@RequestParam("start") String start,@RequestParam("end") String end) {
//        String szStart = start;
//        String szEnd = end;
//        String result = "---- Unknown error: 30 ----";
//        if (name!= null) {
//            if (name.equalsIgnoreCase("SMSC")) {
//                TailLogReader tlr = new TailLogReader(Settings.getInstance().getPath_logCcm());
//                long started;
//                started= Long.valueOf(szStart);
//                started=started-2500;
//                long ended;
//                ended = Long.valueOf(szEnd);
//                try {
//                    result = tlr.readRegion(started, ended);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } else
//            if (name.equalsIgnoreCase("TLS")) {
//                TailLogReader tlr = new TailLogReader(Settings.getInstance().getPath_logDp());
//                long started = Long.valueOf(szStart);
//                started=started-2500;
//                long ended = Long.valueOf(szEnd);
//                try {
//                    result = tlr.readRegion(started, ended);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } else
//            if (name.equalsIgnoreCase("WEB")) {
//                TailLogReader tlr = new TailLogReader(Settings.getInstance().getPath_logSr());
//                long started = Long.valueOf(szStart);
//                started=started-2500;
//                long ended = Long.valueOf(szEnd);
//                try {
//                    result = tlr.readRegion(started, ended);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        //System.out.println(String.format("sending : %s " , result));
//        return result;
//    }

    @GetMapping("home/view/instance/logs")
    public String instancelogs(Model model) {
        return "view_instance_logs";
    }


    @GetMapping("home/view/instance/logs/getFilteredLog")
    @ResponseBody
    public String filterLogs( @RequestParam("rand") String rand,@RequestParam("cmdID") String cmdID,@RequestParam("filter") String filter,@RequestParam("value") String value){

        //LogDAO ldao = new LogDAO();

        //int result = -1;
        String jsonObject = "{ \"success\": \"false\" , \"text\": \"Unknown error: 30\"}";

        //if (filter.equalsIgnoreCase("msisdn")) {
        //    logs = ldao.retrieveByMsisdn(value);
        //}
        //if (logs != null) {
       // Gson gson = new Gson();
        //String logsJson = gson.toJson(logs);
        //jsonObject = "{ \"success\": \"true\" , \"text\": \"Success.\" , \"logs\": " + logsJson + "}";
        //} else {
        //  jsonObject = "{ \"success\": \"false\" , \"text\": \"Unknown error: " + result + "\"}";
        //}
        return jsonObject;
    }
}


