package com.andrew.campy.service.impl;


import com.andrew.campy.dto.CampDTO;
import com.andrew.campy.dto.CampResponseDTO;
import com.andrew.campy.entity.Camp;
import com.andrew.campy.repository.CampRepository;
import com.andrew.campy.service.CampService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampServiceImpl implements CampService {

    private final CampRepository campRepository;

    @Override
    public CampResponseDTO createCamp(CampDTO dto) {
        Camp camp = Camp.builder()
                .name(dto.name)
                .price(dto.price)
                .endDate(dto.endDate)
                .type(dto.type)
                .startDate(dto.startDate)
                .build();
        camp = campRepository.save(camp);
        return mapToResponse(camp);
    }

    @Override
    public CampResponseDTO updateCamp(Long id, CampDTO dto) {
        Camp camp = campRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Camp not found"));

        camp.setName(dto.name);
        camp.setStartDate(dto.startDate);
        camp.setEndDate(dto.endDate);
        camp.setType(dto.type);
        camp.setPrice(dto.price);

        camp = campRepository.save(camp);
        return mapToResponse(camp);
    }

    @Override
    public void deleteCamp(Long id) {
        campRepository.deleteById(id);
    }

    @Override
    public List<CampResponseDTO> getAllCamps() {
        return campRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private CampResponseDTO mapToResponse(Camp camp) {
        CampResponseDTO res = new CampResponseDTO();
        res.id = camp.getId();
        res.name = camp.getName();
        res.startDate = camp.getStartDate();
        res.endDate = camp.getEndDate();
        res.type = camp.getType();
        res.price = camp.getPrice();
        return res;
    }
}