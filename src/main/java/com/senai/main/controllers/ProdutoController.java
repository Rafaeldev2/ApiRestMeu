package com.senai.main.controllers;
import com.senai.main.entity.MsgRetorno;
import com.senai.main.entity.Produto;
import com.senai.main.service.ProdutoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ProdutoController {
    @Autowired
            ProdutoService produtoservice;            
            //GET  http://localhost:8010/apirest/cliente/12       
            @CrossOrigin(origins = "*")
            @GetMapping("/produto/{id}")
            public ResponseEntity<Object> consultaProduto(@PathVariable(value = "id")Long idProduto){
            
            Optional<Produto> produto = produtoservice.consultarProduto(idProduto);
        if(produto.isPresent()){
            return new ResponseEntity<>(produto.get(), HttpStatus.OK);
        } else {
            MsgRetorno erro = new MsgRetorno();
            erro.setFuncao("Consultar Produto");
            erro.setDescrição("Erro ao consultar Produto ID: " + idProduto );
            return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);            
      }
   }
            @CrossOrigin(origins = "*")
            @GetMapping("/produto")
            public ResponseEntity<Object> listarProduto() {
            List<Produto> produtos = produtoservice.listarProduto();
            return new ResponseEntity<>(produtos, HttpStatus.OK);}
            
            @CrossOrigin(origins = "*")
            @PostMapping(value = "/produto", consumes = {"application/json"})
            public ResponseEntity<Object> IncluirProduto(@Valid @RequestBody Produto produto){

            Long idPdt = produtoservice.incluirProduto(produto);
            if(idPdt != null && idPdt > 0){
                return new ResponseEntity<>(idPdt, HttpStatus.OK);
            } else {
                MsgRetorno erro = new MsgRetorno();
                erro.setFuncao("Incluir Produto");
                erro.setDescrição("Erro ao incluir Produto! Chame a TI!!");
                return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);            
            }
        }
            
        @CrossOrigin(origins = "*")
        @PutMapping("/produto")
            public ResponseEntity atualizarProduto(@Valid @RequestBody Produto produto){
            MsgRetorno msg = new MsgRetorno();
            msg.setFuncao("Atualizar Produto");
                if(produtoservice.atualizarProduto(produto)){
                    msg.setDescrição("Produto ID " + produto.getIDProduto()+ " atualizado com sucesso!");
                    return new ResponseEntity<>(msg, HttpStatus.OK);
                        } else {
                            msg.setDescrição("Erro ao atualizar produto ID " + produto.getIDProduto());
                            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
                        }
                }
            
            
            @CrossOrigin(origins = "*")
            @DeleteMapping("/produto/{id}")
                    public ResponseEntity deletarProduto(@PathVariable(value = "id") Long id){
                    MsgRetorno msg = new MsgRetorno();
                        msg.setFuncao("Excluir Produto");
                        if (produtoservice.excluirProduto(id)){
                        msg.setDescrição("Produto ID " + id + " excluído com sucesso!");
                        return new ResponseEntity<>(msg, HttpStatus.OK);
                            } else {
                            msg.setDescrição("Erro ao excluir produto ID " + id + ", não cadastrado/inexistente!");
                            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
                }
            }
}
