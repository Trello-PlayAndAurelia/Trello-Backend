package controllers.data;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.User;
import play.libs.Json;
import play.mvc.*;

import java.util.List;

import static play.libs.Json.toJson;
import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.notFound;
import static play.mvc.Results.ok;

/**
 * Created by Adam Piech on 2016-10-12.
 */
public class UserController {

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

    public Result getUser(Integer id) {
        User user = User.find.byId(id);
        if (user == null){
            return notFound("User not found!");
        }
        return ok(toJson(user));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result updateUser(int id){
        User user = User.find.byId(id);
        if (user == null){
            return notFound("User not found");
        }
        JsonNode json = request().body().asJson();
        user = Json.fromJson(json, User.class);
        user.update();
        return ok();
    }

    public Result deleteUser(int id){
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
