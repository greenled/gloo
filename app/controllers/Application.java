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

package controllers;


import java.io.File;
import java.net.UnknownHostException;

import models.Paste;
import pegotes.PastesManager;
import play.data.Form;
import play.i18n.Lang;
import play.libs.F.Option;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.add;
import views.html.info;
import views.html.notFound;
import views.html.view;

public class Application extends Controller {
	private static Form<Paste> pasteForm = Form.form(Paste.class);

	public static Result add() {
        return ok(add.render(pasteForm));
    }

	public static Result save() {
		Form<Paste> filledForm = pasteForm.bindFromRequest();
		if(filledForm.hasErrors()) {
    		return badRequest(add.render(filledForm));
    	} else {
    		Paste paste = pasteForm.bindFromRequest().get();
    		String key = PastesManager.getAviableKey ();
    		PastesManager.save(key, paste.content, request ().remoteAddress ());
        	return redirect(routes.Application.view(key));
    	}
	}

	public static Result view(String key) {
		Option<String> content = PastesManager.load(key);
		if (content.isDefined())
			return ok(view.render(key, content.get()));
		else
			return notFound(notFound.render());
	}

	public static Result raw(String key) {
		Option<File> f = PastesManager.getPasteFile(key);
		if (f.isDefined())
			return ok(f.get());
		else
			return notFound(notFound.render());
	}

	public static Result info () {
		Lang pref = Lang.preferred(request ().acceptLanguages ());
		return ok(info.render(pref));
	}

	public static Result delete(String key) {
		PastesManager.delete(key);
		return ok("Se ha eliminado " + key);
	}

	public static Result cron () throws UnknownHostException {
		if (request ().remoteAddress ().equals ( "127.0.0.1" )) {
			PastesManager.deleteOld ();
			return redirect ( routes.Application.add () );
		} else {
			return forbidden();
		}
	}
}