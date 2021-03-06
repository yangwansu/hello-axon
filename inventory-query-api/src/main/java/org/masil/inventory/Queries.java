package org.masil.inventory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Queries {

    @Getter
    @AllArgsConstructor
    public static final class AllInventoryViewQuery {

    }

    @Getter
    @AllArgsConstructor
    public static final class FindInventoryViewQuery {
        private final UUID inventoryId;
    }
}
