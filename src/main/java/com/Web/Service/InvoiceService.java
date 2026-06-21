package com.Web.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Web.Entity.Invoice;
import com.Web.Repository.InvoiceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceService {

	private final InvoiceRepository invoiceRepository;
	
	public InvoiceService(InvoiceRepository invoiceRepository) {
		this.invoiceRepository=invoiceRepository;
	}
	
	public Invoice saveInvoice(Invoice invoice) {
		return invoiceRepository.save(invoice);
	}
	public List<Invoice> fetchInvoices(String clerkId){
		return invoiceRepository.findByClerkId(clerkId);
	}
	public void removeInvoice(Long invoiceId,String clerkId) {
	    Invoice invoice=invoiceRepository.findByClerkIdAndId(clerkId, invoiceId)
	        .orElseThrow(() -> new RuntimeException("Invoice not found: " + invoiceId));
	    
	    invoiceRepository.delete(invoice);
	}
}
