package com.senai.main.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "vendasProduto")
@Entity
public class VendasProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long IDVendasProduto;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonBackReference
    private Vendas vendas;
        
    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonBackReference
    private Produto produto;
    
    @Column(nullable = false)
    private Double valorProduto;
    
    @Column(nullable = false)
    private int qtdProduto;
    
    
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Produto getProduto() {
        return produto;
    }
    
    public Vendas getVendas() {
        return vendas;
    }

    public void setVendas(Vendas vendas) {
        this.vendas = vendas;
    }

    public Long getIDVendasProduto() {
        return IDVendasProduto;
    }

    public Double getValorProduto() {
        return valorProduto;
    }

    public int getQtdProduto() {
        return qtdProduto;
    }

    public void setIDVendasProduto(Long IDVendasProduto) {
        this.IDVendasProduto = IDVendasProduto;
    }

    public void setValorProduto(Double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }  
}
