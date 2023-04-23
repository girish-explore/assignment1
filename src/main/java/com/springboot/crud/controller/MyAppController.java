package com.springboot.crud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MyAppController {

    @Value("${myapp.name}")
    private String appName;

    @Value("${myapp.version}")
    private String appVersion;

    @Value("${myapp.description}")
    private String appDescription;

    @GetMapping("/info")
    public ResponseEntity<Map<String, String>> getInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("name", appName);
        info.put("version", appVersion);
        info.put("description", appDescription);
        return ResponseEntity.ok(info);
    }
}

