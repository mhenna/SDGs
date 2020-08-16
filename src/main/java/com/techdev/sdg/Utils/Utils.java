package com.techdev.sdg.Utils;

import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Utils {

    @Autowired
    private JavaMailSender sender;
    public List<Long> getIdsListFromReqBody(Map<String, Object> body, String field) {
        try {
            List<Integer> idIntegers = JSONValue.parse(body.get(field).toString(), ArrayList.class);
            List<Long> idLongs = idIntegers.stream().map(Integer::longValue).collect(Collectors.toList());
            return idLongs;
        } catch (Exception e) {
            return null;
        }
    }

    public List<String> getStrListFromReqBodyObjects(Map<String, Object> body, String field) {
        try {
            List<String> viewersList = JSONValue.parse(body.get(field).toString(), ArrayList.class);
            List<String> viewers = viewersList.stream().map(String::toString).collect(Collectors.toList());
            return viewers;
        } catch (Exception e) {
            return null;
        }
    }
    public void sendEmail(String to) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(to);
        helper.setText("How are you?");
        helper.setSubject("Hi");

        sender.send(message);
    }
}
