package com.toyproject.realty.repository;

import com.toyproject.realty.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {

    @Modifying
    @Query("update House h set h.hit = h.hit + 1 where h.houseId = :houseId")
    int updateHit(String houseId);
}