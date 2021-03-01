package org.masil.hello.axon.inventory.view.app;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Queries {

    @Getter
    @AllArgsConstructor
    public static class FindInventoryViewQuery {
        private final UUID inventoryId;
    }

    public static class AllInventoryViewQuery {

    }
}
