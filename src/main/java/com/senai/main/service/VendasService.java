package com.senai.main.service;

import com.senai.main.entity.Vendas;
import com.senai.main.repository.VendasRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VendasService {
    @Autowired
    VendasRepository vendasRepository;
    
    public Long incluirVenda(Vendas vendas){
        return vendasRepository.save(vendas).getIDVendas();
    }
    public Boolean excluirVenda(Long idVenda){
    
    try{
        vendasRepository.deleteById(idVenda);
        return true;
    } catch (Exception ex){
            System.out.println("Erro ao excluir"
                    + "venda ID: " + idVenda
                    + " Erro: " + ex.getLocalizedMessage());
         return false;
        }
            
    }
    public Optional<Vendas> consultarVenda(Long idVenda){
        
            return vendasRepository.findById(idVenda); 
    }
    
    public List<Vendas> listarVenda(){
        
        return vendasRepository.findAll();
    }
    
    @Transactional
    public Boolean atualizarVenda(Vendas venda) {
        
        Vendas vnd = vendasRepository.getReferenceById(venda.getIDVendas());
                if(vnd   != null){
                    vnd.setCliente(venda.getCliente());
                    vnd.setIDVendas(venda.getIDVendas());
                    vnd.setStatus(venda.getStatus());
                    vnd.setVendasProduto(venda.getVendasProduto());
                   vendasRepository.save(vnd);
                 return true;
            } else {
                return false;            
        }
    }   
}
