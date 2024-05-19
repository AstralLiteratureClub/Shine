package bet.astral.shine.annotations;

import java.lang.annotation.*;

/**
 * Allows a developer to see if a method is supported by glowing entities or glowing blocks.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface GlowingEntities {
	/**
	 * Shows the version which supports glowing entities and glowing blocks (given method)
	 * @return version
	 */
	String version();
}
