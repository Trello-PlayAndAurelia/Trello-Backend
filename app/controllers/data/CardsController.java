package controllers.data;

import com.fasterxml.jackson.databind.JsonNode;
import models.Board;
import models.Card;
import models.List;
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
import static utils.Permanents.BOARD_NOT_FOUND;

/**
 * Created by Adam Piech on 2016-11-16.
 */
public class CardsController {

    @BodyParser.Of(BodyParser.Json.class)
    public Result createCard(long listId) {
        List list = List.find.byId(listId);
        if (list == null){
            return notFound(buildJsonResponse(TYPE_ERROR, LIST_NOT_FOUND));
        }
        JsonNode json = request().body().asJson();
        Card card = Json.fromJson(json, Card.class);
        if (card.toString().equals("")){
            return badRequest(buildJsonResponse(TYPE_ERROR, CARD_NOT_FOUND));
        }
        list.cards.add(card);
        list.update();
        return ok(buildJsonResponse(TYPE_SUCCESS, CARD_CREATED_SUCCESSFULLY));
    }

    public Result getCard(long id) {
        Card card = Card.find.byId(id);
        if (card == null){
            return notFound(buildJsonResponse(TYPE_ERROR, CARD_NOT_FOUND));
        }
        return ok(toJson(card));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result updateCard(long id) {
        Card card = Card.find.byId(id);
        if (card == null){
            return notFound(buildJsonResponse(TYPE_ERROR, CARD_NOT_FOUND));
        }
        JsonNode json = request().body().asJson();
        card = Json.fromJson(json, Card.class);
        card.update();
        return ok(buildJsonResponse(TYPE_SUCCESS, CARD_UPDATED_SUCCESSFULLY));
    }

    public Result deleteCard(long id) {
        Card card = Card.find.byId(id);
        if (card == null){
            return notFound(buildJsonResponse(TYPE_ERROR, CARD_NOT_FOUND));
        }
        card.delete();
        return ok(buildJsonResponse(TYPE_SUCCESS, CARD_DELETED_SUCCESSFULLY));
    }

    public Result getCards(long listId) {
        List list = List.find.byId(listId);
        if (list == null){
            return notFound(buildJsonResponse(TYPE_ERROR, BOARD_NOT_FOUND));
        }
        return ok(toJson(list.cards));
    }

}
