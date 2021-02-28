package org.masil.hello.axon.inventoryview;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Entity
public class InventoryView {
    @Id
    private UUID id;
    private int quantity;

    protected InventoryView() {
    }
}
