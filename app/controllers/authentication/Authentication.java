package controllers.authentication;

import models.User;
import play.data.Form;
import play.data.validation.Constraints;
import play.mvc.Controller;
import play.mvc.Result;
import play.Logger;

import static utils.JsonResponse.*;
import static utils.Permanents.*;

/**
 * Created by Adam Piech on 2016-10-20.
 */

public class Authentication extends Controller {

    private static final Logger.ALogger logger = play.Logger.of("application");

    public Result signup() {
        Form<SignUp> signUpForm = Form.form(SignUp.class).bindFromRequest();
        if (signUpForm.hasErrors()) {
            logger.error(signUpForm.errorsAsJson().asText());
            return badRequest(signUpForm.errorsAsJson());
        }
        SignUp newUser = signUpForm.get();
        User existingUser = User.findByEmail(newUser.email);
        if (existingUser != null) {
            logger.error(USER_EXISTS);
            return badRequest(buildJsonResponse(TYPE_ERROR, USER_EXISTS));
        } else {
            createNewUser(newUser);
            logger.info(USER_CREATED_SUCCESSFULLY + " " + newUser.name);
            return ok(buildJsonResponse(TYPE_SUCCESS, USER_CREATED_SUCCESSFULLY));
        }
    }

    public Result login() {
        Form<Login> loginForm = Form.form(Login.class).bind(request().body().asJson());
        if (loginForm.hasErrors()) {
            logger.error(loginForm.errorsAsJson().asText());
            return badRequest(loginForm.errorsAsJson());
        }
        Login loggingInUser = loginForm.get();
        User user = User.findByEmailAndPassword(loggingInUser.email, loggingInUser.password);
        if (user == null) {
            logger.error(INCORRECT_EMAIL_OR_PASSWORD);
            return badRequest(buildJsonResponse(TYPE_ERROR, INCORRECT_EMAIL_OR_PASSWORD));
        } else {
            session().clear();
            session(SESSION_DATA_LOGIN, user.name);
            logger.info(LOGGED_IN_SUCCESSFULLY + " " + user.name);
            return ok(buildJsonResponse(user, TYPE_SUCCESS, LOGGED_IN_SUCCESSFULLY));
        }
    }

    public Result logout() {
        session().clear();
        logger.info(LOGGED_OUT_SUCCESSFULLY);
        return ok(buildJsonResponse(TYPE_SUCCESS, LOGGED_OUT_SUCCESSFULLY));
    }

    public Result isAuthenticated() {
        if (session().get(SESSION_DATA_LOGIN) == null) {
            return unauthorized(buildJsonResponse(TYPE_ERROR, LOGIN_OR_REGISTER));
        } else {
            return ok(buildJsonResponse(User.findByName(session().get(SESSION_DATA_LOGIN)),
                    TYPE_SUCCESS, USER_IS_LOGGED_IN_ALREADY));
        }
    }

    private static void createNewUser(SignUp newUser) {
        User user = new User(newUser.name, newUser.email, newUser.password);
        user.save();
        session().clear();
        session(SESSION_DATA_LOGIN, user.name);
    }

    public static class UserForm {
        @Constraints.Required
        @Constraints.Email
        public String email;
    }

    public static class SignUp extends UserForm {
        @Constraints.Required
        public String name;

        @Constraints.Required
        @Constraints.MinLength(7)
        public String password;
    }

    public static class Login extends UserForm {
        @Constraints.Required
        public String password;
    }
}