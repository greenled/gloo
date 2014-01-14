package api;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.HeaderNames.CONTENT_TYPE;
import static play.test.Helpers.*;

import org.junit.Before;
import org.junit.Test;

import pegotes.PastesManager;
import play.libs.WS;
import play.libs.F.Promise;
import play.mvc.Result;

public class TextApiTest
{
	@Before
	public void clearDB ()
	{
		running ( fakeApplication (), new Runnable () {
			public void run ()
			{
				PastesManager.deleteAll ();
			}
		} );
	}

	@Test
	public void saveActionRespondsBadRequestOnNonPlainTextContentType ()
	{
		running ( fakeApplication (), new Runnable () {
			public void run ()
			{
				Result result = route ( fakeRequest ( POST, "/api/text/" ) );
				assertThat ( status ( result ) ).isEqualTo ( BAD_REQUEST );
			}
		} );
	}

	@Test
	public void saveActionRespondsBadRequestOnNullContent ()
	{
		running ( fakeApplication (), new Runnable () {
			public void run ()
			{
				Result result = route ( fakeRequest ( POST, "/api/text/" )
						.withHeader ( CONTENT_TYPE, "text/plain" ) );
				assertThat ( status ( result ) ).isEqualTo ( BAD_REQUEST );
			}
		} );
	}

	@Test
	public void saveActionRespondsOkOnValidContent ()
	{
		running ( fakeApplication (), new Runnable () {
			public void run ()
			{
				Result result = route ( fakeRequest ( POST, "/api/text/" )
						.withHeader ( CONTENT_TYPE, "text/plain" )
						.withTextBody ( "Probando el API de texto" ) );
				assertThat ( status ( result ) ).isEqualTo ( CREATED );
				assertThat ( contentType ( result ) ).isEqualTo ( "text/plain" );
				assertThat ( contentAsString ( result ) ).isNotEmpty ();
			}
		} );
	}


	@Test
	public void viewActionRespondsNotFoundOnNonExistentKey ()
	{
		running ( fakeApplication (), new Runnable () {
			public void run ()
			{
				Result result = route ( fakeRequest ( GET,
						"/api/text/nonExistentKey" ) );
				assertThat ( status ( result ) ).isEqualTo ( NOT_FOUND );
			}
		} );
	}

	@Test
	public void testAll () {
		running(fakeApplication(), new Runnable() {
			public void run() {
				String pasteContentToSend = "Probando el API de texto";
				Result result1 = route(fakeRequest(POST, "/api/text/").withHeader(CONTENT_TYPE, "text/plain").withTextBody(pasteContentToSend));
				String key = contentAsString (result1);
				Result result2 = route(fakeRequest(GET, "/api/text/"+key));
				String pasteContentReceived = contentAsString (result2);
				assertThat(pasteContentReceived).isEqualTo(pasteContentToSend);
			}
		});
	}

	@Test
	public void testWithWS () {
		running(testServer(3333), new Runnable() {
			public void run() {
				String pasteContentToSend = "Probando el API de texto";
				Promise<WS.Response> result1 = WS.url("http://localhost:3333/api/text/").setHeader(CONTENT_TYPE, "text/plain").post(pasteContentToSend);
				String key = result1.get().getBody();
				Promise<WS.Response> result2 = WS.url("http://localhost:3333/api/text/"+key).get();
				String pasteContentReceived = result2.get().getBody();
				assertThat(pasteContentReceived).isEqualTo(pasteContentToSend);
			}
		});
	}
}
