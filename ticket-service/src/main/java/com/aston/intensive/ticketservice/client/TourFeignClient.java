package com.aston.intensive.ticketservice.client;

import com.aston.intensive.ticketservice.dto.TourDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("tour-service")
public interface TourFeignClient {

    @GetMapping(value = "/new/tour/{tourId}", consumes = "application/json")
    TourDto getTourById(@PathVariable("tourId") String tourId);
}
