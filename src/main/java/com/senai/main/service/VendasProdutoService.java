package com.senai.main.service;
import com.senai.main.entity.Produto;
import com.senai.main.entity.VendaProdutoWeb;
import com.senai.main.entity.Vendas;
import com.senai.main.entity.VendasProduto;
import com.senai.main.repository.ProdutoRepository;
import com.senai.main.repository.VendasProdutosRepository;
import com.senai.main.repository.VendasRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael_t_moraes
 */

@Service
public class VendasProdutoService {
    
    @Autowired
    private VendasProdutosRepository vendasProdutosRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private VendasRepository vendasRepository;
    
    
     public Long IncluirVendasProduto (VendasProduto vendasProduto){
       return vendasProdutosRepository.save(vendasProduto).getIDVendasProduto ();
   }
     public Boolean excluirVendasProduto(Long IDVendasProduto){
        
    try{
     vendasProdutosRepository.deleteById(IDVendasProduto);
   return true;
   } catch(Exception ex){
   System.out.println("Erro ao excluir"
                      + "  ID: " + IDVendasProduto
                      + " Erro: " + ex.getLocalizedMessage());
   return false;
 }
}
     
    public Optional<VendasProduto> consultarVendasProduto(Long IDVendasProduto){
        
        return vendasProdutosRepository.findById(IDVendasProduto);
}
    public List<VendasProduto> listarVendasProduto(){
        
        return vendasProdutosRepository.findAll();
    }
    @Transactional
    public Boolean atualizarVendasProduto(VendaProdutoWeb vendasProduto) {
        
        VendasProduto vndp = vendasProdutosRepository.getReferenceById(vendasProduto.getIDVendasProduto());
        if( vndp != null) {
            vndp.setIDVendasProduto(vendasProduto.getIDVendasProduto());
            Produto prod = produtoRepository.getReferenceById(vendasProduto.getIDProduto());
            Vendas vend = vendasRepository.getReferenceById(vendasProduto.getIDVendas());
            vndp.setProduto(prod);
            vndp.setQtdProduto(vendasProduto.getQtdProduto());
            vndp.setValorProduto(vendasProduto.getValorProduto());
            vndp.setVendas(vend);
           vendasProdutosRepository.save(vndp);
            return true;
        } else {
            return false;            
        }
    }
}
