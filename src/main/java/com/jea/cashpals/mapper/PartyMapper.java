package com.jea.cashpals.mapper;


import com.jea.cashpals.dto.PartyDTO;
import com.jea.cashpals.entitiy.Event;
import com.jea.cashpals.entitiy.Party;
import com.jea.cashpals.entitiy.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PartyMapper {
    PartyMapper INSTANCE = Mappers.getMapper(PartyMapper.class);

    @Mapping(source = "owner", target = "owner")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "users", target = "userList")
    @Mapping(source = "events", target = "eventList")
    Party fromPartyDTO(PartyDTO source, User owner, List<User> users, List<Event> events);
    @Mapping(source = "ownerId", target = "ownerId")
    @Mapping(source = "usersIds", target = "usersIds")
    @Mapping(source = "eventsIds", target = "eventsIds")
    PartyDTO fromParty(Party source, Integer ownerId, List<Integer> usersIds, List<Integer> eventsIds);


}
