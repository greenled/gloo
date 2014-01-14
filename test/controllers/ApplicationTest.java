package controllers;
import java.util.Map;
import java.util.TreeMap;

import org.junit.*;

import pegotes.PastesManager;
import play.mvc.*;
import play.test.*;
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
			}
		});
	}

	@Test
	public void saveActionRespondsBadRequestOnNullContent() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Result result = route(fakeRequest(POST, "/"));
				assertThat(status(result)).isEqualTo(BAD_REQUEST);
			}
		});
	}

	@Test
	public void saveActionRespondsBadRequestOnWrongContent() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Result result = route(fakeRequest(POST, "/").withTextBody("Algo de texto erróneo"));
				assertThat(status(result)).isEqualTo(BAD_REQUEST);
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
}
