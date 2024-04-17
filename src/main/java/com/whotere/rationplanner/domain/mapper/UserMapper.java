package com.whotere.rationplanner.domain.mapper;

import com.whotere.rationplanner.data.model.User;
import com.whotere.rationplanner.representation.dto.UserDto;
import com.whotere.rationplanner.representation.dto.form.UserAuthenticationForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    User toUser (UserAuthenticationForm userAuthenticationForm);
}
