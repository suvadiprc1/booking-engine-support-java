package com.booking.support.services;

import com.booking.support.dtos.Reservation;
import com.booking.support.dtos.ReservationRequest;
import com.booking.support.utils.ProcessReservationData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ReservationService {
    @Resource
    private Map<String, Reservation> reservationMap;

    public List<Reservation> getCurrentandFutureReservations(final ReservationRequest reservationRequest) {
        final List<Reservation> reservations = new ArrayList<Reservation>();
        final List<String> reservationIds = ProcessReservationData.getReservationIds(reservationRequest.getMemberId(),
            reservationRequest.getLatitude(), reservationRequest.getLongitude());
        final Set<String> reservationKey = reservationMap.keySet();
        for (String reservationId : reservationIds) {
            if (reservationKey.contains(reservationId)) {
                reservations.add(reservationMap.get(reservationId));
            }
        }
        return reservations;

    }

}
