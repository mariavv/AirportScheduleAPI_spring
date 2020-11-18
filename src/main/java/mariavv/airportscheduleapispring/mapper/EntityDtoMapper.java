package mariavv.airportscheduleapispring.mapper;

import java.util.List;

public interface EntityDtoMapper<E, D> {
    D toDto(E entity);
    List<D> toDtoList(List<E> entityList);
}