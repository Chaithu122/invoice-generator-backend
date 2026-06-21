package com.Web.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clerkId;
    private String template;

    private String title;
    private int tax;
    private String notes;
    private String logo;
    private String thumbnail;
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="name", column=@Column(name="billing_name")),
        @AttributeOverride(name="phone", column=@Column(name="billing_phone")),
        @AttributeOverride(name="address", column=@Column(name="billing_address"))
    })
    private Billing billing;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="name", column=@Column(name="shipping_name")),
        @AttributeOverride(name="phone", column=@Column(name="shipping_phone")),
        @AttributeOverride(name="address", column=@Column(name="shipping_address"))
    })
    private Shipping shipping;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="name", column=@Column(name="account_name")),
        @AttributeOverride(name="number", column=@Column(name="account_number")),
        @AttributeOverride(name="ifsccode", column=@Column(name="account_ifsccode"))
    })
    private Account account;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="name", column=@Column(name="company_name")),
        @AttributeOverride(name="phone", column=@Column(name="company_phone")),
        @AttributeOverride(name="address", column=@Column(name="company_address"))
    })
    private Company company;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="number", column=@Column(name="invoice_number")),
        @AttributeOverride(name="date", column=@Column(name="invoice_date")),
        @AttributeOverride(name="dueDate", column=@Column(name="invoice_due_date"))
    })
    private InvoiceDetails invoice;

    @ElementCollection
    @CollectionTable(name = "invoice_items", joinColumns = @JoinColumn(name = "invoice_id"))
    private List<Item> items = new ArrayList<>();

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime lastUpdatedAt = LocalDateTime.now();

   

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getTax() { return tax; }
    public void setTax(int tax) { this.tax = tax; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }

    public Billing getBilling() { return billing; }
    public void setBilling(Billing billing) { this.billing = billing; }

    public Shipping getShipping() { return shipping; }
    public void setShipping(Shipping shipping) { this.shipping = shipping; }

    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }

    public InvoiceDetails getInvoice() { return invoice; }
    public void setInvoice(InvoiceDetails invoice) { this.invoice = invoice; }

    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getLastUpdatedAt() { return lastUpdatedAt; }
    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) { this.lastUpdatedAt = lastUpdatedAt; }
    public String getClerkId() {
        return clerkId;
    }

    public void setClerkId(String clerkId) {
        this.clerkId = clerkId;
    }
    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

 

    @Embeddable
    public static class Billing {
        private String name;
        private String phone;
        private String address;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }

        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
    }

    @Embeddable
    public static class Shipping {
        private String name;
        private String phone;
        private String address;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }

        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
    }

    @Embeddable
    public static class Account {
        private String name;
        private String number;
        private String ifsccode;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getNumber() { return number; }
        public void setNumber(String number) { this.number = number; }

        public String getIfsccode() { return ifsccode; }
        public void setIfsccode(String ifsccode) { this.ifsccode = ifsccode; }
    }

    @Embeddable
    public static class Company {
        private String name;
        private String phone;
        private String address;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }

        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
    }

    @Embeddable
    public static class InvoiceDetails {
        private String number;
        private LocalDate date;
        private LocalDate dueDate;

        public String getNumber() { return number; }
        public void setNumber(String number) { this.number = number; }

        public LocalDate getDate() { return date; }
        public void setDate(LocalDate date) { this.date = date; }

        public LocalDate getDueDate() { return dueDate; }
        public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    }

    @Embeddable
    public static class Item {
        private String name;
        private int qty;
        private double amount;
        private String description;
        private double total;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public int getQty() { return qty; }
        public void setQty(int qty) { this.qty = qty; }

        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public double getTotal() { return total; }
        public void setTotal(double total) { this.total = total; }
       
    }
}