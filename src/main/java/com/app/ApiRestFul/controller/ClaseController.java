package com.app.ApiRestFul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.ApiRestFul.model.Clase;
import com.app.ApiRestFul.services.ClaseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/class")
public class ClaseController {
	@Autowired
	ClaseService service;

	/**
	 * @return EndPoint que nos devuelve un HttpStatus.OK y todos las clases que
	 *         existan en la BBDD a traves del servicio
	 */
    @ApiOperation(value = "Get all class", notes = "Returns a class list")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
	@GetMapping
	public ResponseEntity<List<Clase>> getAllClass() {
		try {
			List<Clase> all = service.getAllClass();
			return new ResponseEntity<List<Clase>>(all, new HttpHeaders(), HttpStatus.OK);
		} catch (ResponseStatusException e) {
			List<Clase> all = service.getAllClass();
			return new ResponseEntity<List<Clase>>(all, new HttpHeaders(), HttpStatus.OK);
		}
	}

	/**
	 * @param name
	 * @return lista de clase en concreto de la BBDD con el nombre pasado por
	 *         parametro
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en funcion de la
	 *                                 peticion)
	 */
    @ApiOperation(value = "Get a class by name", notes = "Returns a class as per the name")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,class found"),
      @ApiResponse(code = 404, message = "ERROR:class not found"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
	@GetMapping("/search/{name}")
	public ResponseEntity<List<Clase>> getClassByName(@PathVariable("name") String name) throws ResponseStatusException {
		if (name != null) {
			try {
				List<Clase> all = service.getClassByName(name);
				return new ResponseEntity<List<Clase>>(all, new HttpHeaders(), HttpStatus.OK);
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,
						"la clase con nombre: " + name + "no se ha encontrado", e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

	}

	/**
	 * @param id
	 * @return una clase en concreto de la BBDD con el id pasado por parametro
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en funcion de la
	 *                                 peticion)
	 */
    @ApiOperation(value = "Get a class by id", notes = "Returns a class as per the id")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,kid found"),
      @ApiResponse(code = 404, message = "ERROR:class not found"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
	@GetMapping("/{id}")
	public ResponseEntity<Clase> getClassById(@PathVariable("id") Long id) throws ResponseStatusException {

		if (id != null && id > -1) {
			try {
				Clase kid = service.getClassById(id);
				return new ResponseEntity<Clase>(kid, new HttpHeaders(), HttpStatus.OK);
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La clase con id: " + id + "no se ha encontrado",
						e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

	}

	/**
	 * @param n
	 * @return EndPoint que nos devuelve un HttpStatus.OK y una nueva clase creado en
	 *         la BBDD
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en funcion de la
	 *                                 peticion)
	 */
    @ApiOperation(value = "Create a new class", notes = "Returns a new class")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,class can be created"),
      @ApiResponse(code = 404, message = "ERROR:class can't be created"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
	@PostMapping
	public ResponseEntity<Clase> createClass(@RequestBody Clase n) throws ResponseStatusException {
		if (n != null) {
			try {
				Clase kid = service.createClass(n);
				return new ResponseEntity<Clase>(kid, new HttpHeaders(), HttpStatus.OK);
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La clase no ha sido guardado correctamente", e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}
	}

	/**
	 * @param n
	 * @return EndPoint que nos devuelve un HttpStatus.OK y una clase actualizada en
	 *         la BBDD
	 * @throws ResponseStatusException (nos devolveria unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en funcion de la
	 *                                 peticion)
	 */
    @ApiOperation(value = "Update a class", notes = "Returns a updated class")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,class can be created"),
      @ApiResponse(code = 404, message = "ERROR:class can't be created"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
	@PutMapping
	public ResponseEntity<Clase> UpdateClass(@RequestBody Clase n) throws ResponseStatusException {
		if (n != null && n.getId() > 0) {
			try {
				Clase clase = service.Update(n);
				return new ResponseEntity<Clase>(clase, new HttpHeaders(), HttpStatus.OK);
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la clase no ha sido actualizado correctamente",
						e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

	}

	/**
	 * @param id
	 * @return EndPoint que nos devuelve un HttpStatus.OK y una clase eliminado en la
	 *         BBDD
	 * @throws ResponseStatusException (En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en funcion de la
	 *                                 peticion)
	 */
    @ApiOperation(value = "Delete a class", notes = "Delete a class")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,class can be deleted"),
      @ApiResponse(code = 404, message = "ERROR:class can't be deleted"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
	@DeleteMapping("/{id}")
	public HttpStatus deleteClass(@PathVariable("id") Long id) throws ResponseStatusException {
		if (id != null && id > -1) {
			try {
				service.deleteClassById(id);
				return HttpStatus.OK;
			} catch (ResponseStatusException e) {

				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La clase no ha sido eliminado correctamente",
						e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

	}
}
