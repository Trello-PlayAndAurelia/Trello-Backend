package controllers.authentication;

import play.mvc.Result;
import play.mvc.Security;

import static play.mvc.Http.*;

/**
 * Created by Adam Piech on 2016-10-20.
 */

public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("user");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return unauthorized();
    }
}