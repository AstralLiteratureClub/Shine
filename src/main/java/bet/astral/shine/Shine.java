package bet.astral.shine;

import bet.astral.shine.annotations.GlowingEntities;
import bet.astral.shine.annotations.LunarClient;
import bet.astral.shine.receiver.BlockShineReceiver;
import bet.astral.shine.receiver.EntityShineReceiver;
import bet.astral.shine.receiver.ForwardingShineReceiver;
import bet.astral.shine.receiver.ShineReceiver;
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
			glowModule1 = Apollo.getModuleManager().getModule(GlowModule.class);
			glowModule1.enable();
		} catch (ClassNotFoundException ignored) {
		}
		try {
			Class.forName("fr.skytasul.glowingentities.GlowingEntities");
			glowingBlocks1 = new GlowingBlocks(javaPlugin);
			Method method = glowingBlocks1.getClass().getDeclaredMethod("entities");
			glowingEntities1 = (fr.skytasul.glowingentities.GlowingEntities) method.invoke(glowingBlocks1);
			glowingBlocks1.enable();
		} catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
		         IllegalAccessException ignored) {
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
	 * @param receiver receiver
	 * @param who who
	 * @param color color
	 * @throws ReflectiveOperationException If glowing entities has found a reflection exception
	 */
	@LunarClient(version = "> 1.7")
	@GlowingEntities(version = "> 1.17")
	public void setGlowing(@NotNull ShineReceiver receiver, @NotNull ShineReceiver who, @NotNull ShineColor color) throws ReflectiveOperationException {
		if (receiver instanceof ForwardingShineReceiver forwardingShineReceiver){
			for (ShineReceiver shineReceiver : forwardingShineReceiver){
				setGlowing(shineReceiver, who, color);
			}
		} else if (receiver instanceof EntityShineReceiver entityShineReceiver && entityShineReceiver.getPlayer() != null){
			setGlowing(entityShineReceiver.getPlayer(), who, color);
		}
	}

	/**
	 * Makes the given entity glow with given color to the player.
	 * @param receiver receiver
	 * @param entity entity to glow
	 * @param color color
	 * @throws ReflectiveOperationException If glowing entities has found a reflection exception
	 */
	@LunarClient(version = "> 1.7")
	@GlowingEntities(version = "> 1.17")
	public void setGlowing(@NotNull ShineReceiver receiver, @NotNull Entity entity, @NotNull ShineColor color) throws ReflectiveOperationException {
		setGlowing(receiver, entity.getUniqueId(), color);
	}

	/**
	 * Makes the given entity glow with given color to the player.
	 * @param receiver player
	 * @param entityId entity to glow
	 * @param color color
	 * @throws ReflectiveOperationException If glowing entities has found a reflection exception
	 */
	@LunarClient(version = "> 1.7")
	@GlowingEntities(version = "> 1.17")
	public void setGlowing(@NotNull ShineReceiver receiver, @NotNull UUID entityId, @NotNull ShineColor color) throws ReflectiveOperationException {
		if (receiver instanceof ForwardingShineReceiver forwardingShineReceiver){
			for (ShineReceiver shineReceiver : forwardingShineReceiver){
				setGlowing(shineReceiver, entityId, color);
			}
		} else if (receiver instanceof EntityShineReceiver entityShineReceiver && entityShineReceiver.getPlayer() != null){
			setGlowing(entityShineReceiver.getPlayer(), entityId, color);
		}
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
	 * @param who who to glow
	 * @param color color
	 * @throws ReflectiveOperationException If glowing entities has found a reflection exception
	 */
	@LunarClient(version = "> 1.7")
	@GlowingEntities(version = "> 1.17")
	public void setGlowing(@NotNull Player player, @NotNull ShineReceiver who, @NotNull ShineColor color) throws ReflectiveOperationException {
		if (who instanceof ForwardingShineReceiver forwardingShineReceiver){
			for (ShineReceiver shineReceiver : forwardingShineReceiver){
				setGlowing(player, shineReceiver, color);
			}
		} else if (who instanceof EntityShineReceiver entityShineReceiver && entityShineReceiver.getEntity() != null){
			setGlowing(player, entityShineReceiver.getEntity(), color);
		} else if (who instanceof BlockShineReceiver blockShineReceiver && blockShineReceiver.getBlock() != null){
			setGlowing(player, blockShineReceiver.getBlock(), color);
		}
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
	 * @param receiver receiver
	 * @param entity entity
	 * @throws ReflectiveOperationException If glowing entities has found a reflection exception
	 */
	@LunarClient(version = "> 1.7")
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@NotNull ShineReceiver receiver, @NotNull Entity entity) throws ReflectiveOperationException {
		removeGlowing(receiver, entity.getUniqueId());
	}
	/**
	 * Removes glowing from given entity from the given player
	 * @param receiver receiver
	 * @param who who to remove glowing off
	 * @throws ReflectiveOperationException If glowing entities has found a reflection exception
	 */
	@LunarClient(version = "> 1.7")
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@NotNull ShineReceiver receiver, @NotNull ShineReceiver who) throws ReflectiveOperationException {
		if (receiver instanceof ForwardingShineReceiver forwardingShineReceiver){
			for (ShineReceiver shineReceiver : forwardingShineReceiver){
				removeGlowing(shineReceiver, who);
			}
		} else if (receiver instanceof EntityShineReceiver entityShineReceiver && entityShineReceiver.getPlayer()  != null){
			removeGlowing(entityShineReceiver.getPlayer(), who);
		}
	}
	/**
	 * Removes glowing from given entity from the given player
	 * @param receiver receiver
	 * @param entityId entity
	 * @throws ReflectiveOperationException If glowing entities has found a reflection exception
	 */
	@LunarClient(version = "> 1.7")
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@NotNull ShineReceiver receiver, @NotNull UUID entityId) throws ReflectiveOperationException {
		if (receiver instanceof ForwardingShineReceiver forwardingShineReceiver){
			for (ShineReceiver shineReceiver : forwardingShineReceiver){
				removeGlowing(shineReceiver, entityId);
			}
		} else if (receiver instanceof EntityShineReceiver entityShineReceiver && entityShineReceiver.getPlayer()  != null){
			removeGlowing(entityShineReceiver.getPlayer(), entityId);
		}	}
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
	 * @param receiver receiver
	 * @throws ReflectiveOperationException If glowing entities has found a reflection exception
	 */
	@LunarClient(version = "> 1.7")
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@NotNull Player player, @NotNull ShineReceiver receiver) throws ReflectiveOperationException {
		if (receiver instanceof ForwardingShineReceiver forwardingShineReceiver){
			for (ShineReceiver shineReceiver : forwardingShineReceiver.getReceivers()){
				removeGlowing(player, shineReceiver);
			}
		} else if (receiver instanceof EntityShineReceiver entityShineReceiver){
			removeGlowing(player, entityShineReceiver.getUniqueId());
		} else if (receiver instanceof BlockShineReceiver blockShineReceiver){
			if (blockShineReceiver.getBlock() == null){
				return;
			}
			removeGlowing(player, blockShineReceiver.getBlock());
		}
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
	 * @param receiver player
	 * @param block block to glow
	 * @param color color
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void setGlowing(@NotNull ShineReceiver receiver, @NotNull Block block, @NotNull ShineColor color) throws ReflectiveOperationException {
		if (receiver instanceof ForwardingShineReceiver forwardingShineReceiver){
			for (ShineReceiver shineReceiver : forwardingShineReceiver){
				setGlowing(shineReceiver, block, color);
			}
		} else if (receiver instanceof EntityShineReceiver shineReceiver){
			if (shineReceiver.getPlayer() == null){
				return;
			}
			setGlowing(shineReceiver.getPlayer(), block, color);
		}
	}
	/**
	 * Uses GlowingEntities to set given block to glow using a fake shulker box.
	 * @param receiver player
	 * @param location location of block to glow
	 * @param color color
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void setGlowing(@NotNull ShineReceiver receiver, @NotNull Location location, @NotNull ShineColor color) throws ReflectiveOperationException {
		setGlowing(receiver, location.getBlock(), color);
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
	 * Removes the fake shulker and glowing from given block from given player.
	 * @param receiver player
	 * @param block block to remove glowing from
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@NotNull ShineReceiver receiver, @NotNull Block block) throws ReflectiveOperationException {
		removeGlowing(receiver, block.getLocation());
	}

	/**
	 * Removes the fake shulker and glowing from given block from given player.
	 * @param receiver player
	 * @param location location of block to remove glowing from
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@NotNull ShineReceiver receiver, @NotNull Location location) throws ReflectiveOperationException {
		if (receiver instanceof ForwardingShineReceiver forwardingShineReceiver){
			for (ShineReceiver shineReceiver : forwardingShineReceiver){
				removeGlowing(shineReceiver, location);
			}
		} else if (receiver instanceof EntityShineReceiver shineReceiver){
			if (shineReceiver.getPlayer() == null){
				return;
			}
			removeGlowing(shineReceiver.getPlayer(), location);
		}
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
	 * @param location location of block to remove glowing from
	 * @throws ReflectiveOperationException If glowing blocks has found a reflection exception
	 */
	@GlowingEntities(version = "> 1.17")
	public void removeGlowing(@NotNull Player player, @NotNull Location location) throws ReflectiveOperationException {
		removeGlowing(player, location.getBlock());
	}
}