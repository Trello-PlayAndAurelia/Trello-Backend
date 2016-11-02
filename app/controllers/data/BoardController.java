package controllers.data;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.Board;
import models.User;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;

import java.util.List;

import static play.libs.Json.toJson;
import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.notFound;
import static play.mvc.Results.ok;
import static utils.JsonResponse.*;
import static utils.Permanents.*;

/**
 * Created by Adam Piech on 2016-10-12.
 */
public class BoardController {

    @BodyParser.Of(BodyParser.Json.class)
    public Result createBoard() {
        JsonNode json = request().body().asJson();
        Board board = Json.fromJson(json, Board.class);
        if (board.toString().equals("")){
            return badRequest(buildJsonResponse(TYPE_ERROR, BOARD_NOT_FOUND));
        }
        if (User.find.byId(board.userId) == null){
            return notFound(buildJsonResponse(TYPE_ERROR, USER_NOT_FOUND));
        }
        board.save();
        return ok(buildJsonResponse(TYPE_SUCCESS, BOARD_CREATED_SUCCESSFULLY));
    }

    public Result getBoard(long id) {
        Board board = Board.find.byId(id);
        if (board == null){
            return notFound(buildJsonResponse(TYPE_ERROR, BOARD_NOT_FOUND));
        }
        return ok(toJson(board));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result updateBoard(long id) {
        Board board = Board.find.byId(id);
        if (board == null){
            return notFound(buildJsonResponse(TYPE_ERROR, BOARD_NOT_FOUND));
        }
        JsonNode json = request().body().asJson();
        board = Json.fromJson(json, Board.class);
        board.update();
        return ok(buildJsonResponse(TYPE_SUCCESS, BOARD_UPDATED_SUCCESSFULLY));
    }

    public Result deleteBoard(long id) {
        Board board = Board.find.byId(id);
        if (board == null){
            return notFound(buildJsonResponse(TYPE_ERROR, BOARD_NOT_FOUND));
        }
        board.delete();
        return ok(buildJsonResponse(TYPE_SUCCESS, BOARD_DELETED_SUCCESSFULLY));
    }

    public Result listBoards() {
        List<Board> board = new Model.Finder(Board.class).all();
        return ok(toJson(board));
    }

    public Result getBoards(long userId) {
        if (User.find.byId(userId) == null){
            return notFound(buildJsonResponse(TYPE_ERROR, USER_NOT_FOUND));
        }
        return ok(toJson(Board.find
                .where()
                .eq("idUser", userId)
                .findList()));
    }

}
