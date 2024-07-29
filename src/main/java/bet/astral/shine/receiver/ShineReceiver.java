package bet.astral.shine.receiver;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;

public interface ShineReceiver {
	@NotNull
	static ShineReceiver of(@NotNull UUID entityId){
		return new EntityShineReceiverImpl(entityId);
	}
	@NotNull
	static ShineReceiver of(@NotNull UUID... entityIds){
		Collection<ShineReceiver> receivers = new LinkedList<>();
		for (UUID id : entityIds){
			receivers.add(of(id));
		}
		return of(receivers);
	}
	@NotNull
	static ShineReceiver of(@NotNull Entity entity){
		return new EntityShineReceiverImpl(entity.getUniqueId());
	}
	@NotNull
	static ShineReceiver of(@NotNull Entity... entities){
		Collection<ShineReceiver> receivers = new LinkedList<>();
		for (Entity entity : entities){
			receivers.add(of(entity));
		}
		return of(receivers);
	}
	@NotNull
	static ShineReceiver of(@NotNull Location location){
		return new BlockShineReceiverImpl(location);
	}
	@NotNull
	static ShineReceiver of(@NotNull Location... locations){
		Collection<ShineReceiver> receivers = new LinkedList<>();
		for (Location location : locations){
			receivers.add(of(location));
		}
		return of(receivers);
	}
	@NotNull
	static ShineReceiver of(@NotNull Block block){
		return new BlockShineReceiverImpl(block.getLocation());
	}
	@NotNull
	static ShineReceiver of(@NotNull Block... blocks){
		Collection<ShineReceiver> receivers = new LinkedList<>();
		for (Block location : blocks){
			receivers.add(of(location));
		}
		return of(receivers);
	}

	@NotNull
	static ShineReceiver of(@NotNull ShineReceiver... shineReceivers){
		return new ForwardingShineReceiverImpl(Arrays.stream(shineReceivers).toList());
	}
	@NotNull
	static ShineReceiver of(@NotNull Collection<ShineReceiver> shineReceivers){
		return new ForwardingShineReceiverImpl(shineReceivers);
	}
}
