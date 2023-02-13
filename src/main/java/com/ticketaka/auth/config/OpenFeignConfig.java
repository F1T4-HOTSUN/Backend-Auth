package com.ticketaka.auth.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.ticketaka.auth")
public class OpenFeignConfig {
}
