package controllers;
import java.util.Map;
import java.util.TreeMap;

import org.junit.*;

import pegotes.PastesManager;
import play.mvc.*;
import play.test.*;
import play.api.http.ContentTypes;
import play.libs.F.*;

import static play.mvc.Http.Status.BAD_REQUEST;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {
	@Before
	public void clearDB() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				PastesManager.deleteAll();
			}
		});
	}

	@Test
	public void addActionAllwaysRespondsOk() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Result result = route(fakeRequest(GET, "/"));
				assertThat(status(result)).isEqualTo(OK);
				assertThat(contentType(result)).isEqualTo("text/html");
			}
		});
	}

	@Test
	public void saveActionRespondsBadRequestOnNullContent() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Result result = route(fakeRequest(POST, "/"));
				assertThat(status(result)).isEqualTo(BAD_REQUEST);
				assertThat(contentType(result)).isEqualTo("text/html");
			}
		});
	}

	@Test
	public void saveActionRespondsBadRequestOnWrongContent() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Result result = route(fakeRequest(POST, "/").withTextBody("Algo de texto erróneo"));
				assertThat(status(result)).isEqualTo(BAD_REQUEST);
				assertThat(contentType(result)).isEqualTo("text/html");
			}
		});
	}

	@Test
	public void saveActionRespondsSeeOtherOnRightContent() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Map<String, String> args = new TreeMap<>();
				args.put("content", "Algo de texto correcto");
				Result result = route(fakeRequest(POST, "/").withFormUrlEncodedBody(args));
				System.out.println(contentAsString(result));
				assertThat(status(result)).isEqualTo(SEE_OTHER);
			}
		});
	}

	@Test
	public void viewActionRespondsNotFoundOnInexistentKey() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Result result = route(fakeRequest(GET, "/impossible*key"));
				assertThat(status(result)).isEqualTo(NOT_FOUND);
				assertThat(contentType(result)).isEqualTo("text/html");
			}
		});
	}

	@Test
	public void viewActionRespondsOkOnValidKey() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Map<String, String> args = new TreeMap<>();
				args.put("content", "Algo de texto correcto");
				Result result1 = route(fakeRequest(POST, "/").withFormUrlEncodedBody(args));
				String location = redirectLocation(result1);
				Result result2 = route(fakeRequest(GET, location));
				assertThat(status(result2)).isEqualTo(OK);
				assertThat(contentType(result2)).isEqualTo("text/html");
			}
		});
	}

	@Test
	public void rawActionRespondsNotFoundOnInexistentKey() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Result result = route(fakeRequest(GET, "/raw/impossible*key"));
				assertThat(status(result)).isEqualTo(NOT_FOUND);
				assertThat(contentType(result)).isEqualTo("text/html");
			}
		});
	}

	@Test
	public void rawActionRespondsOkOnValidKey() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Map<String, String> args = new TreeMap<>();
				args.put("content", "Algo de texto");
				Result result1 = route(fakeRequest(POST, "/").withFormUrlEncodedBody(args));
				String location = redirectLocation(result1);
				Result result2 = route(fakeRequest(GET, "/raw"+location));
				assertThat(status(result2)).isEqualTo(OK);
				assertThat(contentType(result2)).isEqualTo("application/octet-stream");
			}
		});
	}

	@Test
	public void infoActionAllwaysRespondsOk() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Result result = route(fakeRequest(GET, "/info"));
				assertThat(status(result)).isEqualTo(OK);
				assertThat(contentType(result)).isEqualTo("text/html");
			}
		});
	}

	@Test
	public void deleteActionRespondsNotFoundOnNoKey() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Result result = route(fakeRequest(GET, "/delete"));
				assertThat(status(result)).isEqualTo(NOT_FOUND);
				assertThat(contentType(result)).isEqualTo("text/html");
			}
		});
	}

	@Test
	public void deleteActionRespondsNotFoundOnInexistentKey() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Result result = route(fakeRequest(GET, "/delete/impossible*key"));
				assertThat(status(result)).isEqualTo(NOT_FOUND);
				assertThat(contentType(result)).isEqualTo("text/html");
			}
		});
	}

	@Test
	public void deleteActionRespondsOkOnValidKey() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Map<String, String> args = new TreeMap<>();
				args.put("content", "Algo de texto");
				Result result1 = route(fakeRequest(POST, "/").withFormUrlEncodedBody(args));
				String location = redirectLocation(result1);
				Result result2 = route(fakeRequest(GET, "/delete"+location));
				assertThat(status(result2)).isEqualTo(SEE_OTHER);
				assertThat(redirectLocation(result2)).isEqualTo("/");
				Result result3 = route(fakeRequest(GET, location));
				assertThat(status(result3)).isEqualTo(NOT_FOUND);
				assertThat(contentType(result3)).isEqualTo("text/html");
			}
		});
	}

	@Test
	public void cronActionAllwaysRespondsOk() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Result result = route(fakeRequest(GET, "/cron"));
				assertThat(status(result)).isEqualTo(SEE_OTHER);
				assertThat(redirectLocation(result)).isEqualTo("/");
			}
		});
	}
}