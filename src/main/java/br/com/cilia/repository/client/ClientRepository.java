package br.com.cilia.repository.client;

import br.com.cilia.entity.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository  extends JpaRepository<Client, Long> {

    @Query(" select c.email from Client c where c.email = :email ")
    String findByEmail(@Param("email") String email);
}
