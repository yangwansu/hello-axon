package org.masil.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventoryViewRepository extends JpaRepository<InventoryView, UUID> {


}
