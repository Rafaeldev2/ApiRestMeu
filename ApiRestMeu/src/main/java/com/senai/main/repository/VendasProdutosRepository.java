
package com.senai.main.repository;

import com.senai.main.entity.VendasProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendasProdutosRepository extends JpaRepository<VendasProduto, Long>{   
}
