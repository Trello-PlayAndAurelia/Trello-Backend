package controllers.data;

import com.fasterxml.jackson.databind.JsonNode;
import models.Board;
import models.List;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;

import static play.libs.Json.toJson;
import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.notFound;
import static play.mvc.Results.ok;
import static utils.JsonResponse.*;
import static utils.Permanents.*;

/**
 * Created by Adam Piech on 2016-11-16.
 */
public class ListController {

    @BodyParser.Of(BodyParser.Json.class)
    public Result createList(long boardId) {
        Board board = Board.find.byId(boardId);
        if (board == null){
            return notFound(buildJsonResponse(TYPE_ERROR, BOARD_NOT_FOUND));
        }
        JsonNode json = request().body().asJson();
        List list = Json.fromJson(json, List.class);
        if (list.toString().equals("")){
            return badRequest(buildJsonResponse(TYPE_ERROR, LIST_NOT_FOUND));
        }
        board.lists.add(list);
        board.update();
        return ok(buildJsonResponse(TYPE_SUCCESS, LIST_CREATED_SUCCESSFULLY));
    }

    public Result getList(long id) {
        List list = List.find.byId(id);
        if (list == null){
            return notFound(buildJsonResponse(TYPE_ERROR, LIST_NOT_FOUND));
        }
        return ok(toJson(list));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result updateList(long id) {
        List list = List.find.byId(id);
        if (list == null){
            return notFound(buildJsonResponse(TYPE_ERROR, LIST_NOT_FOUND));
        }
        JsonNode json = request().body().asJson();
        list = Json.fromJson(json, List.class);
        list.update();
        return ok(buildJsonResponse(TYPE_SUCCESS, LIST_UPDATED_SUCCESSFULLY));
    }

    public Result deleteList(long id) {
        List list = List.find.byId(id);
        if (list == null){
            return notFound(buildJsonResponse(TYPE_ERROR, LIST_NOT_FOUND));
        }
        list.delete();
        return ok(buildJsonResponse(TYPE_SUCCESS, LIST_DELETED_SUCCESSFULLY));
    }

    public Result getLists(long boardId) {
        Board board = Board.find.byId(boardId);
        if (board == null){
            return notFound(buildJsonResponse(TYPE_ERROR, BOARD_NOT_FOUND));
        }
        return ok(toJson(board.lists));
    }

}
