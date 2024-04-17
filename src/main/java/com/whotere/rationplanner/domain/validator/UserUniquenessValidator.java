package com.whotere.rationplanner.domain.validator;

import com.whotere.rationplanner.data.model.User;
import com.whotere.rationplanner.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserUniquenessValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        
        User user = (User) target;
        String username = user.getUsername();

        if(username == null) return;

        Optional<User> userOptional = userRepository.findByUsername(username);

        if(userOptional.isPresent() && !Objects.equals(userOptional.get().getId(), user.getId())) {
            errors.rejectValue(
                    "username",
                    "username.invalid",
                    "Пользователь с таким именем уже существует"
            );
        }
    }
}
