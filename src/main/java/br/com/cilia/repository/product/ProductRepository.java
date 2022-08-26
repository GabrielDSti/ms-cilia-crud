package br.com.cilia.repository.product;

import br.com.cilia.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(" select p.name from Product p where p.name like :name ")
    String findByName(@Param("name") String name);
}
