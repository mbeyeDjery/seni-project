package fr.app.seni.core.mapper;

import java.util.List;

public interface EntityMapper<D, E> {
    E toEntity(D dto);
    D toDto(E entity);
    List<D> toDtos(List<E> entityList);
    List<E> toEntities(List<D> dtoList);
}