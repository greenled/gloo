package api;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.HeaderNames.CONTENT_TYPE;
import static play.test.Helpers.*;

import java.io.StringReader;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import pegotes.PastesManager;
import play.libs.Json;
import play.libs.WS;
import play.libs.XML;
import play.libs.F.Promise;
import play.mvc.Result;

public class JsonApiTest {
	@Before
	public void clearDB() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				PastesManager.deleteAll();
			}
		});
	}

	@Test
	public void saveActionRespondsBadRequestOnNonXMLContentType() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Result result = route(fakeRequest(POST, "/api/json/"));
				assertThat(status(result)).isEqualTo(BAD_REQUEST);
			}
		});
	}

	@Test
	public void saveActionRespondsBadRequestOnNullContent() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				ObjectNode body = Json.newObject();
				Result result = route(fakeRequest(POST, "/api/json/")
						.withHeader(CONTENT_TYPE, "application/json")
						.withJsonBody(body));
				assertThat(status(result)).isEqualTo(BAD_REQUEST);
			}
		});
	}

	@Test
	public void saveActionRespondsOkOnValidContent() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				ObjectNode body = Json.newObject();
				body.put("content", "Probando el API json");
				Result result = route(fakeRequest(POST, "/api/json/")
						.withHeader(CONTENT_TYPE, "application/json")
						.withJsonBody(body));
				assertThat(status(result)).isEqualTo(CREATED);
				assertThat(contentType(result)).isEqualTo("application/json");
				assertThat(contentAsString(result)).isNotEmpty();
			}
		});
	}

	@Test
	public void viewActionRespondsNotFoundOnNonExistentKey() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Result result = route(fakeRequest(GET,
						"/api/json/nonExistentKey"));
				assertThat(status(result)).isEqualTo(NOT_FOUND);
			}
		});
	}

	@Test
	public void testAll () {
		running(fakeApplication(), new Runnable() {
			public void run() {
				String pasteContentToSend = "Probando el API json";
				ObjectNode body = Json.newObject();
				body.put("content", pasteContentToSend);
				Result result1 = route(fakeRequest(POST, "/api/json/")
						.withHeader(CONTENT_TYPE, "application/json")
						.withJsonBody(body));
				JsonNode node1 = Json.parse(contentAsString(result1));
				String key = node1.findPath("key").asText();
				Result result2 = route(fakeRequest(GET, "/api/json/"+key));
				JsonNode node2 = Json.parse(contentAsString(result2));
				String pasteContentReceived = node2.findPath("content").asText();
				assertThat(pasteContentReceived).isEqualTo(pasteContentToSend);
			}
		});
	}

	@Test
	public void testWithWS () {
		running(testServer(3333), new Runnable() {
			public void run() {
				String pasteContentToSend = "Probando el API json";
				ObjectNode body = Json.newObject();
				body.put("content", pasteContentToSend);
				Promise<WS.Response> result1 = WS.url("http://localhost:3333/api/json/").setHeader(CONTENT_TYPE, "application/json").post(Json.stringify(body));
				String key = result1.get().asJson().findPath("key").asText();
				Promise<WS.Response> result2 = WS.url("http://localhost:3333/api/json/"+key).get();
				String pasteContentReceived = result2.get().asJson().findPath("content").asText();
				assertThat(pasteContentReceived).isEqualTo("Probando el API json");
			}
		});
	}
}
