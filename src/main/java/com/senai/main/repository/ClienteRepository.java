package com.senai.main.repository;

import com.senai.main.entity.Cliente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{    

    Cliente findByCpf(String cpf);
    public void save(Optional cli);
}
