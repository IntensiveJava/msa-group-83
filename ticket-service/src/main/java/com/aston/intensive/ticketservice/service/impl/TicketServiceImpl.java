package com.aston.intensive.ticketservice.service.impl;

import com.aston.intensive.ticketservice.client.TourFeignClient;
import com.aston.intensive.ticketservice.dto.TicketResponseDto;
import com.aston.intensive.ticketservice.dto.TourDto;
import com.aston.intensive.ticketservice.service.TicketService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TourFeignClient tourFeignClient;

    @CircuitBreaker(name = "findTourById", fallbackMethod = "buildFallbackTourDto")
    @Retry(name = "retryFindTourById", fallbackMethod = "buildFallbackTourDto")
    @Bulkhead(name = "bulkheadFindTourById", fallbackMethod = "buildFallbackTourDto")
    public TicketResponseDto getAvailableTickets(String tourId){
        TourDto tourDto = tourFeignClient.getTourById(tourId);

        // Обрабатываем tourDto
        // формируем инфу о билетах

        List<String> tickets = List.of("Ticket1", "Ticket2", "Ticket3");

        return TicketResponseDto.builder()
                .id(tourId)
                .tourName(tourDto.tourName())
                .contactName(tourDto.contactName())
                .contactPhone(tourDto.contactPhone())
                .tickets(tickets)
                .isResponseValid(true)
                .message("recommendations can be here")
                .build();
    }

    @SuppressWarnings("unused")
    public TicketResponseDto buildFallbackTourDto(String tourId, Exception t) {
        log.error("Fallback method worked. TourDto is not received", t);

        return TicketResponseDto.builder()
                .contactName("support service")
                .contactPhone("8-800-00-00-00")
                .isResponseValid(false)
                .message("sorry, but service is not available now")
                .build();
    }

// fallback метод должен соответствовать нескольким критериям:
// - возвращать тот же тип данных, что и исходный метод
// - принимать исключение в качестве параметра, а также может принимать те же параметры что и основной метод
}
