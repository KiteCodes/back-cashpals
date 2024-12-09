package com.jea.cashpals.mapper;

import com.jea.cashpals.dto.EventDTO;
import com.jea.cashpals.entitiy.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(target = "id", ignore = true)
    Event fromEventDTO(EventDTO source);

    EventDTO fromEvent(Event source);

}
