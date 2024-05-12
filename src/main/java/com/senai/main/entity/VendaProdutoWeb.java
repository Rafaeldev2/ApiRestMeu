package com.senai.main.entity;

/**
 *
 * @author rafael_t_moraes
 */
public class VendaProdutoWeb {
    
    private Long IDVendasProduto;
    private Double valorProduto;
    private int qtdProduto;
    private Long IDVendas;
    private Long IDProduto;

    public void setIDVendasProduto(Long IDVendasProduto) {
        this.IDVendasProduto = IDVendasProduto;
    }

    public void setValorProduto(Double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public void setIDVendas(Long IDVendas) {
        this.IDVendas = IDVendas;
    }

    public void setIDProduto(Long IDProduto) {
        this.IDProduto = IDProduto;
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

    public Long getIDVendas() {
        return IDVendas;
    }

    public Long getIDProduto() {
        return IDProduto;
    }
}
