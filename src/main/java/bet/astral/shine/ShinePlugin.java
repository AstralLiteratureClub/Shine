package bet.astral.shine;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Enables the shine API as a plugin which other plugins can depend on and not shade the Shine api to their jar files.
 */
public class ShinePlugin extends JavaPlugin {
	/**
	 *
	 */
	private Shine shine;
	/**
	 *
	 */
	@Override
	public void onEnable(){
		this.shine = new Shine(this);
		getLogger().info("ShineAPI has enabled as plugin!");
	}

	/**
	 *
	 */
	@Override
	public void onDisable(){
		this.shine.disable();
		getLogger().info("ShineAPI has disabled as plugin!");
	}

	/**
	 * Returns shine shared ShineAPI between the server.
	 * @return shine
	 */
	public Shine getShine() {
		return shine;
	}
}
