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

import pegotes.PastesManager;
import play.libs.F.Option;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

public class TextAPI extends Controller
{
	/**
	 * Guardar un pegote
	 */
	@BodyParser.Of ( BodyParser.Text.class )
	public static Result save ()
	{
		String content = request ().body ().asText ();
		if ( content == null ) {
			return badRequest ("Se esperaba texto plano");
		} else if (content.isEmpty ()) {
			return badRequest ("Se esperaba texto plano con algo de texto");
		} else {
			String key = PastesManager.getAviableKey ();
			PastesManager.save(key, content, request ().remoteAddress ());
			return created (key);
		}
	}

	/**
	 * Mostrar un pegote guardado
	 * @param key Identificador
	 */
	@BodyParser.Of ( BodyParser.Text.class )
	public static Result view ( String key )
	{
		Option<String> content = PastesManager.load ( key );
		if ( content.isDefined () )
			return ok (content.get ());
		else
			return notFound ("No existe un texto con la clave " + key);
	}
}
