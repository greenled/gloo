package gloo;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class KeyGeneratorTest {
	@Test
	public void the_generator_generates_a_key() {
		String k = KeyGenerator.getNewKey();
		assertThat(k).isNotNull();
		assertThat(k).isNotEmpty();
	}
	
	@Test
	public void the_keys_are_not_equal() {
		String k1 = KeyGenerator.getNewKey();
		String k2 = KeyGenerator.getNewKey();
		assertThat(k1).isNotEqualTo(k2);
	}
}
