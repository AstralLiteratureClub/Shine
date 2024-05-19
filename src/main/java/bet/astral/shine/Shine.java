package bet.astral.shine;

import bet.astral.platform.entity.reference.IEntityReference;
import bet.astral.platform.entity.reference.IPlayerReference;
import bet.astral.platform.world.IBlock;
import bet.astral.platform.world.Position;
import bet.astral.shine.annotations.GlowingEntities;
import bet.astral.shine.annotations.LunarClient;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.glow.GlowModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import fr.skytasul.glowingentities.GlowingBlocks;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Shine is a {@link fr.skytasul.glowingentities.GlowingEntities} and {@link GlowModule} linking system
 * to use LunarClient's glowing system or to use the vanilla minecraft glowing system.
 */
public final class Shine {
	private final fr.skytasul.glowingentities.GlowingEntities glowingEntities;
	private final GlowingBlocks glowingBlocks;
	private final GlowModule glowModule;

	/**
	 * Creates and enables the glowing system
	 * @param javaPlugin your plugin main class
	 */
	public Shine(JavaPlugin javaPlugin) {
		GlowingBlocks glowingBlocks1 = null;
		fr.skytasul.glowingentities.GlowingEntities glowingEntities1 = null;
		GlowModule glowModule1 = null;
		try {
			Class.forName("com.lunarclient.apollo.Apollo");
			Apollo.getModuleManager().getModule(GlowModule.class);
		} catch (ClassNotFoundException ignored) {
		}
		try {
			Class.forName("fr.skytasul.glowingentities.GlowingEntities");
			glowingEntities1 = new fr.skytasul.glowingentities.GlowingEntities(javaPlugin);
			glowingEntities1.enable();
			glowingBlocks1 = new GlowingBlocks(javaPlugin);
			glowingBlocks1.enable();
		} catch (ClassNotFoundException ignored) {
		}
		glowingBlocks = glowingBlocks1;
		glowingEntities = glowingEntities1;
		glowModule = glowModule1;
	}

	/**
	 * Disables the glowing entities glowing system
	 */
	public void disable(){
		if (glowingEntities != null){
			glowingEntities.disable();
			glowingBlocks.disable();;
		}
	}

	/**
	 * Converts the given player to lunar client player if the player is connected using lunar client
	 * @param player player
	 * @return apollo player
	 */
	@Nullable
	public ApolloPlayer toLunar(@NotNull Player player){
		return Apollo.getPlayerManager().getPlayer(player.getUniqueId()).orElse(null);
	}

	/**
	 * Checks if the given player is using the lunar client and supports custom glowing colors.
	 * @param player player
	 * @return true if it is using the lunar client, else false
	 */
	public boolean isLunarPlayer(@NotNull Player player){
		if (glowModule == null){
			return false;
		}
		return Apollo.getPlayerManager().hasSupport(player.getUniqueId());
	}


	/**
	 * Makes the given entity glow with given color to the player.
	 * @param player player
	 * @param entity entity to glow
	 * @param color color
	 * @throws ReflectiveOperationException If glowing entities has found a reflection exception
	 */
	@LunarClient(version = "> 1.7")
	@GlowingEntities(version = "> 1.17")
	public void setGlowing(@NotNull IPlayerReference<?, ?> player, @NotNull Entity entity, @NotNull ShineColor color) throws ReflectiveOperationException {
		setGlowing(player, entity.getUniqueId(), color);
	}

	/**
	 * Makes the given entity glow with given color to the player.
	 * @param player player
	 * @param entity entity to glow
	 * @param color color
	 * @throws ReflectiveOperationException If glowing entities has found a reflection exception
	 */
	@LunarClient(version = "> 1.7")
	@GlowingEntities(version = "> 1.17")
	public void setGlowing(@NotNull IPlayerReference<?, ?> player, @NotNull IEntityReference<?> entity, @NotNull ShineColor color) throws ReflectiveOperationException {
		setGlowing(player, entity.getUniqueId(), color);
	}

