package com.api.medfacil.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ContactUser {

    private String email;

    @Column(length = 4)
    private String ddi;

    @Column(length = 2)
    private String ddd;

    @Column(name = "phone_number", length = 9)
    private String phoneNumber;

    public String fullNumber(){
        return this.ddi+this.ddd+this.phoneNumber;
    }
}
