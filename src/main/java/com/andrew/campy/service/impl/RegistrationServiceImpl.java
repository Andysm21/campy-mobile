package com.andrew.campy.service.impl;


import com.andrew.campy.dto.PaymentUpdateDTO;
import com.andrew.campy.dto.RegistrationDTO;
import com.andrew.campy.dto.RegistrationResponseDTO;
import com.andrew.campy.entity.Camp;
import com.andrew.campy.entity.PaymentStatus;
import com.andrew.campy.entity.Registration;
import com.andrew.campy.repository.CampRepository;
import com.andrew.campy.repository.RegistrationRepository;
import com.andrew.campy.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;

    private final CampRepository campRepository;

    @Override
    public RegistrationResponseDTO registerCamper(RegistrationDTO dto) {
        Camp camp = campRepository.findById(dto.campId)
                .orElseThrow(() -> new IllegalArgumentException("Camp not found"));

        Registration reg = new Registration();
        reg.setCamperName(dto.camperName);
        reg.setEmail(dto.email);
        reg.setPhoneNumber(dto.phoneNumber);
        reg.setAge(dto.age);
        reg.setCamp(camp);
        reg.setPaymentStatus(PaymentStatus.UNPAID);


        reg = registrationRepository.save(reg);
        return mapToResponse(reg);
    }

    @Override
    public List<RegistrationResponseDTO> listRegistrations(Long campId) {
        return registrationRepository.findByCampId(campId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RegistrationResponseDTO updatePayment(Long registrationId, PaymentUpdateDTO dto) {
        Registration reg = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new IllegalArgumentException("Registration not found"));
        reg.setPaymentStatus(dto.paymentStatus);
        reg = registrationRepository.save(reg);
        return mapToResponse(reg);
    }

    private RegistrationResponseDTO mapToResponse(Registration reg) {
        RegistrationResponseDTO res = new RegistrationResponseDTO();
        res.id = reg.getId();
        res.camperName = reg.getCamperName();
        res.email = reg.getEmail();
        res.phoneNumber = reg.getPhoneNumber();
        res.age = reg.getAge();
        res.campId = reg.getCamp().getId();
        res.paymentStatus = reg.getPaymentStatus();
        return res;
    }
}