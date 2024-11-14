package com.aston.intensive.ticketservice.dto;

import lombok.Builder;

@Builder
public record TourDto(
        String id,
        String tourName,
        String contactName,
        String contactPhone
) {
}