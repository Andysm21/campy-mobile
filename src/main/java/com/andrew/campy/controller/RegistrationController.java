package com.andrew.campy.controller;


import com.andrew.campy.dto.PaymentUpdateDTO;
import com.andrew.campy.dto.RegistrationDTO;
import com.andrew.campy.dto.RegistrationResponseDTO;
import com.andrew.campy.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
@RequiredArgsConstructor
public class RegistrationController {


    private final RegistrationService registrationService;

    @PostMapping
    public RegistrationResponseDTO registerCamper(@RequestBody RegistrationDTO dto) {
        return registrationService.registerCamper(dto);
    }

    @GetMapping
    public List<RegistrationResponseDTO> listRegistrations(@RequestParam Long campId) {
        return registrationService.listRegistrations(campId);
    }

    @PutMapping("/{id}/payment")
    public RegistrationResponseDTO updatePayment(@PathVariable Long id, @RequestBody PaymentUpdateDTO dto) {
        return registrationService.updatePayment(id, dto);
    }
}