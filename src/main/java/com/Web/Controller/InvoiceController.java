package com.Web.Controller;


import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.Web.Entity.Invoice;
import com.Web.Service.EmailService;
import com.Web.Service.InvoiceService;


@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

	private final InvoiceService invoiceService;
	private final EmailService emailService;

	public InvoiceController(InvoiceService invoiceService,EmailService emailService) {
		this.invoiceService=invoiceService;
		this.emailService = emailService;
	}
	@PostMapping
	public ResponseEntity<Invoice>  saveInvoice(@RequestBody Invoice invoice) {
		return ResponseEntity.ok(invoiceService.saveInvoice(invoice));
	}

	@GetMapping
	public ResponseEntity<List<Invoice>> fetchInvoices(Authentication authentication){
		return ResponseEntity.ok(invoiceService.fetchInvoices(authentication.getName()));
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removeInvoice(@PathVariable Long id,Authentication authentication){
		if(authentication.getName()!= null) {
			invoiceService.removeInvoice(id,authentication.getName());
			return ResponseEntity.noContent().build();
		}
		throw new ResponseStatusException(HttpStatus.FORBIDDEN,"User does not have permission to access this resource");
	}
	@PostMapping("/sendinvoice")
	public ResponseEntity<?> sendInvoice(@RequestPart("file") MultipartFile file,
			                             @RequestPart("email") String customerEmail){
		try {
			emailService.sendInvoiceEmail(customerEmail, file);
			return ResponseEntity.ok().body("Invoice sent successfully.");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send invoice.");
		}
	}
	
	
	
}
