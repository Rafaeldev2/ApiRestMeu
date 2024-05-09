package com.senai.main.controllers;
import com.senai.main.entity.MsgRetorno;
import com.senai.main.entity.VendaProdutoWeb;
import com.senai.main.entity.VendasProduto;
import com.senai.main.service.VendasProdutoService;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rafael_t_moraes
 */


@RestController
public class VendasProdutoController {
    @Autowired
    VendasProdutoService vendasProdutoService;
    
    
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/vendasproduto", consumes = {"application/json"})
    public ResponseEntity<Object> incluirVendasProduto(@Valid @RequestBody VendasProduto vendasproduto){
        
        Long idVndp = vendasProdutoService.IncluirVendasProduto(vendasproduto);
        if(idVndp != null && idVndp > 0){
            return new ResponseEntity<>(idVndp, HttpStatus.OK);
        } else {
            MsgRetorno erro = new MsgRetorno();
            erro.setFuncao("Incluir VendasProduto");
            erro.setDescrição("Erro ao incluir VendasProduto! Chame a TI!!");
            return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);            
        }
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/vendasproduto/{id}")
    public ResponseEntity<Object> consultaVendasProduto(@PathVariable(value = "id") Long idVendasProduto){
        
        Optional<VendasProduto> vendasproduto = vendasProdutoService.consultarVendasProduto(idVendasProduto);
        if(vendasproduto.isPresent()){
            return new ResponseEntity<>(vendasproduto.get(), HttpStatus.OK);
        } else {
            MsgRetorno erro = new MsgRetorno();
            erro.setFuncao("Consultar VendasProduto");
            erro.setDescrição("Erro ao consultar VendasProduto ID: " + idVendasProduto );
            return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);            
        }
    }
  
    
  
    @CrossOrigin(origins = "*")
    @PutMapping("/vendasproduto")
    public ResponseEntity<Object>atualizarVendasProduto( @Valid @RequestBody VendaProdutoWeb vndweb)
    {
        MsgRetorno msg = new MsgRetorno();
        msg.setFuncao("Atualizar VendasProduto");
        if(vendasProdutoService.atualizarVendasProduto(vndweb)){
            msg.setDescrição("VendasProduto IdVendasProduto ("+ vndweb.getIDVendasProduto() + ") atualizado com sucesso!");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }else{
            msg.setDescrição("VendasProduto IdVendasProduto ("+ vndweb.getIDVendasProduto() + ") Erro ao atualizar VendasProduto.");
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
    }    
    
    
    @CrossOrigin(origins = "*")
    @DeleteMapping("/VendasProduto/{id}")
    public ResponseEntity<Object> excluirVendasProduto(@PathVariable(value = "id") long id){
        
        MsgRetorno msg = new MsgRetorno();
        msg.setFuncao("Excluir cliente");
        if ( vendasProdutoService.excluirVendasProduto(id) ){
            msg.setDescrição("VendasProduto IdVendasProduto ("+ id + ") excluído com sucesso!");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } else {
            msg.setFuncao("Excluir VendasProduto");
            msg.setDescrição("Erro ao excluir VendasProduto IdVendasProduto("+id+"), não cadastrado/inexistente!");
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
    }    
}