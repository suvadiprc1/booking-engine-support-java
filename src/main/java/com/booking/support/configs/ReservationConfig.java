package com.booking.support.configs;

import com.booking.support.dtos.Reservation;
import com.booking.support.utils.ProcessReservationData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ReservationConfig {

    
    @Bean
    public Map<String,Reservation> reservationMap()
    {
        final Map<String, Reservation> populateReservationMap = ProcessReservationData.populateReservationMap();
        return populateReservationMap;
        
    }
}
