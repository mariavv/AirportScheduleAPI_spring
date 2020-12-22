package mariavv.airportscheduleapispring.mapper;

import mariavv.airportscheduleapispring.domain.dto.FlightDto;
import mariavv.airportscheduleapispring.domain.dto.request.FlightRequest;
import mariavv.airportscheduleapispring.domain.dto.response.FlightWithIdResponse;
import mariavv.airportscheduleapispring.domain.entity.AirportEntity;
import mariavv.airportscheduleapispring.domain.entity.FlightEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", imports = {AirportEntity.class})
@Component
public interface AnotherFlightMapper {
    
    @Mappings({
    @Mapping(target = "id", ignore = true),
    @Mapping(target = "airportFrom", expression = "java(airportFrom)"),
    @Mapping(target = "airportTo", expression = "java(airportTo)"),
    @Mapping(target = "departure", expression = "java(flightRequest.getDeparture())"),
    @Mapping(target = "arrival", expression = "java(flightRequest.getArrival())"),
    @Mapping(target = "delay", expression = "java(flightRequest.getDelay())"),
    @Mapping(target = "delayArrival", expression = "java(flightRequest.getDelayArrival())"),
    @Mapping(target = "postponedOn", expression = "java(postponedOn)"),
    @Mapping(target = "isCanceled", expression = "java(flightRequest.getIsCanceled())")
    })
    FlightEntity toFlightEntity(FlightRequest flightRequest, AirportEntity airportFrom, AirportEntity airportTo, FlightEntity postponedOn);

    @Mapping(target = "flight", source = "flightEntity")
    FlightWithIdResponse toFlightWithIdDto(FlightEntity flightEntity);

    @Mappings({
            @Mapping(target = "airportFromId", expression = "java(flightEntity.getAirportFrom().getId())"),
            @Mapping(target = "airportToId", expression = "java(flightEntity.getAirportTo().getId())"),
            @Mapping(target = "postponedOn", expression = "java(flightEntity.getPostponedOn() == null ? null : flightEntity.getPostponedOn().getId())")
    })
    FlightDto toFlightDto(FlightEntity flightEntity);

    List<FlightWithIdResponse> toDtoList(List<FlightEntity> entityList);
}
