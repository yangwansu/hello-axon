package org.masil.hello.axon.inventory.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class InventoryView {
    @Id
    private UUID inventoryId;

    private int quantity;
}
