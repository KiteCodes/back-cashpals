package com.jea.cashpals.mapper;

import com.jea.cashpals.dto.EventDTO;
import com.jea.cashpals.entitiy.Event;
import com.jea.cashpals.entitiy.Transaction;
import com.jea.cashpals.entitiy.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "creator", target = "creator")
    @Mapping(source = "transactionList", target = "transactionList")
    Event fromEventDTO(EventDTO source, User creator, List<Transaction> transactionList);


    @Mapping(source = "transactionIds", target = "transactionIds")
    @Mapping(source = "creatorId", target = "creatorId")
    @Mapping(source = "memberListIds", target = "usersIds")
    EventDTO fromEvent(Event source, Integer creatorId, List<Integer> transactionIds, List<Integer> memberListIds);

}
