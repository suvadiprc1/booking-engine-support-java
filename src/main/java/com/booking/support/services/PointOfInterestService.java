package com.booking.support.services;

import com.booking.support.dtos.Location;
import com.booking.support.dtos.PointOfInterest;
import com.booking.support.dtos.PointOfInterestRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PointOfInterestService {
    @Resource
    private Map<Location, List<PointOfInterest>> poiMap;

    public List<PointOfInterest> getAllPointOfInterestByLatLong(final PointOfInterestRequest request) {
        final List<PointOfInterest> poiList = new ArrayList<PointOfInterest>();
        final Location location = request.getLocation();
        if (poiMap.containsKey(location)) {
            poiList.addAll(poiMap.get(location));
        }
        return poiList;
    }
}
