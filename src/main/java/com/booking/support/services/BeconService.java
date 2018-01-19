package com.booking.support.services;

import com.booking.support.dtos.DeviceInformation;
import com.booking.support.dtos.GetBeconRequest;
import com.booking.support.dtos.RegistrationInformation;
import com.booking.support.utils.ProcessBeconData;
import org.springframework.stereotype.Service;

@Service
public class BeconService {

    public RegistrationInformation addBecons(final DeviceInformation deviceInformation) {
        final ProcessBeconData processBeconData = new ProcessBeconData();
        final RegistrationInformation registrationInformation = new RegistrationInformation();
        registrationInformation.setRegistertered(processBeconData.addBecon(deviceInformation));
        return registrationInformation;
    }

    public RegistrationInformation isBeconPresent(final GetBeconRequest getBeconRequest) {
        final ProcessBeconData processBeconData = new ProcessBeconData();
        final RegistrationInformation registrationInformation = new RegistrationInformation();
        return processBeconData.findBecon(getBeconRequest);
    }
}
