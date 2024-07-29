package bet.astral.shine.receiver;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

record EntityShineReceiverImpl(UUID uniqueId) implements EntityShineReceiver {
	@Override
	public @NotNull UUID getUniqueId() {
		return uniqueId;
	}
}
