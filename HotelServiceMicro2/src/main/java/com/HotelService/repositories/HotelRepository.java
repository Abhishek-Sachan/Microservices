package com.HotelService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HotelService.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
