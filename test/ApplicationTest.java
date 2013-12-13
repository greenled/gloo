import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Paste;

import org.codehaus.jackson.JsonNode;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.Form;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {
	@Test
	public void add_renders_correctly() {
		Form<Paste> pasteForm = Form.form(Paste.class);
    	Content html = views.html.add.render(pasteForm);
    	assertThat(contentType(html)).isEqualTo("text/html");
	}
	
	@Test
	public void view_renders_correctly() {
    	Content html = views.html.view.render("0123-key", "<p>content</p>");
    	assertThat(contentType(html)).isEqualTo("text/html");
    	assertThat(contentAsString(html)).contains("0123-key");
    	assertThat(contentAsString(html)).contains("&lt;p&gt;content&lt;/p&gt;");
	}
}
