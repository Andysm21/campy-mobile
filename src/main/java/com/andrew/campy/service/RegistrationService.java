package com.andrew.campy.service;


import com.andrew.campy.dto.PaymentUpdateDTO;
import com.andrew.campy.dto.RegistrationDTO;
import com.andrew.campy.dto.RegistrationResponseDTO;

import java.util.List;

public interface RegistrationService {
    RegistrationResponseDTO registerCamper(RegistrationDTO dto);
    List<RegistrationResponseDTO> listRegistrations(Long campId);
    RegistrationResponseDTO updatePayment(Long registrationId, PaymentUpdateDTO dto);
}