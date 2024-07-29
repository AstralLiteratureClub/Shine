package bet.astral.shine.receiver;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

record ForwardingShineReceiverImpl(Collection<ShineReceiver> receivers) implements ForwardingShineReceiver {

	@Override
	public @NotNull Collection<ShineReceiver> getReceivers() {
		return receivers;
	}
}