	/**
	 * Makes the given entity glow with given color to the player.
	 * @param player player
	 * @param entityId entity to glow
	 * @param color color
	 * @throws ReflectiveOperationException If glowing entities has found a reflection exception
	 */
	@LunarClient(version = "> 1.7")
	@GlowingEntities(version = "> 1.17")
	public void setGlowing(@NotNull IPlayerReference<?, ?> player, @NotNull UUID entityId, @NotNull ShineColor color) throws ReflectiveOperationException {
		//noinspection DataFlowIssue
		setGlowing((Player) player.getPlayerPlatform(), entityId, color);
	}
	/**
	 * Makes the given entity glow with given color to the player.
	 * @param player player
	 * @param entity entity to glow
	 * @param color color
	 * @throws ReflectiveOperationException If glowing entities has found a reflection exception
	 */
	@LunarClient(version = "> 1.7")
	@GlowingEntities(version = "> 1.17")
	public void setGlowing(@NotNull Player player, @NotNull Entity entity, @NotNull ShineColor color) throws ReflectiveOperationException {
		setGlowing(player, entity.getUniqueId(), color);
	}
	/**
	 * Makes the given entity glow with given color to the player.
	 * @param player player
	 * @param entity entity to glow
	 * @param color color
	 * @throws ReflectiveOperationException If glowing entities has found a reflection exception
	 */
	@LunarClient(version = "> 1.7")
	@GlowingEntities(version = "> 1.17")
	public void setGlowing(@NotNull Player player, @NotNull IEntityReference<?> entity, @NotNull ShineColor color) throws ReflectiveOperationException {
		setGlowing(player, entity.getUniqueId(), color);
	}
	/**
	 * Makes the given entity glow with given color to the player.
	 * @param player player
	 * @param entityId entity to glow
	 * @param color color
	 * @throws ReflectiveOperationException If glowing entities has found a reflection exception
	 */
	@LunarClient(version = "> 1.7")
	@GlowingEntities(version = "> 1.17")
	public void setGlowing(@NotNull Player player, @NotNull UUID entityId, @NotNull ShineColor color) throws ReflectiveOperationException {
		if (isLunarPlayer(player)){
			glowModule.overrideGlow(toLunar(player), entityId, color);
		} else {
			if (glowingEntities == null){
				return;
			}
			glowingEntities.setGlowing(Bukkit.getEntity(entityId), player, color.getBukkitEquivalent());
		}
	}

