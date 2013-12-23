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
}
