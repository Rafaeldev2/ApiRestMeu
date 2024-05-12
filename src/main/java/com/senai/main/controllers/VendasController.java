package com.senai.main.controllers;

import com.senai.main.entity.MsgRetorno;
import com.senai.main.entity.Vendas;
import com.senai.main.service.VendasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

public class VendasController {
            @Autowired
           VendasService vendaservice;
            @Operation(summary = "Manter Vendas no Sistema",description = "Criar uma nova Venda por Cliente")
            @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna um ID de uma nova Venda" ),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor!"),})
            //GET  http://localhost:8010/apirest/cliente/12   
            
            
            @CrossOrigin(origins = "*")
            @GetMapping("/venda/{id}")
            public ResponseEntity<Object> consultarVenda(@PathVariable(value = "id")Long idVenda){
            
            Optional<Vendas> vendas = vendaservice.consultarVenda(idVenda);
        if(vendas.isPresent()){
            return new ResponseEntity<>(vendas.get(), HttpStatus.OK);
        } else {
            MsgRetorno erro = new MsgRetorno();
            erro.setFuncao("Consultar Venda");
            erro.setDescrição("Erro ao consultar Venda ID: " + idVenda );
            return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);            
      }
   }
            @CrossOrigin(origins = "*")
            @GetMapping("/venda")
            public ResponseEntity<Object> listarVenda() {
            List<Vendas> produtos = vendaservice.listarVenda();
            return new ResponseEntity<>(produtos, HttpStatus.OK);}
            
            @CrossOrigin(origins = "*")
            @PostMapping("/vendas/{IdCliente}")
            public ResponseEntity<Object> incluirVenda(@Valid @RequestBody Vendas vendas){

            Long idVnd = vendaservice.incluirVenda(vendas);
            if(idVnd != null && idVnd > 0){
                return new ResponseEntity<>(idVnd, HttpStatus.OK);
            } else {
                MsgRetorno erro = new MsgRetorno();
                erro.setFuncao("Incluir Venda");
                erro.setDescrição("Erro ao incluir Venda! Chame a TI!!");
                return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);            
            }
        }
            
        @CrossOrigin(origins = "*")
        @PutMapping("/venda")
            public ResponseEntity atualizarVenda(@Valid @RequestBody Vendas vendas){
            MsgRetorno msg = new MsgRetorno();
            msg.setFuncao("Atualizar Venda");
                if(vendaservice.atualizarVenda(vendas)){
                    msg.setDescrição("Venda ID " + vendas.getIDVendas()+ " atualizado com sucesso!");
                    return new ResponseEntity<>(msg, HttpStatus.OK);
                        } else {
                            msg.setDescrição("Erro ao atualizar venda ID " + vendas.getIDVendas());
                            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
                        }
                }
            
            
            @CrossOrigin(origins = "*")
            @DeleteMapping("/venda/{id}")
                    public ResponseEntity excluirVenda(@PathVariable(value = "id") Long id){
                    MsgRetorno msg = new MsgRetorno();
                        msg.setFuncao("Excluir Venda");
                        if (vendaservice.excluirVenda(id)){
                        msg.setDescrição("Venda ID " + id + " excluído com sucesso!");
                        return new ResponseEntity<>(msg, HttpStatus.OK);
                            } else {
                            msg.setDescrição("Erro ao excluir venda ID " + id + ", não cadastrado/inexistente!");
                            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
                }
            }
}
