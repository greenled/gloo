import play.*;
import play.libs.*;
import scala.concurrent.duration.Duration;

import gloo.PastesManager;

import java.util.concurrent.TimeUnit;

public class Global extends GlobalSettings
{
	@Override
	public void onStart ( Application app )
	{
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