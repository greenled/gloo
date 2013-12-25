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

package controllers;

import gloo.KeyGenerator;
import gloo.PastesManager;

import java.io.File;

import models.Paste;
import play.data.Form;
import play.libs.F.Option;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.add;
import views.html.view;

public class Application extends Controller {
	private static Form<Paste> pasteForm = Form.form(Paste.class);

	public static Result add() {
        return ok(add.render(pasteForm));
    }

	public static Result save() {
		Form<Paste> filledForm = pasteForm.bindFromRequest();
		if(filledForm.hasErrors()) {
    		return badRequest(views.html.add.render(filledForm));
    	} else {
    		Paste paste = pasteForm.bindFromRequest().get();
    		String key = KeyGenerator.getNewKey();
    		while (!PastesManager.isKeyAviable(key)) {
    			key = KeyGenerator.getNewKey();
    		}
    		PastesManager.save(key, paste.content);
        	return redirect(routes.Application.view(key));
    	}
	}

	public static Result view(String key) {
		Option<String> content = PastesManager.load(key);
		if (content.isDefined())
			return ok(view.render(key, content.get()));
		else
			return notFound(views.html.notFound.render("No se ha encontrado " + key));
	}

	public static Result raw(String key) {
		Option<File> f = PastesManager.getPasteFile(key);
		if (f.isDefined())
			return ok(f.get());
		else
			return notFound(views.html.notFound.render("No se ha encontrado " + key));
	}

	public static Result delete(String key) {
		PastesManager.delete(key);
		return ok("Se ha eliminado " + key);
	}
}
