package com.senai.main.service;
import com.senai.main.entity.Cliente;
import com.senai.main.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClienteService {
        
    @Autowired
    ClienteRepository clienteRepository;
    
    @Value("${apiweb.pagina.reg}")
    private Integer regpag;
    
    private Pageable pageable;
    
    public Long incluirCliente(Cliente cliente){
        
        String cpf = cliente.getCpf();
        Cliente cli = clienteRepository.findByCpf(cpf);
        if(cli == null){
           return clienteRepository.save(cliente).getIDCliente();
        }
        return null;
    }
    public Boolean excluirCliente(Long idCliente){
    
    try{
        clienteRepository.deleteById(idCliente);
        return true;
    } catch (Exception ex){
            System.out.println("Erro ao excluir"
                    + "cliente ID: " + idCliente
                    + " Erro: " + ex.getLocalizedMessage());
         return false;
        }
            
    }
    public Optional<Cliente> consultarCliente(Long idCliente){
        
            return clienteRepository.findById(idCliente); 
    }
    
    public List<Cliente> listarClientes(){
        
        return clienteRepository.findAll();
    }
    
    public List<Cliente> listarClientesOrdem(String ordem, Integer pag){
        
        pageable = PageRequest.of(pag,regpag,Sort.by(Sort.Direction.ASC, ordem));
        List<Cliente> clientes = clienteRepository.findAll(pageable).toList();
        return clientes;
    }
    
    @Transactional
    public Boolean atualizarCliente(Cliente cliente) {
        
        Cliente cli = clienteRepository.getReferenceById(cliente.getIDCliente());
                if(cli.getIDCliente() != null){
                cli.setCpf(cliente.getCpf());
                cli.setEmail(cliente.getEmail());
                cli.setNome(cliente.getNome());
                clienteRepository.save(cli);
                 return true;
            } else {
                return false;            
        }
    }   
}