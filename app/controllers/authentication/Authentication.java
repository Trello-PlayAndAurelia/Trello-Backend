package controllers.authentication;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.User;
import play.data.Form;
import play.data.validation.Constraints;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

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
        if(existingUser != null) {
            return badRequest(buildJsonResponse("error", "User exists"));
        } else {
            User user = new User(newUser.name, newUser.email, newUser.password);
            user.save();
            session().clear();
            session("name", newUser.name);

            return ok(buildJsonResponse("success", "User created successfully"));
        }
    }

    private static ObjectNode buildJsonResponse(String type, String message) {
        ObjectNode wrapper = Json.newObject();
        ObjectNode msg = Json.newObject();
        msg.put("message", message);
        wrapper.put(type, msg);
        return wrapper;
    }

    public Result login() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(loginForm.errorsAsJson());
        }
        Login loggingInUser = loginForm.get();
        User user = getUserFromForm(loggingInUser);
        if(user == null) {
            return badRequest(buildJsonResponse("error", "Incorrect email or password"));
        } else {
            session().clear();
            session("username", loggingInUser.email);

            ObjectNode wrapper = Json.newObject();
            ObjectNode msg = Json.newObject();
            msg.put("message", "Logged in successfully");
            msg.put("user", loggingInUser.email);
            wrapper.put("success", msg);
            return ok(wrapper);
        }
    }

    private static User getUserFromForm(Login loggingInUser) {
        User user = null;
        if (!loggingInUser.email.equals("")) {
            user = User.findByEmailAndPassword(loggingInUser.email, loggingInUser.password);
        }
        if (!loggingInUser.name.equals("")) {
            user = User.findByNameAndPassword(loggingInUser.name, loggingInUser.password);
        }
        return user;
    }

    public Result logout() {
        session().clear();
        return ok(buildJsonResponse("success", "Logged out successfully"));
    }

    public static Result isAuthenticated() {
        if(session().get("user") == null) {
            return unauthorized();
        } else {
            ObjectNode wrapper = Json.newObject();
            ObjectNode msg = Json.newObject();
            msg.put("message", "User is logged in already");
            msg.put("user", session().get("username"));
            wrapper.put("success", msg);
            return ok(wrapper);
        }
    }

    public static class UserForm {
        @Constraints.Required
        @Constraints.Email
        public String email;

        @Constraints.Required
        public String name;
    }

    public static class SignUp extends UserForm {
        @Constraints.Required
        @Constraints.MinLength(7)
        public String password;
    }

    public static class Login extends UserForm {
        @Constraints.Required
        public String password;
    }
}
