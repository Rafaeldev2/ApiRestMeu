package com.senai.main.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;

@Table(name = "produtos")
@Entity
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
        private Long IDProduto;
    
        @Column(nullable = false)
        private String nomeProduto;
        
        @Column(nullable = true)
        private String descricaoProduto;
        
        @Column(nullable = true)
        private Double valorProduto;
        
        @OneToMany
        private Set<VendasProduto> vendasProduto;

    public void setIDProduto(Long IDProduto) {
        this.IDProduto = IDProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public void setValorProduto(Double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public void setVendasProduto(Set<VendasProduto> vendasProduto) {
        this.vendasProduto = vendasProduto;
    }

    public Long getIDProduto() {
        return IDProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public Double getValorProduto() {
        return valorProduto;
    }

    public Set<VendasProduto> getVendasProduto() {
        return vendasProduto;
    }
}
