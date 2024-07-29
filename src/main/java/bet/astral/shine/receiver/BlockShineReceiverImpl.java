package bet.astral.shine.receiver;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

class BlockShineReceiverImpl implements BlockShineReceiver{
	private final Location location;

	public BlockShineReceiverImpl(Location location) {
		this.location = location;
	}

	@Override
	public @NotNull Location getLocation() {
		return location;
	}
}
