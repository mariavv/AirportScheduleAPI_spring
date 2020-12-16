package mariavv.airportscheduleapispring.mapper;

import mariavv.airportscheduleapispring.domain.dto.FlightDto;
import mariavv.airportscheduleapispring.domain.dto.FlightWithIdDto;
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
    @Mapping(target = "departure", expression = "java(flightDto.getDeparture())"),
    @Mapping(target = "arrival", expression = "java(flightDto.getArrival())"),
    @Mapping(target = "delay", expression = "java(flightDto.getDelay())"),
    @Mapping(target = "delayArrival", expression = "java(flightDto.getDelayArrival())"),
    @Mapping(target = "postponedOn", expression = "java(postponedOn)"),
    @Mapping(target = "isCanceled", expression = "java(flightDto.getIsCanceled())")
    })
    FlightEntity toFlightEntity(FlightDto flightDto, AirportEntity airportFrom, AirportEntity airportTo, FlightEntity postponedOn);

    @Mapping(target = "flight", source = "flightEntity")
    FlightWithIdDto toFlightWithIdDto(FlightEntity flightEntity);

    @Mappings({
            @Mapping(target = "airportFromId", expression = "java(flightEntity.getAirportFrom().getId())"),
            @Mapping(target = "airportToId", expression = "java(flightEntity.getAirportTo().getId())"),
            @Mapping(target = "postponedOn", expression = "java(flightEntity.getPostponedOn() == null ? null : flightEntity.getPostponedOn().getId())")
    })
    FlightDto toFlightDto(FlightEntity flightEntity);

    List<FlightWithIdDto> toDtoList(List<FlightEntity> entityList);
}
