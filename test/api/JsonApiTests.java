package api;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

import java.io.StringReader;

import gloo.PastesManager;

import org.codehaus.jackson.node.ObjectNode;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import play.libs.Json;
import play.libs.XML;
import play.mvc.Result;

public class JsonApiTests {
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
				body.put("gloo", "Probando el API json");
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
}
