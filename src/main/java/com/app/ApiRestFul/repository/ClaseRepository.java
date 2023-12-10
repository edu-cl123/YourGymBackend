package com.app.ApiRestFul.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.app.ApiRestFul.model.Client;
import com.app.ApiRestFul.model.Clase;


@Repository
public interface ClaseRepository extends JpaRepository<Clase,Long>{
	//aqui van las consultas
	
	//Consulta para buscar las clase por el nombre (filtro de busqueda)
	@Query(value = "SELECT * FROM CLASE AS a WHERE a.name LIKE %?1%",nativeQuery = true)
	List<Clase> getByName(String title);

   
	
}
