package com.Web.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Web.Entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
 List<Invoice> findByClerkId(String id);
 
 Optional<Invoice> findByClerkIdAndId(String clerkId,Long id);
}
