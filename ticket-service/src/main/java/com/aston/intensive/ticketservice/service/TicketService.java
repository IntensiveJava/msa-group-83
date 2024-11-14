package com.aston.intensive.ticketservice.service;

import com.aston.intensive.ticketservice.dto.TicketResponseDto;

public interface TicketService {

    TicketResponseDto getAvailableTickets(String tourId);
}
