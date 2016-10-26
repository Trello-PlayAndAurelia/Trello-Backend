package controllers.data;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.Board;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;

import java.util.List;

import static play.libs.Json.toJson;
import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.notFound;
import static play.mvc.Results.ok;

/**
 * Created by Adam Piech on 2016-10-12.
 */
public class BoardController {

    @BodyParser.Of(BodyParser.Json.class)
    public Result createBoard(){
        JsonNode json = request().body().asJson();
        Board board = Json.fromJson(json, Board.class);
        if (board.toString().equals("")){
            return badRequest("Missing parameter");
        }
        board.save();
        return ok();
    }

    public Result getBoard(long id) {
        Board board = Board.find.byId(id);
        if (board == null){
            return notFound("Board not found!");
        }
        return ok(toJson(board));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result updateBoard(long id){
        Board board = Board.find.byId(id);
        if (board == null){
            return notFound("Board not found");
        }
        JsonNode json = request().body().asJson();
        board = Json.fromJson(json, Board.class);
        board.update();
        return ok();
    }

    public Result deleteBoard(long id){
        Board board = Board.find.byId(id);
        if (board == null){
            return notFound("Board not found");
        }
        board.delete();
        return ok();
    }

    public Result listBoards(){
        List<Board> board = new Model.Finder(Board.class).all();
        return ok(toJson(board));
    }
}
