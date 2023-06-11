package com.example.relationships.repository;

import com.example.relationships.model.PurchaseOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    @Query("update PurchaseOrder po set po.deleted=true where po.id=?1 and po.deleted = false")
    @Modifying
    int softDelete(Long id);

    @Query("SELECT COUNT(po) > 0 FROM PurchaseOrder po WHERE po.id = :id and po.deleted = false")
    boolean exists(@Param("id") Long id);

    Optional<PurchaseOrder> findByIdAndDeleted(Long id,Boolean deleted);


    @EntityGraph(attributePaths= {"lines"})
    Page<PurchaseOrder> findByDeleted(Boolean deleted,Pageable pageable);

    @Query(value = "from PurchaseOrder po inner join fetch po.lines pol where po.deleted = :deleted",
            countQuery =  "select count(po) from PurchaseOrder po where po.deleted = :deleted")
    Page<PurchaseOrder> findAllByJpqlQuery(@Param("deleted") Boolean deleted, Pageable pageable);
}
