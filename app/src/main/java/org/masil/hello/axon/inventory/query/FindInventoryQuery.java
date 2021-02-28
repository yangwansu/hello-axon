package org.masil.hello.axon.inventory.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class FindInventoryQuery {
    private final UUID inventoryId;
}
