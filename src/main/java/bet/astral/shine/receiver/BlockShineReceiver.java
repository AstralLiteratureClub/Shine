package bet.astral.shine.receiver;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BlockShineReceiver extends ShineReceiver{
	@NotNull
	Location getLocation();
	@Nullable
	default Block getBlock() {
		Location location = getLocation();
		World world = location.getWorld();
		if (world == null){
			return null;
		}
		return world.getBlockAt(location);
	}
}
