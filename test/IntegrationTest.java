import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

import org.junit.Test;

import play.libs.F.Callback;
import play.test.TestBrowser;

public class IntegrationTest {

    /**
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */
	@Test
	public void test_add() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
			public void invoke(TestBrowser browser) {
				browser.goTo("http://localhost:3333");

				browser.$("#content").text("Un paste de prueba");
				browser.$("#save").click();

				assertThat (browser.$("#content").getText()).isEqualTo("Un paste de prueba");
			}
		});
	}

//	@Test
//	public void test_view() {
//		running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
//			public void invoke(TestBrowser browser) {
//				browser.goTo("http://localhost:3333/impossible*key");
//				// TODO ver cómo pruebo si la respuesta es un 404
//			}
//		});
//	}
}
