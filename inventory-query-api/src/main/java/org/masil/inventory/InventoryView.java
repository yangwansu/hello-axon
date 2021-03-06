package org.masil.inventory;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class InventoryView {

    @Id
    private UUID uuid;
    private int quantity;


}
