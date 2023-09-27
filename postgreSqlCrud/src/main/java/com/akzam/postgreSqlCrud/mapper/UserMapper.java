package com.akzam.postgreSqlCrud.mapper;

import com.akzam.postgreSqlCrud.entity.User;
import com.akzam.postgreSqlCrud.dto.user.UserRequestDto;
import com.akzam.postgreSqlCrud.dto.user.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserRequestDto, UserResponseDto> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    User toEntity(UserRequestDto request);

}
