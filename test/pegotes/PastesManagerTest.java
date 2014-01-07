package pegotes;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

import org.junit.Before;
import org.junit.Test;

import pegotes.KeyGenerator;
import pegotes.PastesManager;
import play.libs.F.Option;

public class PastesManagerTest {
	@Before
	public void clearDB ()
	{
		running ( fakeApplication (), new Runnable () {
			public void run ()
			{
				PastesManager.deleteAll ();
			}
		} );
	}

	@Test
	public void saveWorks() {
		running ( fakeApplication (), new Runnable () {
			public void run ()
			{
				String key = KeyGenerator.getNewKey();
				PastesManager.save(key, "some content", "127.0.0.1");
				Option<String> content = PastesManager.load(key);
				assertThat(content).isNotNull();
				assertThat(content.isDefined()).isTrue();
				assertThat(content.get()).isNotNull();
				assertThat(content.get()).isEqualTo("some content");
			}
		} );
	}

	@Test
	public void isKeyAviableWorks() {
		running ( fakeApplication (), new Runnable () {
			public void run ()
			{
				String key = KeyGenerator.getNewKey();
				PastesManager.save(key, "some content", "127.0.0.1");
				assertThat(PastesManager.isKeyAviable(key)).isFalse();
			}
		} );
	}
}
