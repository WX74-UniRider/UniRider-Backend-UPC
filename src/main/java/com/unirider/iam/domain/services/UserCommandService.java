package com.unirider.iam.domain.services;

import com.unirider.iam.domain.model.aggregates.User;
import com.unirider.iam.domain.model.commands.SignInCommand;
import com.unirider.iam.domain.model.commands.SignUpDriverCommand;
import com.unirider.iam.domain.model.commands.SignUpPassengerCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;


public interface UserCommandService {
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
    //Optional<User> handle(SignUpCommand command);
    Optional<User> handle(SignUpDriverCommand command);
    Optional<User> handle(SignUpPassengerCommand command);
}
