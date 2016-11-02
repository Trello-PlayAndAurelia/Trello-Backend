package controllers.data;

import com.fasterxml.jackson.databind.JsonNode;
import models.Card;
import models.Comment;
import models.User;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;

import static play.libs.Json.toJson;
import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.notFound;
import static play.mvc.Results.ok;
import static utils.JsonResponse.buildJsonResponse;
import static utils.Permanents.*;

/**
 * Created by Adam Piech on 2016-11-02.
 */
public class CommentController {

    @BodyParser.Of(BodyParser.Json.class)
    public Result addComment() {
        JsonNode json = request().body().asJson();
        Comment comment = Json.fromJson(json, Comment.class);
        if (comment.toString().equals("")){
            return badRequest(buildJsonResponse(TYPE_ERROR, ""));
        }
        if (Card.find.byId(comment.cardId) == null){
            return notFound(buildJsonResponse(TYPE_ERROR, CARD_NOT_FOUND));
        }
        if (User.find.byId(comment.userId) == null){
            return notFound(buildJsonResponse(TYPE_ERROR, USER_NOT_FOUND));
        }
        comment.save();
        return ok(buildJsonResponse(TYPE_SUCCESS, COMMENT_ADDED_SUCCESSFULLY));
    }

    public Result getComments(long cardId) {
        if (Card.find.byId(cardId) == null){
            return notFound(buildJsonResponse(TYPE_ERROR, CARD_NOT_FOUND));
        }
        return ok(toJson(Comment.find
                .where()
                .eq("cardId", cardId)
                .findList()));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result updateComment(long id) {
        Comment comment = Comment.find.byId(id);
        if (comment == null){
            return notFound(buildJsonResponse(TYPE_ERROR, COMMENT_NOT_FOUND));
        }
        JsonNode json = request().body().asJson();
        comment = Json.fromJson(json, Comment.class);
        comment.update();
        return ok(buildJsonResponse(TYPE_SUCCESS, COMMENT_UPDATED_SUCCESSFULLY));
    }

    public Result deleteComment(long id) {
        Comment comment = Comment.find.byId(id);
        if (comment == null){
            return notFound(buildJsonResponse(TYPE_ERROR, COMMENT_NOT_FOUND));
        }
        comment.delete();
        return ok(buildJsonResponse(TYPE_SUCCESS, COMMENT_DELETED_SUCCESSFULLY));
    }
}
