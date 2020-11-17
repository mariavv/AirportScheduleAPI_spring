package mariavv.airportscheduleapispring.mapper;

import java.util.List;

public interface EntityDtoMapper<E, D> {
    E toEntity(D dto);
    List<E> toEntityList(List<D> dtoList);
    D toDto(E entity);
    List<D> toDtoList(List<E> entityList);
}