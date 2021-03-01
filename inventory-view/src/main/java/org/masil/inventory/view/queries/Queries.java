package org.masil.inventory.view.queries;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Queries {


    @Getter
    @AllArgsConstructor
    public final static class FindInventoryViewQuery {
        private UUID inventoryId;
    }


    @Getter
    @AllArgsConstructor
    public final static class AllInventoriesQuery {

    }
}
