/*
This file is part of Gloo.

Gloo is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Gloo is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with Gloo.  If not, see <http://www.gnu.org/licenses/>.
*/

package api;

import gloo.KeyGenerator;
import gloo.PastesManager;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import play.libs.F.Option;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

public class JsonAPI extends Controller {
	
	@BodyParser.Of(BodyParser.Json.class)
	public static Result save() {
		JsonNode json = request().body().asJson();
		ObjectNode result = Json.newObject();
		if (json == null) {
			return badRequest("Se esperaba Json");
		} else {
			String content = json.findPath("content").getTextValue();
			if(content == null) {
	    		result.put("message", "Se esperaba parámetro [content]");
				return badRequest(result);
			} else {
				String key = KeyGenerator.getNewKey();
	    		while (!PastesManager.isKeyAviable(key)) {
	    			key = KeyGenerator.getNewKey();
	    		}
	    		PastesManager.save(key, content);
	    		result.put("key", key);
	    	    return created(result);
			}
		}
	}
	
	public static Result raw (String key)
	{
		ObjectNode result = Json.newObject();
		Option<String> content = PastesManager.load(key);
		if (content.isDefined()) {
    		result.put("content", content.get());
			return ok(result);
		} else {
			return notFound();
		}
	}
}
