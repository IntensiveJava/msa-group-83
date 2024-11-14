package com.aston.intensive.ticketservice.controller;

import com.aston.intensive.ticketservice.dto.TicketResponseDto;
import com.aston.intensive.ticketservice.service.impl.TicketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TicketController {

    private final TicketServiceImpl ticketServiceImpl;

    @GetMapping("/availableTickets/{tourId}")
    public ResponseEntity<TicketResponseDto> getAvailableTickets(@PathVariable("tourId") String tourId) {
        TicketResponseDto ticketResponseDto = ticketServiceImpl.getAvailableTickets(tourId);
        return ResponseEntity.ok(ticketResponseDto);
    }
}
