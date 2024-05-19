package bet.astral.shine.annotations;

import java.lang.annotation.*;

/**
 * Allows a developer to see if a method is supported by lunar client.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface LunarClient {
	/**
	 * Returns the client version given feature is supported in.
	 * @return version
	 */
	String version();
}
