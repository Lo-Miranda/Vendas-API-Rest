package com.udemy.expert.exercicios.domain.repository;

import ch.qos.logback.core.boolex.EvaluationException;
import com.udemy.expert.exercicios.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Integer>{

    //consulta hql:
    @Query(value = " select c from Cliente c where c.nome like :nome ")
    List<Cliente> findByNomeLike(@Param("nome") String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id ")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);



}
