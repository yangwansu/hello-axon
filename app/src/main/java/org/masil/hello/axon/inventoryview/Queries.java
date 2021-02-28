package org.masil.hello.axon.inventoryview;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Queries {


    public static final class AllInventoryQuery {

    }

    @Getter
    @AllArgsConstructor
    public static final class FindInventoryQuery {
        private final UUID inventoryId;
    }
}
