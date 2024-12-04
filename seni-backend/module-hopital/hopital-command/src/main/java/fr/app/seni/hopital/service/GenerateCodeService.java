package fr.app.seni.hopital.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GenerateCodeService {

    public String getHopitalCode() {
        Random rand = new Random();
        String code = rand.ints(48, 123)
                .filter(num -> (num<58 || num>64) && (num<91 || num>96))
                .limit(10)
                .mapToObj(c -> (char)c).collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)
                .toString();
        return code.toUpperCase();
    }
}
