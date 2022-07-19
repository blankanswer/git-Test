package com.example.springboottest.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
@Component
@ConfigurationProperties(prefix = "enterprise")
public class Enterprise {
    private  int age;
}
