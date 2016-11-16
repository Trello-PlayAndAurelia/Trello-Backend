package controllers.data;

import com.fasterxml.jackson.databind.JsonNode;
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

//    @BodyParser.Of(BodyParser.Json.class)
//    public Result createList() {
//        JsonNode json = request().body().asJson();
//        Board board = Json.fromJson(json, Board.class);
//        if (board.toString().equals("")){
//            return badRequest(buildJsonResponse(TYPE_ERROR, BOARD_NOT_FOUND));
//        }
//        if (User.find.byId(board.userId) == null){
//            return notFound(buildJsonResponse(TYPE_ERROR, USER_NOT_FOUND));
//        }
//        board.save();
//        return ok(buildJsonResponse(TYPE_SUCCESS, BOARD_CREATED_SUCCESSFULLY));
//    }

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

//    public Result listLists(long boardId) {
//        List<Board> board = new Model.Finder(Board.class).all();
//        return ok(toJson(board));
//    }

//    public Result getLists(long userId) {
//        if (User.find.byId(userId) == null){
//            return notFound(buildJsonResponse(TYPE_ERROR, USER_NOT_FOUND));
//        }
//        return ok(toJson(Board.find
//                .where()
//                .eq("idUser", userId)
//                .findList()));
//    }
}
