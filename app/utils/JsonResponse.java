package utils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Board;
import models.Card;
import models.List;
import models.User;
import play.libs.Json;

import static play.libs.Json.toJson;
import static utils.Permanents.*;

/**
 * Created by Adam Piech on 2016-11-02.
 */
public class JsonResponse {

    public static ObjectNode buildJsonResponse(User user, String type, String message) {
        ObjectNode wrapper = Json.newObject();
        ObjectNode msg = Json.newObject();
        msg.put(MESSAGE, message);
        msg.put(TYPE_USER, toJson(user));
        wrapper.put(type, msg);
        return wrapper;
    }

    public static ObjectNode buildJsonResponse(Board board, String type, String message) {
        ObjectNode wrapper = Json.newObject();
        ObjectNode msg = Json.newObject();
        msg.put(MESSAGE, message);
        msg.put(TYPE_BOARD, toJson(board));
        wrapper.put(type, msg);
        return wrapper;
    }

    public static ObjectNode buildJsonResponse(List list, String type, String message) {
        ObjectNode wrapper = Json.newObject();
        ObjectNode msg = Json.newObject();
        msg.put(MESSAGE, message);
        msg.put(TYPE_LIST, toJson(list));
        wrapper.put(type, msg);
        return wrapper;
    }

    public static ObjectNode buildJsonResponse(Card card, String type, String message) {
        ObjectNode wrapper = Json.newObject();
        ObjectNode msg = Json.newObject();
        msg.put(MESSAGE, message);
        msg.put(TYPE_CARD, toJson(card));
        wrapper.put(type, msg);
        return wrapper;
    }


    public static ObjectNode buildJsonResponse(String type, String message) {
        ObjectNode wrapper = Json.newObject();
        ObjectNode msg = Json.newObject();
        msg.put(MESSAGE, message);
        wrapper.put(type, msg);
        return wrapper;
    }
}
