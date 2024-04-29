package com.senai.main.service;
import com.senai.main.entity.Produto;
import com.senai.main.repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
        
    @Autowired
    ProdutoRepository produtoRepository;
    
    public Long incluirProduto(Produto produto){
        return produtoRepository.save(produto).getIDProduto();
    }
    public Boolean excluirProduto(Long idProduto){
    
    try{
        produtoRepository.deleteById(idProduto);
        return true;
    } catch (Exception ex){
            System.out.println("Erro ao excluir"
                    + "produto ID: " + idProduto
                    + " Erro: " + ex.getLocalizedMessage());
         return false;
        }
            
    }
    public Optional<Produto> consultarProduto(Long idProduto){
        
            return produtoRepository.findById(idProduto); 
    }
    
    public List<Produto> listarProduto(){
        
        return produtoRepository.findAll();
    }
    
    public Boolean atualizarProduto(Produto produto) {
        
        Produto pdt = produtoRepository.getReferenceById(produto.getIDProduto());
                if(pdt != null){
                    pdt.setDescricaoProduto(produto.getDescricaoProduto());
                    pdt.setNomeProduto(produto.getNomeProduto());
                    pdt.setValorProduto(produto.getValorProduto());
                 return true;
            } else {
                return false;            
        }
    }   
}
