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

import play.*;
import play.api.mvc.EssentialFilter;
import play.filters.gzip.GzipFilter;
import play.libs.*;
import scala.concurrent.duration.Duration;

import gloo.PastesManager;

import java.util.concurrent.TimeUnit;

public class Global extends GlobalSettings
{
	@Override
	public void onStart ( Application app )
	{
		if (!Play.isTest ()) {
			long interval = Play.application().configuration().getMilliseconds("cleanning.interval");
			Akka.system ().scheduler ().schedule (
				Duration.create(0, TimeUnit.MILLISECONDS),
				Duration.create(interval, TimeUnit.MILLISECONDS),
				new Runnable() {
					public void run() {
						PastesManager.deleteOld ();
					}
				},
				Akka.system().dispatcher()
			);
		}
	}

	public <T extends EssentialFilter> Class<T>[] filters() {
		return new Class[]{GzipFilter.class};
	}
}
