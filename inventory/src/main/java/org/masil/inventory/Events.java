package org.masil.inventory;

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
        private UUID inventoryId;
    }
    @Getter
    @AllArgsConstructor
    public static class InventoryIncreasedEvent {
        private UUID inventoryId;
        private int delta;
        private int remained;
    }

    @Getter
    @AllArgsConstructor
    public static class InventoryDecreasedEvent {
        private UUID inventoryId;
        private int delta;
        private int remained;
    }
}
