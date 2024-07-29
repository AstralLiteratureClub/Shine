package bet.astral.shine.receiver;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface EntityShineReceiver extends ShineReceiver {
	@NotNull UUID getUniqueId();
	@Nullable
	default Entity getEntity() {
		return Bukkit.getEntity(getUniqueId());
	}
	@Nullable
	default Player getPlayer(){
		return Bukkit.getPlayer(getUniqueId());
	}
}
