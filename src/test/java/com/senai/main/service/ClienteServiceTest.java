package com.senai.main.service;

import com.senai.main.entity.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteServiceTest {
    
    @Autowired
    private ClienteService srv;
    private final Random random = new Random();
    
    private final String nomes[] = { "Alice","João","Maria","Pedro","Laura","Marcos","Ana","Gabriel","Bruno",
                              "Manuela","Gustavo","Carolina","Felipe","Juliana","Thiago","Camila","Marcelo",
                              "Vanessa","André","Fernanda","Victor","Gabriela","Daniel" };

    private final String sobrenomes[] = { "Silva", "Santos", "Oliveira", "Souza", "Pereira", "Costa", "Ferreira",
                                    "Rodrigues", "Almeida", "Carvalho", "Gomes", "Martins", "Lima", "Araújo",
                                    "Barbosa", "Ribeiro", "Alves","Cardoso", "Miranda", "Rocha", "Moraes", "Santos",
                                    "Cunha", "Moreira", "Dias", "Castro", "Nascimento", "Nunes", "Mendes", "Torres" };
    
    
    public ClienteServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
        System.out.println("\n############ Fim dos Testes ###################\n");
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

    public List<String> gerarNumeroCPF(int qtd){
        
        List<String> listaCPF = new ArrayList();
        for(int i=0;i<=qtd;i++){
            listaCPF.add(geraRandomico() + geraRandomico() +
               geraRandomico() + geraRandomico().substring(1));
        }
        return listaCPF;
    }
    
    public List<String> gerarNomes(int qtd){
        
        List<String> listaNomes = new ArrayList();
        int idxnome, idxsobre = 0;
        for(int i=0;i<=qtd;i++){
            idxnome = random.nextInt(nomes.length-1);
            idxsobre = random.nextInt(sobrenomes.length -1);
            listaNomes.add( nomes[idxnome] + " " + sobrenomes[idxsobre]);
        }
            return listaNomes;
        }
    
    
    @Test
    @Order(1)
    public void incluirMassaClientes(){
        
        System.out.println("#0 Gerando massa de dados de Clientes para testes...");
        List<String> listaCli = gerarNomes(1000);
        List<String> listaCPF = gerarNumeroCPF(1000);
        List<Long> listaIds = new ArrayList<>();
        for(int i=0;i<listaCli.size();i++){
            Cliente cli = new Cliente();
            cli.setCpf(listaCPF.get(i));
            cli.setNome(listaCli.get(i));
            cli.setEmail(listaCli.get(i).replace(" ","_") + "@gmail.com");
            listaIds.add( srv.incluirCliente(cli) );
        }
        assertTrue(!listaIds.isEmpty(), "#0 ERRO: Não foram gerados Clientes, verifique! ");
    }
    
    
    /**
     * Test of incluirCliente method, of class ClienteService.
     */
    @Test
    @Order(2)
    public void testIncluirCliente() {

        System.out.println("\n############ Início da Rotina de Testes ########\n");
        Cliente cliente = new Cliente();
        cliente.setCpf(gerarNumeroCPF(1).get(0));
        String nomesL = gerarNomes(1).get(0);
        cliente.setEmail(nomesL.replace(" ", "_")+ "@gmail.com");
        cliente.setNome(nomesL);
        Long expResult = null;
        Long result = srv.incluirCliente(cliente);
        assertNotEquals(expResult, result,"#1 ERRO. Não incluiu Cliente no sistema, verifique! ");
        System.out.println("#1 Incluir Cliente no sistema - Ok!");        
        
    }
    
    
    @Test
    @Order(3)
    public void testIncluirClienteSemCpf() {
        System.out.println("incluirCliente");
        Cliente cliente = new Cliente();
        cliente.setEmail("Email");
        cliente.setNome("josé");
        Long expResult = null;
        Long result = srv.incluirCliente(cliente);
        assertNotEquals(expResult, result);


    }
    
    
    @Test
    @Order(4)
    public void testIncluirClienteSemEmail() {
        System.out.println("incluirCliente");
        Cliente cliente = new Cliente();
        cliente.setCpf("Cpf");
        cliente.setNome("josé");
        Long expResult = null;
        Long result = srv.incluirCliente(cliente);
        assertNotEquals(expResult, result);


    }

    
    @Test
    @Order(5)
    public void testIncluirClienteSemNome() {
        System.out.println("incluirCliente");
        Cliente cliente = new Cliente();
        cliente.setCpf("Cpf");
        cliente.setEmail("Email");
        Long expResult = null;
        Long result = srv.incluirCliente(cliente);
        assertNotEquals(expResult, result);

    }
    
    
    @Test
    @Order(6)
    public void testExcluirClienteIdNull() {
        Boolean result = srv.excluirCliente(null);
        assertFalse(result,"#2 Excluir Cliente ID nulo, ERRO: Excluiu Cliente");
        System.out.println("#2 Não excluir Cliente ID nulo. - Ok!");
    }

    
    /**
     * Test of excluirCliente method, of class ClienteService.
     */
    @Test
    @Order(7)
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
    @Order(8)
    public void testConsultarCliente() {
        System.out.println("consultarCliente");
        Long idCliente = null;
        ClienteService instance = new ClienteService();
        Optional<Cliente> expResult = null;
        Optional<Cliente> result = instance.consultarCliente(idCliente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of listarClientes method, of class ClienteService.
     */
    @Test
    @Order(9)
    public void testListarClientes() {
        System.out.println("listarClientes");
        ClienteService instance = new ClienteService();
        List<Cliente> expResult = null;
        List<Cliente> result = instance.listarClientes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    
    @Test
    @Order(10)
    public void testListarClientesOrdem() {
        System.out.println("listarClientesOrdem");
        String ordem = "";
        Integer pag = null;
        ClienteService instance = new ClienteService();
        List<Cliente> expResult = null;
        List<Cliente> result = instance.listarClientesOrdem(ordem, pag);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of atualizarCliente method, of class ClienteService.
     */
    @Test
    @Order(11)
    public void testAtualizarCliente() {
        System.out.println("atualizarCliente");
        Cliente cliente = null;
        ClienteService instance = new ClienteService();
        Boolean expResult = null;
        Boolean result = instance.atualizarCliente(cliente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
