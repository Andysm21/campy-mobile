package com.andrew.campy.dto;
import com.andrew.campy.entity.PaymentStatus;

public class RegistrationResponseDTO {
    public Long id;
    public String camperName;
    public String email;
    public String age;
    public String phoneNumber;
    public Long campId;
    public PaymentStatus paymentStatus;
}
