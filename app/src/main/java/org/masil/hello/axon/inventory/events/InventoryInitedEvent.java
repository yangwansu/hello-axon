package org.masil.hello.axon.inventory.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class InventoryInitedEvent {
    private final UUID inventoryId;
}
