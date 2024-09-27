package com.unirider.iam.interfaces.acl;

import com.unirider.iam.domain.model.aggregates.User;
import com.unirider.iam.domain.model.commands.SignUpDriverCommand;
import com.unirider.iam.domain.model.commands.SignUpPassengerCommand;
import com.unirider.iam.domain.model.queries.GetUserByIdQuery;
import com.unirider.iam.domain.model.queries.GetUserByUsernameQuery;
import com.unirider.iam.domain.services.UserCommandService;
import com.unirider.iam.domain.services.UserQueryService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * IamContextFacade
 * <p>
 *     This class is a facade for the IAM context. It provides a simple interface for other bounded contexts to interact with the
 *     IAM context.
 *     This class is a part of the ACL layer.
 * </p>
 */
@Service
public class IamContextFacade {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public IamContextFacade(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    /**
     * Creates a developer user with the given username, password, and developer-specific information.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param firstName The first name of the developer.
     * @param lastName The last name of the developer.
     * @param roleNames The names of the roles of the user. When a role does not exist, it is ignored.
     * @return The id of the created user.
     */
    public Long createDriverUser(String username, String password, String firstName, String lastName, String vehiclePlate,List<String> roleNames) {
        var signUpCommand = new SignUpDriverCommand(username, password, firstName, lastName,vehiclePlate);
        var result = userCommandService.handle(signUpCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Creates an enterprise user with the given username, password, and enterprise-specific information.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param roleNames The names of the roles of the user. When a role does not exist, it is ignored.
     * @return The id of the created user.
     */
    public Long createPassengerUser(String username, String password, String firstName, String lastName, List<String> roleNames) {
        var signUpCommand = new SignUpPassengerCommand(username, password,firstName, lastName);
        var result = userCommandService.handle(signUpCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Fetches the id of the user with the given username.
     * @param username The username of the user.
     * @return The id of the user.
     */
    public Long fetchUserIdByUsername(String username) {
        var getUserByUsernameQuery = new GetUserByUsernameQuery(username);
        var result = userQueryService.handle(getUserByUsernameQuery);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Fetches the username of the user with the given id.
     * @param userId The id of the user.
     * @return The username of the user.
     */
    public String fetchUsernameByUserId(Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var result = userQueryService.handle(getUserByIdQuery);
        if (result.isEmpty()) return Strings.EMPTY;
        return result.get().getUsername();
    }

    public User getUserById(Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = this.userQueryService.handle(getUserByIdQuery);
        return user.orElse(null);
    }
}