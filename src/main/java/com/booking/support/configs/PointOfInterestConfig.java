package com.booking.support.configs;

import com.booking.support.dtos.Location;
import com.booking.support.dtos.PointOfInterest;
import com.booking.support.utils.ProcessPoiData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class PointOfInterestConfig {

    
    @Bean
    public Map<Location, List<PointOfInterest>> poiMap()
    {
        final Map<Location, List<PointOfInterest>> poiMap = ProcessPoiData.populatePointOfInterestMap();
        return poiMap;
        
    }
}
