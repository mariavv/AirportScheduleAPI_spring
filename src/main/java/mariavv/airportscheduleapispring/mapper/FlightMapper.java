package mariavv.airportscheduleapispring.mapper;

import mariavv.airportscheduleapispring.domain.dto.FlightDto;
import mariavv.airportscheduleapispring.domain.dto.request.FlightRequest;
import mariavv.airportscheduleapispring.domain.dto.response.FlightWithIdResponse;
import mariavv.airportscheduleapispring.domain.entity.FlightEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("FlightMapper")
public class FlightMapper implements EntityDtoMapper<FlightEntity, FlightWithIdResponse> {

    @Override
    public FlightWithIdResponse toDto(FlightEntity entity) {
        FlightEntity postponedOn = entity.getPostponedOn();

        return new FlightWithIdResponse(
                entity.getId(),
                        new FlightDto(
                                entity.getAirportFrom().getId(),
                                entity.getAirportTo().getId(),
                                entity.getDeparture(),
                                entity.getArrival(),
                                entity.getDelay(),
                                entity.getDelayArrival(),
                                postponedOn == null ? null : postponedOn.getId(),
                                entity.getIsCanceled()
                        )
                );
    }

    @Override
    public List<FlightWithIdResponse> toDtoList(List<FlightEntity> entityList) {
        List<FlightWithIdResponse> flightsDto = new ArrayList<>(entityList.size());

        for (FlightEntity flight : entityList) {
            flightsDto.add(toDto(flight));
        }
        return flightsDto;
    }
}