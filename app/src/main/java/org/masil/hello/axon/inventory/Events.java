package org.masil.hello.axon.inventory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Events {
    @Getter
    @AllArgsConstructor
    public static class InventoryCreatedEvent {
        private final UUID inventoryId;
    }

    @Getter
    @AllArgsConstructor
    public static class InventoryIncreasedEvent {
        private final UUID inventoryId;
        private final int quantity;
    }

    @Getter
    @AllArgsConstructor
    public static class InventoryDecreasedEvent {
        private final UUID inventoryId;
        private final int quantity;

    }
}
