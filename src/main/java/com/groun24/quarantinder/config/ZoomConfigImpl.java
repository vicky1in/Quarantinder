package com.groun24.quarantinder.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ZoomConfigImpl implements ZoomConfig{
    private String clientId;
    private String clientSecret;
    private String redirectLink;

    public ZoomConfigImpl() {
        this.clientId = "L83dbtc3QtykxSdEIBT7w";
        this.clientSecret = "rISExtLnVax3zjzs9lA8i7VfGUif19gA";
        this.redirectLink = "https://f9cc4f34dec8.ngrok.io/zoom";
    }

    @Override
    public String getURL() {
        String url = "https://zoom.us/oauth/authorize?response_type=code&client_id=" + clientId + "&redirect_uri="
                + redirectLink;
        return url;
    }

    @Override
    public String getClientID() {
        return this.clientId;
    }

    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    @Override
    public String getRedirectAuthLink() {
        return this.redirectLink + "/auth";
    }

}