	/**
	 * Removes glowing from given entity from the given player
	 * @param player player
	 * @param entity entity
	 * @throws ReflectiveOperationException If glowing entities has found a reflection exception
	 */
	@LunarClient(version = "> 1.7")
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@NotNull IPlayerReference<?, ?> player, @NotNull Entity entity) throws ReflectiveOperationException {
		removeGlowing(player, entity.getUniqueId());
	}
	/**
	 * Removes glowing from given entity from the given player
	 * @param player player
	 * @param entity entity
	 * @throws ReflectiveOperationException If glowing entities has found a reflection exception
	 */
	@LunarClient(version = "> 1.7")
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@NotNull IPlayerReference<?, ?> player, @NotNull IEntityReference<?> entity) throws ReflectiveOperationException {
		removeGlowing(player, entity.getUniqueId());
	}
	/**
	 * Removes glowing from given entity from the given player
	 * @param player player
	 * @param entityId entity
	 * @throws ReflectiveOperationException If glowing entities has found a reflection exception
	 */
	@LunarClient(version = "> 1.7")
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@NotNull IPlayerReference<?, ?> player, @NotNull UUID entityId) throws ReflectiveOperationException {
		removeGlowing((Player) Objects.requireNonNull(player.getPlayerPlatform()), entityId);
	}
	/**
	 * Removes glowing from given entity from the given player
	 * @param player player
	 * @param entity entity
	 * @throws ReflectiveOperationException If glowing entities has found a reflection exception
	 */
	@LunarClient(version = "> 1.7")
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@NotNull Player player, @NotNull Entity entity) throws ReflectiveOperationException {
		removeGlowing(player, entity.getUniqueId());
	}
	/**
	 * Removes glowing from given entity from the given player
	 * @param player player
	 * @param entity entity
	 * @throws ReflectiveOperationException If glowing entities has found a reflection exception
	 */
	@LunarClient(version = "> 1.7")
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@NotNull Player player, @NotNull IEntityReference<?> entity) throws ReflectiveOperationException {
		removeGlowing(player, entity.getUniqueId());
	}
	/**
	 * Removes glowing from given entity from the given player
	 * @param player player
	 * @param entityId entity
	 * @throws ReflectiveOperationException If glowing entities has found a reflection exception
	 */
	@LunarClient(version = "> 1.7")
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@NotNull Player player, @NotNull UUID entityId) throws ReflectiveOperationException {
		if (isLunarPlayer(player)){
			glowModule.resetGlow(toLunar(player), entityId);
		} else {
			if (glowingEntities == null){
				return;
			}
			glowingEntities.unsetGlowing(Bukkit.getEntity(entityId), player);
		}
	}


	/**
	 * Uses GlowingEntities to set given block to glow using a fake shulker box.
	 * @param player player
	 * @param block block to glow
	 * @param color color
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void setGlowing(@NotNull IPlayerReference<?, ?> player, @NotNull Block block, @NotNull ShineColor color) throws ReflectiveOperationException {
		setGlowing((Player) player.getPlayerPlatform(), block, color);
	}
	/**
	 * Uses GlowingEntities to set given block to glow using a fake shulker box.
	 * @param player player
	 * @param location location of block to glow
	 * @param color color
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void setGlowing(@NotNull IPlayerReference<?, ?> player, @NotNull Location location, @NotNull ShineColor color) throws ReflectiveOperationException {
		setGlowing(player, location.getBlock(), color);
	}
	/**
	 * Uses GlowingEntities to set given block to glow using a fake shulker box.
	 * @param player player
	 * @param block block to glow
	 * @param color color
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void setGlowing(@NotNull IPlayerReference<?, ?> player, @NotNull IBlock<?> block, @NotNull ShineColor color) throws ReflectiveOperationException {
		setGlowing(player, (Block) block.getHandle(), color);
	}
	/**
	 * Uses GlowingEntities to set given block to glow using a fake shulker box.
	 * @param player player
	 * @param location location of block to glow
	 * @param color color
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void setGlowing(@NotNull IPlayerReference<?, ?> player, @NotNull Position location, @NotNull ShineColor color) throws ReflectiveOperationException {
		setGlowing(player, new Location(Bukkit.getWorld(location.getWorld()), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch()), color);
	}
	/**
	 * Uses GlowingEntities to set given block to glow using a fake shulker box.
	 * @param player player
	 * @param block block to glow
	 * @param color color
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void setGlowing(@Nullable Player player, @NotNull Block block, @NotNull ShineColor color) throws ReflectiveOperationException {
		if (player == null){
			return;
		}
		if (glowingBlocks != null){
			glowingBlocks.setGlowing(block, player, color.getBukkitEquivalent());
		}
	}
	/**
	 * Uses GlowingEntities to set given block to glow using a fake shulker box.
	 * @param player player
	 * @param location location of block to glow
	 * @param color color
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void setGlowing(@NotNull Player player, @NotNull Location location, @NotNull ShineColor color) throws ReflectiveOperationException {
		setGlowing(player, location.getBlock(), color);
	}
	/**
	 * Uses GlowingEntities to set given block to glow using a fake shulker box.
	 * @param player player
	 * @param block block to glow
	 * @param color color
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void setGlowing(@NotNull Player player, @NotNull IBlock<?> block, @NotNull ShineColor color) throws ReflectiveOperationException {
		setGlowing(player, (Block) block.getHandle(), color);
	}
	/**
	 * Uses GlowingEntities to set given block to glow using a fake shulker box.
	 * @param player player
	 * @param location location of block to glow
	 * @param color color
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void setGlowing(@NotNull Player player, @NotNull Position location, @NotNull ShineColor color) throws ReflectiveOperationException {
		setGlowing(player, new Location(Bukkit.getWorld(location.getWorld()), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch()), color);
	}
	/**
	 * Removes the fake shulker and glowing from given block from given player.
	 * @param player player
	 * @param block block to remove glowing from
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@NotNull IPlayerReference<?, ?> player, @NotNull Block block) throws ReflectiveOperationException {
		removeGlowing((Player) player.getPlayerPlatform(), block);
	}

	/**
	 * Removes the fake shulker and glowing from given block from given player.
	 * @param player player
	 * @param location location of block to remove glowing from
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@NotNull IPlayerReference<?, ?> player, @NotNull Location location) throws ReflectiveOperationException {
		removeGlowing(player, location.getBlock());
	}
	/**
	 * Removes the fake shulker and glowing from given block from given player.
	 * @param player player
	 * @param block block to remove glowing from
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@NotNull IPlayerReference<?, ?> player, @NotNull IBlock<?> block) throws ReflectiveOperationException {
		removeGlowing(player, (Block) block.getHandle());
	}
	/**
	 * Removes the fake shulker and glowing from given block from given player.
	 * @param player player
	 * @param location location of block to remove glowing from
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@NotNull IPlayerReference<?, ?> player, @NotNull Position location) throws ReflectiveOperationException {
		removeGlowing(player, new Location(Bukkit.getWorld(location.getWorld()), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch()));
	}
	/**
	 * Removes the fake shulker and glowing from given block from given player.
	 * @param player player
	 * @param block block to remove glowing from
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@Nullable Player player, @NotNull Block block) throws ReflectiveOperationException {
		if (player == null){
			return;
		}
		if (glowingBlocks != null){
			glowingBlocks.unsetGlowing(block, player);
		}
	}
	/**
	 * Removes the fake shulker and glowing from given block from given player.
	 * @param player player
	 * @param block block to remove glowing from
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@NotNull Player player, @NotNull IBlock<?> block) throws ReflectiveOperationException {
		removeGlowing(player, (Block) block.getHandle());
	}
	/**
	 * Removes the fake shulker and glowing from given block from given player.
	 * @param player player
	 * @param location location of block to remove glowing from
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@NotNull Player player, @NotNull Location location) throws ReflectiveOperationException {
		removeGlowing(player, location.getBlock());
	}
	/**
	 * Removes the fake shulker and glowing from given block from given player.
	 * @param player player
	 * @param location location of block to remove glowing from
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@NotNull Player player, @NotNull Position location) throws ReflectiveOperationException {
		removeGlowing(player, new Location(Bukkit.getWorld(location.getWorld()), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch()));
	}
}