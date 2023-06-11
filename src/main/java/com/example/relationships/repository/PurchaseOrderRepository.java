package com.example.relationships.repository;

import com.example.relationships.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    @Query("update PurchaseOrder po set po.deleted=true where po.id=?1 and po.deleted = false")
    @Modifying
    int softDelete(Long id);


    @Query("SELECT COUNT(po) > 0 FROM PurchaseOrder po WHERE po.id = :id and po.deleted = false")
    boolean exists(@Param("id") Long id);
}
