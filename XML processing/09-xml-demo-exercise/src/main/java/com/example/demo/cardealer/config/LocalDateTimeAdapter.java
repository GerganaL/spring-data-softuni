package com.example.demo.cardealer.config;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;


public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(String date) throws Exception {
        return LocalDateTime.parse(date);
    }

    @Override
    public String marshal(LocalDateTime localDateTime) throws Exception {
        return localDateTime.toString();
    }
}
