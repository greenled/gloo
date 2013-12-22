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

public class JsonAPI extends Controller
{

	@BodyParser.Of ( BodyParser.Json.class )
	public static Result save ()
	{
		JsonNode json = request ().body ().asJson ();
		ObjectNode result = Json.newObject ();
		if ( json == null ) {
			result.put ( "gloo", "Se esperaba Json" );
			return badRequest ( result );
		} else {
			String content = json.findPath("gloo").getTextValue();
			if (content == null || content.isEmpty()) {
				result.put ( "gloo", "Se esperaba Json con algún contenido" );
				return badRequest ( result );
			} else {
				String key = KeyGenerator.getNewKey ();
				while ( !PastesManager.isKeyAviable ( key ) ) {
					key = KeyGenerator.getNewKey ();
				}
				PastesManager.save ( key, content );
				result.put ( "gloo", key );
				return created ( result );
			}
		}
	}

	@BodyParser.Of ( BodyParser.Json.class )
	public static Result view ( String key )
	{
		ObjectNode result = Json.newObject ();
		Option<String> content = PastesManager.load ( key );
		if ( content.isDefined () ) {
			result.put ( "gloo", content.get () );
			return ok ( result );
		} else {
			result.put ( "gloo", "No existe un texto con la clave " + key );
			return notFound ( result );
		}
	}
}
