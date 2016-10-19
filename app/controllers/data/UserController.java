package controllers.data;


import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.User;
import play.libs.Json;
import play.mvc.*;

import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by Adam Piech on 2016-10-12.
 */
public class UserController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public Result createUser(){
        JsonNode json = request().body().asJson();
        User user = Json.fromJson(json, User.class);
        if (user.toString().equals("")){
            return badRequest("Missing parameter");
        }
        user.save();
        return ok();
    }

    public Result getUser(long id) {
        User user = User.find.byId(id);
        if (user == null){
            return notFound("User not found!");
        }
        return ok(toJson(user));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result updateUser(long id){
        User user = User.find.byId(id);
        if (user == null){
            return notFound("User not found");
        }
        JsonNode json = request().body().asJson();
        user = Json.fromJson(json, User.class);
        user.update();
        return ok();
    }

    public Result deleteUser(long id){
        User user = User.find.byId(id);
        if (user == null){
            return notFound("User not found");
        }
        user.delete();
        return ok();
    }

    public Result listUsers(){
        List<User> users = new Model.Finder(User.class).all();
        return ok(toJson(users));
    }

}
