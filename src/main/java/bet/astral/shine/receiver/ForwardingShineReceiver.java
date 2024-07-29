package bet.astral.shine.receiver;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;

public interface ForwardingShineReceiver extends ShineReceiver, Iterable<ShineReceiver> {
	@NotNull Collection<ShineReceiver> getReceivers();

	@NotNull
	@Override
	default Iterator<ShineReceiver> iterator() {
		return getReceivers().iterator();
	}
}
