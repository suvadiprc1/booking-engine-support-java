package com.booking.support.controllers;

import com.booking.support.dtos.PointOfInterest;
import com.booking.support.dtos.PointOfInterestRequest;
import com.booking.support.dtos.PointOfInterestResponse;
import com.booking.support.services.PointOfInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/booking-engine-poi")
public class PointOfInterestController {
    @Autowired
    private PointOfInterestService pointOfInterestService;

    @RequestMapping(value = "/api/info/getAllPointsOfInterest", method = RequestMethod.POST)
    public PointOfInterestResponse getAllPointsOfInterest(@RequestBody final PointOfInterestRequest pointOfInterestRequest) {
        final List<PointOfInterest> pointsOfInterest =
            pointOfInterestService.getAllPointOfInterestByLatLong(pointOfInterestRequest);
        final PointOfInterestResponse resp = new PointOfInterestResponse();
        resp.setPointOfInterests(pointsOfInterest);
        return resp;
    }

}
