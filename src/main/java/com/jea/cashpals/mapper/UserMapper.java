package com.jea.cashpals.mapper;

import com.jea.cashpals.dto.UserDTO;
import com.jea.cashpals.entitiy.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    User fromUserDTO(UserDTO source);

    UserDTO fromUser(User source);
}
