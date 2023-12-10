package com.app.ApiRestFul.services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ApiRestFul.model.Clase;
import com.app.ApiRestFul.repository.ClaseRepository;
import com.app.ApiRestFul.exceptions.RecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Service
public class ClaseService {
	@Autowired
	ClaseRepository repository;

	private static final Logger log4 = LoggerFactory.getLogger(ClaseService.class);

	/**
	 * 
	 * @return Una lista de todos las clases de la BBDD
	 * @throws RecordNotFoundException
	 */
	public List<Clase> getAllClass() throws RecordNotFoundException {
		List<Clase> result = repository.findAll();
		if (!result.isEmpty()) {
			log4.info("Se han obtenido todos las clases con �xito");
			return result;
		} else {
			log4.error("ERROR: No se han encontrado clases en la base de datos");
			throw new RecordNotFoundException("No hay valores");
		}

	}

	/**
	 * @param kid
	 * @return Crea una clase con los parametros pasados en la BBDD
	 * @throws RecordNotFoundException,NullPointerException,IllegalArgumentException
	 */
	public Clase createClass (Clase clase) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (clase != null) {
				try {
					clase = repository.save(clase);
					log4.info("clase creada con �xito");
					return clase;
				} catch (IllegalArgumentException e) {
					log4.error("Se han recibido datos incorrectos al crear la clase, ERROR: " + e);
					throw new IllegalArgumentException(
							"Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
				}

		} else {
			log4.error("ERROR: Hay datos que son nulos al crear la clase");
			throw new NullPointerException("Valor nulo");
		}

	}

	/**
	 * @param kid
	 * @return una clase que ya existe actualizado con nuevos valores
	 * @throws RecordNotFoundException, NullPointerException,
	 *                                  IllegalArgumentException
	 */
	public Clase Update(Clase clase) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (clase != null) {
			try {
				Optional<Clase> n = repository.findById(clase.getId());// si hay algo lo busca por id en la bbdd
				if (n.isPresent()) { // UPDATE
					Clase newClass = n.get(); // se trae la clase que hemos metido para setear los campos
					newClass.setId(clase.getId());
					newClass.setName(clase.getName());
					newClass.setDescripcion(clase.getDescripcion());
					newClass.setClient(clase.getClient());
					newClass = repository.save(newClass);
					log4.info("INFO: La clase con nombre: " + clase.getName() + ", ha sido actualizado al nombre: "
							+ newClass.getName());
					return newClass;

				} else {
					log4.error("ERROR: Se han recibido datos incorrectos al crear una clase");
					throw new IllegalArgumentException("Los valores introducidos no son correctos");
				}
			} catch (Exception e) {
				log4.error("ERROR: valores mal introducidos al actulizar una clase");
				throw new RecordNotFoundException("Los valores introducidos no son correctos");
			}

		} else {
			log4.error("ERROR: Hay datos que son nulos al crear nino");
			throw new NullPointerException("Valor nulo");
		}
	}

	/**
	 * @param id
	 * @return borra a una clase en concreto con el id pasado por parametro de la BBDD
	 * @throws RecordNotFoundException, IllegalArgumentException,
	 *                                  NullPointerException
	 */
	public void deleteClassById(Long id) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (id != null && id > -1) {
			try {
				Optional<Clase> clase = repository.findById(id);
				if (clase.isPresent()) {
					log4.info("INFO: Clase eliminada con id: " + id);
					repository.deleteById(id);

				}
			} catch (Exception e) {
				log4.error("ERROR: Valores nulos introducidos");
				throw new IllegalArgumentException(
						"Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
			}
		} else {
			log4.error("ERROR: Valores nulos introducidos");
			throw new NullPointerException();
		}
	}

	/**
	 * @param id
	 * @return una clase en concreto de la BBDD con el id pasado por parametro
	 * @throws RecordNotFoundException, NullPointerException,
	 *                                  IllegalArgumentException
	 */
	public Clase getClassById(Long id) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (id != null) {
			try {
				Optional<Clase> result = repository.findById(id);
				if (result.isPresent()) {
					log4.info("INFO: clase con id " + id + " ha sido encontrado en la base de datos");
					return result.get();
				} else {
					log4.info("ERROR: No hay resultados en obtener una clase para el id: " + id);
					throw new RecordNotFoundException("No se han encontrado valores para el id: ", id);
				}
			} catch (IllegalArgumentException e) {
				log4.info("ERROR: Los datos introducidos para encontrar una clase por id no son correctos");
				throw new IllegalArgumentException(
						"Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
			}
		} else {
			log4.info("ERROR: Los datos introducidos para encontrar una clase por id son nulos");
			throw new NullPointerException("Valor nulo");
		}
	}

	/**
	 * @param title
	 * @return un nino en concreto de la BBDD con el nombre pasado por parametro
	 * @throws RecordNotFoundException, NullPointerException,
	 *                                  IllegalArgumentException
	 */
	public List<Clase> getClassByName(String title)
			throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (title != null) {
			try {
				List<Clase> list = repository.getByName(title);

				if (list.size() > 0) {
					log4.info("INFO: La busqueda de clases encontrados por nombre ha sido realizada");
					return list;
				} else {
					log4.info("ERROR: No hay resultados en obtener una clase para el nombre: " + title);
					throw new RecordNotFoundException("No hay resultados", title);
				}

			} catch (IllegalArgumentException e) {
				log4.info("ERROR: Los datos introducidos para encontrar una clase por nombre no son correctos");
				throw new IllegalArgumentException(
						"Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
			}
		} else {
			log4.info("ERROR: Los datos introducidos para encontrar una clase por nombre son nulos");
			throw new NullPointerException();
		}
	}

}