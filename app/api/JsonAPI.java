/*
This file is part of Pegotes.

Pegotes is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Pegotes is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with Pegotes.  If not, see <http://www.gnu.org/licenses/>.
*/

package api;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import pegotes.PastesManager;
import play.libs.F.Option;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

public class JsonAPI extends Controller
{

	/**
	 * Guardar un pegote
	 */
	@BodyParser.Of ( BodyParser.Json.class )
	public static Result save ()
	{
		JsonNode json = request ().body ().asJson ();
		ObjectNode result = Json.newObject ();
		if ( json == null ) {
			result.put ( "message", "Se esperaba Json" );
			return badRequest ( result );
		} else {
			String content = json.findPath("content").asText();;
			if (content == null || content.isEmpty()) {
				result.put ( "message", "Se esperaba Json con algún contenido" );
				return badRequest ( result );
			} else {
				String key = PastesManager.getAviableKey ();
				PastesManager.save(key, content, request ().remoteAddress ());
				result.put ( "key", key );
				return created ( result );
			}
		}
	}

	/**
	 * Mostrar un pegote guardado
	 * @param key Identificador
	 */
	@BodyParser.Of ( BodyParser.Json.class )
	public static Result view ( String key )
	{
		ObjectNode result = Json.newObject ();
		Option<String> content = PastesManager.load ( key );
		if ( content.isDefined () ) {
			result.put ( "content", content.get () );
			return ok ( result );
		} else {
			result.put ( "message", "No existe un texto con la clave " + key );
			return notFound ( result );
		}
	}
}
