package com.aston.intensive.tourservice.controller;

import com.aston.intensive.tourservice.model.Tour;
import com.aston.intensive.tourservice.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tours")
@RequiredArgsConstructor
public class KafkaTourController {

    private final TourService tourService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String sendOrder(@RequestBody Tour tour) {

        tourService.sendTourEvent(tour);

        return "Send Tour to kafka";
    }
}
