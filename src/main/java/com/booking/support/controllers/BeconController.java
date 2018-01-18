package com.booking.support.controllers;

import com.booking.support.dtos.DeviceInformation;
import com.booking.support.dtos.GetBeconRequest;
import com.booking.support.dtos.RegistrationInformation;
import com.booking.support.services.BeconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking-engine-poi")
public class BeconController {

    @Autowired
    private BeconService beconService;

    @RequestMapping(value = "/api/info/addBecons", method = RequestMethod.POST, produces = "application/json")
    public RegistrationInformation addBecons(@RequestBody final DeviceInformation deviceInformation) {
        return beconService.addBecons(deviceInformation);
    }

    @RequestMapping(value = "/api/info/isBeconValid", method = RequestMethod.POST)
    public RegistrationInformation isBeconValid(@RequestBody final GetBeconRequest getBeconRequest) {
        return beconService.isBeconPresent(getBeconRequest);
    }
}
