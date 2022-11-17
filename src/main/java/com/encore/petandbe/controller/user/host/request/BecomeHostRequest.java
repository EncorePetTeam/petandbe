package com.encore.petandbe.controller.user.host.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public final class BecomeHostRequest {
    private String business_registation_number;
    private String host_name;
    private String business_open_date;
    private String state;

    public BecomeHostRequest(String business_registation_number,String host_name, String business_open_date,String state) {
        this.business_registation_number = business_registation_number;
        this.host_name = host_name;
        this.business_open_date = business_open_date;
        this.state = state;
    }
}
