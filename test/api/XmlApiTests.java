package api;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

import java.io.StringReader;

import gloo.PastesManager;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import play.libs.XML;
import play.mvc.Result;

public class XmlApiTests {
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
				Result result = route(fakeRequest(POST, "/api/xml/"));
				assertThat(status(result)).isEqualTo(BAD_REQUEST);
			}
		});
	}

	@Test
	public void saveActionRespondsBadRequestOnNullContent() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Result result = route(fakeRequest(POST, "/api/xml/").withHeader(CONTENT_TYPE, "text/xml"));
				assertThat(status(result)).isEqualTo(BAD_REQUEST);
			}
		});
	}

	@Test
	public void saveActionRespondsOkOnValidContent() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Result result = route(fakeRequest(POST, "/api/xml/")
						.withHeader(CONTENT_TYPE, "text/xml").withXmlBody(
								new InputSource (new StringReader ( "<gloo>Probando el API xml</gloo>" ))));
				assertThat(status(result)).isEqualTo(CREATED);
				assertThat(contentType(result)).isEqualTo("text/xml");
				assertThat(contentAsString(result)).isNotEmpty();
			}
		});
	}

	@Test
	public void rawActionRespondsNotFoundOnNonExistentKey() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Result result = route(fakeRequest(GET, "/api/xml/nonExistentKey"));
				assertThat(status(result)).isEqualTo(NOT_FOUND);
			}
		});
	}
}
