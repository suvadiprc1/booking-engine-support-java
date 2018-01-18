package com.booking.support.controllers;

import com.booking.support.dtos.Reservation;
import com.booking.support.dtos.ReservationRequest;
import com.booking.support.dtos.ReservationResponse;
import com.booking.support.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/booking-engine-reservation")
public class BookingReservationController {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(value = "/api/info/getCurrentandFutureReservations", method = RequestMethod.POST)
    public ReservationResponse getCurrentandFutureReservations(@RequestBody final ReservationRequest reservationRequest) {
        final List<Reservation> currentandFutureReservations =
            reservationService.getCurrentandFutureReservations(reservationRequest);
        final ReservationResponse resp = new ReservationResponse();
        resp.setReservations(currentandFutureReservations);
        return resp;
    }

}
