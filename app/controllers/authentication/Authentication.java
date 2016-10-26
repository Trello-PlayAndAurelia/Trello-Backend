package controllers.authentication;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.User;
import play.data.Form;
import play.data.validation.Constraints;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import static play.libs.Json.toJson;
import static utils.Permanents.*;

/**
 * Created by Adam Piech on 2016-10-20.
 */

public class Authentication extends Controller {

    public Result signup() {
        Form<SignUp> signUpForm = Form.form(SignUp.class).bindFromRequest();
        if (signUpForm.hasErrors()) {
            return badRequest(signUpForm.errorsAsJson());
        }
        SignUp newUser = signUpForm.get();
        User existingUser = User.findByEmail(newUser.email);
        if (existingUser != null) {
            return badRequest(buildJsonResponse(TYPE_ERROR, USER_EXISTS));
        } else {
            createNewUser(newUser);
            return ok(buildJsonResponse(TYPE_SUCCESS, USER_CREATED_SUCCESSFULLY));
        }
    }

    public Result login() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(loginForm.errorsAsJson());
        }
        Login loggingInUser = loginForm.get();
        User user = User.findByEmailAndPassword(loggingInUser.email, loggingInUser.password);
        if (user == null) {
            return badRequest(buildJsonResponse(TYPE_ERROR, INCORRECT_EMAIL_OR_PASSWORD));
        } else {
            session().clear();
            session(SESSION_DATA_LOGIN, user.name);
            return ok(buildJsonResponse(user, TYPE_SUCCESS, LOGGED_IN_SUCCESSFULLY));
        }
    }

    public Result logout() {
        session().clear();
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

    private static ObjectNode buildJsonResponse(User user, String type, String message) {
        ObjectNode wrapper = Json.newObject();
        ObjectNode msg = Json.newObject();
        msg.put(MESSAGE, message);
        msg.put(TYPE_USER, toJson(user));
        wrapper.put(type, msg);
        return wrapper;
    }

    private static ObjectNode buildJsonResponse(String type, String message) {
        ObjectNode wrapper = Json.newObject();
        ObjectNode msg = Json.newObject();
        msg.put(MESSAGE, message);
        wrapper.put(type, msg);
        return wrapper;
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
