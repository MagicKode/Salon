package com.example.salon.model.mapper;

import com.example.salon.model.dto.UserDto;
import com.example.salon.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDto toUserDto(User user);
    User toUser(UserDto userDto);
}
