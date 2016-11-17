import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Board;
import org.junit.Test;
import play.libs.Json;
import play.mvc.Http.RequestBuilder;
import play.mvc.Result;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static play.mvc.Results.*;
import static play.test.Helpers.*;

public class IntegrationTest {

    @Test
    public void messageTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, browser -> {
            browser.goTo("http://localhost:3333");
            assertTrue(browser.pageSource().contains("Trello"));
        });
    }

    @Test
    public void testBadRoute() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                RequestBuilder request = new RequestBuilder()
                        .method(GET)
                        .uri("/notFount");
                Result result = route(request);
                assertEquals(NOT_FOUND, result.status());
            }
        });
    }

    @Test
    public void testNotLogged() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                RequestBuilder request = new RequestBuilder()
                        .method(GET)
                        .uri("/authentication");
                Result result = route(request);
                assertEquals(unauthorized().status(), result.status());
            }
        });
    }

    @Test
    public void testLoggedSuccessful() {
        ObjectNode wrapper = Json.newObject();
        wrapper.put("email", "user@user.pl");
        wrapper.put("password", "user123");

        running(fakeApplication(), new Runnable() {
            public void run() {
                RequestBuilder request = new RequestBuilder()
                        .method(POST)
                        .uri("/login")
                        .bodyJson(wrapper);
                Result result = route(request);
                assertEquals(ok().status(), result.status());
            }
        });
    }

    @Test
    public void testSaveBoardToDatabase() {
        Board board = new Board();
        board.name = "fakeBoard";

        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                board.save();
                Board dbBoard = Board.find.byId(board.id);
                
                assertNotNull(dbBoard);
                assertEquals(dbBoard.name, board.name);
            }
        });
    }

}
