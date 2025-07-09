package com.andrew.campy.service;


import com.andrew.campy.dto.CampDTO;
import com.andrew.campy.dto.CampResponseDTO;
import java.util.List;

public interface CampService {
    CampResponseDTO createCamp(CampDTO campDTO);
    CampResponseDTO updateCamp(Long id, CampDTO campDTO);
    void deleteCamp(Long id);
    List<CampResponseDTO> getAllCamps();
}