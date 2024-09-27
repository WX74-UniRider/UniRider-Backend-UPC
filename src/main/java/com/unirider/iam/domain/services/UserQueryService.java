package com.unirider.iam.domain.services;

import com.unirider.iam.domain.model.aggregates.User;
import com.unirider.iam.domain.model.queries.GetAllUsersQuery;
import com.unirider.iam.domain.model.queries.GetUserByIdQuery;
import com.unirider.iam.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> handle(GetUserByUsernameQuery query);}
