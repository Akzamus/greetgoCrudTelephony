package com.akzam.mongoDbCrud.mapper;

import com.akzam.mongoDbCrud.document.User;
import com.akzam.mongoDbCrud.dto.user.UserRequestDto;
import com.akzam.mongoDbCrud.dto.user.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserRequestDto, UserResponseDto> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    User toEntity(UserRequestDto request);

}
