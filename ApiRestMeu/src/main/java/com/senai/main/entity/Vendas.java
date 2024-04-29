package com.senai.main.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;

@Table(name = "vendas")
@Entity
public class Vendas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long IDVendas;
   
    @Column(nullable = false)
    private int status;
    
    @OneToMany(mappedBy="vendas",
               fetch=FetchType.LAZY,
               orphanRemoval = true,
               cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<VendasProduto> vendasProduto;     

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    public void setVendasProduto(Set<VendasProduto> vendasProduto) {
        this.vendasProduto = vendasProduto;
    }

    public Set<VendasProduto> getVendasProduto() {
        return vendasProduto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setIDVendas(Long IDVendas) {
        this.IDVendas = IDVendas;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getIDVendas() {
        return IDVendas;
    }

   public int getStatus() {
        return status;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
