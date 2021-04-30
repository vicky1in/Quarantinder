package com.groun24.quarantinder;

import com.groun24.quarantinder.Modal.ZoomToken;
import com.groun24.quarantinder.Services.ZoomAPICallService;
import com.groun24.quarantinder.Services.ZoomAuthCallService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("zoomtest")
@Configuration
public class ZoomTestsConfig {
    @Bean
    @Primary
    public ZoomToken zoomToken() {
        return Mockito.mock(ZoomToken.class);
    }

    @Bean
    @Primary
    public ZoomAuthCallService zoomAuthCallService() {
        return Mockito.mock(ZoomAuthCallService.class);
    }

    @Bean
    @Primary
    public ZoomAPICallService zoomAPICallService() {
        return Mockito.mock(ZoomAPICallService.class);
    }
}
