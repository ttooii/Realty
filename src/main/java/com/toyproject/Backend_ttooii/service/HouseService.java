package com.toyproject.Backend_ttooii.service;

import com.toyproject.Backend_ttooii.dto.HouseListDto;
import com.toyproject.Backend_ttooii.dto.HouseSaveDto;
import com.toyproject.Backend_ttooii.entity.House;
import com.toyproject.Backend_ttooii.repository.HouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true) // 구동 실패 시 Rollback 할 수 있도록 하는 안전장치
@AllArgsConstructor
public class HouseService {

    private HouseRepository houseRepository;

    @Transactional
    public String createHouse(HouseListDto houseDto) {
        House house = houseDto.toEntity();
        houseRepository.save(house);

        return house.getHouseId();
    }

    @Transactional
    public int houseUpdateHit(String houseId) {
        return houseRepository.updateHit(houseId);
    }

//    public Object getHouseList(Pageable pageable) {
//
//    }
//
    @Transactional
    public List<HouseListDto> getHouseList() {
        List<House> houseEntities = houseRepository.findAll();
        List<HouseListDto> houseDtoList = new ArrayList<>();

        for(House houseEntity : houseEntities) {
            HouseListDto houseDto = HouseListDto.builder()
                    .houseId(houseEntity.getHouseId())
                    .transactionType(houseEntity.getTransactionType())
                    .floor(houseEntity.getFloor())
                    .totalFloor(houseEntity.getTotalFloor())
                    .roomCount(houseEntity.getRoomCount())
                    .administrationCost(houseEntity.getAdministrationCost())
                    .bathroomCount(houseEntity.getBathroomCount())
                    .direction(houseEntity.getDirection())
                    .heatingSystem(houseEntity.getHeatingSystem())
                    .title(houseEntity.getTitle())
                    .content(houseEntity.getContent())
                    .landArea(houseEntity.getLandArea())
                    .parkingCount(houseEntity.getParkingCount())
                    .purpose(houseEntity.getPurpose())
                    .confirmation(houseEntity.getConfirmation())
                    .serviceType(houseEntity.getServiceType())
                    .monthlyExpenses(houseEntity.getMonthlyExpenses())
                    .build();

            houseDtoList.add(houseDto);
        }

        return houseDtoList;
    }

    @Transactional
    public String save(HouseSaveDto requestDto) {

        return houseRepository.save(requestDto.toEntity()).getHouseId();
    }

    public List<HouseListDto> searchLocationHouse(String keyword) {
        List<House> houseEntities = houseRepository.findByLocationContaining(keyword);
        List<HouseListDto> houseDtoList = new ArrayList<>();

        if (houseEntities.isEmpty()) return houseDtoList;

        for (House houseEntity : houseEntities) {
            houseDtoList.add(this.convertEntityToDto(houseEntity));
        }

        return houseDtoList;
    }

    public List<HouseListDto> searchServiceTypeHouse(String keyword) {
        List<House> houseEntities = houseRepository.findByServiceTypeContaining(keyword);
        List<HouseListDto> houseDtoList = new ArrayList<>();

        if (houseEntities.isEmpty()) return houseDtoList;

        for (House houseEntity : houseEntities) {
            houseDtoList.add(this.convertEntityToDto(houseEntity));
        }

        return houseDtoList;
    }

    public List<HouseListDto> searchTransactionTypeHouse(String keyword) {
        List<House> houseEntities = houseRepository.findByTransactionTypeContaining(keyword);
        List<HouseListDto> houseDtoList = new ArrayList<>();

        if (houseEntities.isEmpty()) return houseDtoList;

        for (House houseEntity : houseEntities) {
            houseDtoList.add(this.convertEntityToDto(houseEntity));
        }

        return houseDtoList;
    }

    public List<HouseListDto> searchMonthlyExpensesHouse(String keyword) {
        List<House> houseEntities = houseRepository.findByMonthlyExpensesContaining(keyword);
        List<HouseListDto> houseDtoList = new ArrayList<>();

        if (houseEntities.isEmpty()) return houseDtoList;

        for (House houseEntity : houseEntities) {
            houseDtoList.add(this.convertEntityToDto(houseEntity));
        }

        return houseDtoList;
    }

    private HouseListDto convertEntityToDto(House houseEntity) {

        return HouseListDto.builder()
                .houseId(houseEntity.getHouseId())
                .transactionType(houseEntity.getTransactionType())
                .floor(houseEntity.getFloor())
                .totalFloor(houseEntity.getTotalFloor())
                .roomCount(houseEntity.getRoomCount())
                .administrationCost(houseEntity.getAdministrationCost())
                .bathroomCount(houseEntity.getBathroomCount())
                .direction(houseEntity.getDirection())
                .heatingSystem(houseEntity.getHeatingSystem())
                .title(houseEntity.getTitle())
                .content(houseEntity.getContent())
                .landArea(houseEntity.getLandArea())
                .parkingCount(houseEntity.getParkingCount())
                .purpose(houseEntity.getPurpose())
                .confirmation(houseEntity.getConfirmation())
                .location(houseEntity.getLocation())
                .serviceType(houseEntity.getServiceType())
                .monthlyExpenses(houseEntity.getMonthlyExpenses())
                .build();
    }


}