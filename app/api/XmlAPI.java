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

import org.w3c.dom.Document;

import play.libs.F.Option;
import play.libs.XPath;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

public class XmlAPI extends Controller {

	@BodyParser.Of(BodyParser.Xml.class)
	public static Result save() {
		Document dom = request().body().asXml();
		if (dom == null) {
			return badRequest("Se esperaba Xml");
		} else {
			String content = XPath.selectText("//content", dom);
			if(content == null) {
				return badRequest("<message>Se esperaba parámetro [content]</message>");
			} else {
				String key = KeyGenerator.getNewKey();
	    		while (!PastesManager.isKeyAviable(key)) {
	    			key = KeyGenerator.getNewKey();
	    		}
	    		PastesManager.save(key, content);
	    	    return created("<key>" + key + "</key>");
			}
		}
	}

	@BodyParser.Of(BodyParser.Xml.class)
	public static Result view (String key)
	{
		Option<String> content = PastesManager.load(key);
		if (content.isDefined()) {
			return ok("<content>" + content.get() + "</content>");
		} else {
			return notFound();
		}
	}
}
