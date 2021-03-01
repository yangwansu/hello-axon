package org.masil.inventory.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

public class Events {

    @Getter
    @AllArgsConstructor
    public static final class InventoryCreatedEvent {
        private final UUID inventoryId;
    }

    @Getter
    @AllArgsConstructor
    public static final class InventoryIncreasedEvent {
        private final UUID inventoryId;
        private final int quantity;
    }

    @Getter
    @AllArgsConstructor
    public static final class InventoryDecreasedEvent {
        private final UUID inventoryId;
        private final int quantity;
    }
}
