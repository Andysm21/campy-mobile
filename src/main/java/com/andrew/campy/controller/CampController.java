package com.andrew.campy.controller;


import com.andrew.campy.dto.CampDTO;
import com.andrew.campy.dto.CampResponseDTO;
import com.andrew.campy.service.CampService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/camps")
@RequiredArgsConstructor
public class CampController {


    private final CampService campService;

    @PostMapping
    public CampResponseDTO createCamp(@RequestBody CampDTO campDTO) {
        return campService.createCamp(campDTO);
    }

    @PutMapping("/{id}")
    public CampResponseDTO updateCamp(@PathVariable Long id, @RequestBody CampDTO campDTO) {
        return campService.updateCamp(id, campDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCamp(@PathVariable Long id) {
        campService.deleteCamp(id);
    }

    @GetMapping
    public List<CampResponseDTO> getAllCamps() {
        return campService.getAllCamps();
    }
}