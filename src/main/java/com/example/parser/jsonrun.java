package com.example.parser;

import com.fasterxml.jackson.databind.ObjectMapper;

public class jsonrun {

    public static void main(String[] args) {
        String input = "{\"name\":\"thisisthename\"}";
        ObjectMapper mapper = new ObjectMapper();
        try{
            jsoninput mp = mapper.readValue(input, jsoninput.class);
            System.out.println(mp.getName());
        } catch (Exception e) {}
    }

}
