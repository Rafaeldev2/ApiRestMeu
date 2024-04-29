/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.senai.main.service;

import com.senai.main.entity.Cliente;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClienteServiceTest {
    
    @Autowired
    private ClienteService srv;
    Random random = new Random();
    public ClienteServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    public String geraRandomico(){
        int numero = random.nextInt(999);
        String snumero = String.valueOf(numero);
        if(snumero.length() == 2){ snumero = "0" + snumero; }
        if(snumero.length() == 1){ snumero = "00" + snumero; }
        return snumero;
    }
    
    public String geraNomes(){
        int idxnome = 
    }

    /**
     * Test of incluirCliente method, of class ClienteService.
     */
    @Test
    public void testIncluirCliente() {
        System.out.println("incluirCliente");
        Cliente cliente = new Cliente();
        cliente.setCpf("901.888.512-91");
        cliente.setEmail("zezinho@gmail.com");
        cliente.setNome("José da Silva");
        Long id = srv.incluirCliente(cliente);
        Long expResult = null;
        assertEquals(expResult, id);
    }

    /**
     * Test of excluirCliente method, of class ClienteService.
     */
    @Test
    public void testExcluirCliente() {
        System.out.println("excluirCliente");
        Long idCliente =52L;
        Boolean expResult = true;
        Boolean result = srv.excluirCliente(idCliente);
        assertEquals(expResult, result);
    }

    /**
     * Test of consultarCliente method, of class ClienteService.
     */
    @Test
    public void testConsultarCliente() {
        System.out.println("consultarCliente");
        Long idCliente = 2L;
        Optional<Cliente> expResult = null;
        Optional<Cliente> result = srv.consultarCliente(idCliente);
        System.out.println("Nome do cliente 152: " + result.get().getNome());
        assertNotEquals(expResult, result);
    }

    /**
     * Test of listarClientes method, of class ClienteService.
     */
    @Test
    public void testListarClientes() {
        System.out.println("listarClientes");
        List<Cliente> expResult = null;
        List<Cliente> result = srv.listarClientes();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of atualizarCliente method, of class ClienteService.
     */
    @Test
    public void testAtualizarCliente() {
        System.out.println("atualizarCliente");
        Cliente cliente = new Cliente();
        Long idCliente = Long.valueOf("2");
        cliente.setIDCliente(idCliente);
        cliente.setCpf("999.201.615-74");
        cliente.setEmail("rafael@gmail.com");
        cliente.setNome("rafael do socorro");
        Boolean expResult = true;
        Boolean result = srv.atualizarCliente(cliente);
        assertEquals(expResult, result);
    }
    
}
