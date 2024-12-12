package com.jea.cashpals.mapper;


import com.jea.cashpals.dto.PartyDTO;
import com.jea.cashpals.entitiy.Party;
import com.jea.cashpals.entitiy.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PartyMapper {
    PartyMapper INSTANCE = Mappers.getMapper(PartyMapper.class);

    @Mapping(source = "user", target = "owner")
    @Mapping(target = "id", ignore = true)
    Party fromPartyDTO(PartyDTO source, User user);
    @Mapping(source = "owner.id", target = "ownerId")
    PartyDTO fromParty(Party source);
}
