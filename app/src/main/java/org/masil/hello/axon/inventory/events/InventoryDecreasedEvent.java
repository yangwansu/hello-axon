package org.masil.hello.axon.inventory.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class InventoryDecreasedEvent {

    private final UUID inventoryId;
    private final int quantity;
}
