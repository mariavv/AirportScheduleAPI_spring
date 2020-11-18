package mariavv.airportscheduleapispring.mapper;

import mariavv.airportscheduleapispring.domain.dto.FlightDto;
import mariavv.airportscheduleapispring.domain.dto.FlightWithIdDto;
import mariavv.airportscheduleapispring.domain.entity.FlightEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("FlightMapper")
public class FlightMapper implements EntityDtoMapper<FlightEntity, FlightWithIdDto> {

    @Override
    public FlightWithIdDto toDto(FlightEntity entity) {
        FlightEntity postponedOn = entity.getPostponedOn();
        Integer postponedOnId;
        if (postponedOn != null) {
            postponedOnId = postponedOn.getId();
        } else {
            postponedOnId = null;
        }
        return new FlightWithIdDto(
                entity.getId(),
                        new FlightDto(
                                entity.getAirportFrom().getId(), entity.getAirportTo().getId(),
                                entity.getDeparture(), entity.getArrival(),
                                entity.getDelay(), entity.getDelayArrival(),
                                postponedOnId, entity.getIsCanceled())
                );
    }

    @Override
    public List<FlightWithIdDto> toDtoList(List<FlightEntity> entityList) {
        List<FlightWithIdDto> flightsDto = new ArrayList<>();

        for (FlightEntity flight : entityList) {
            flightsDto.add(toDto(flight));
        }
        return flightsDto;
    }
}