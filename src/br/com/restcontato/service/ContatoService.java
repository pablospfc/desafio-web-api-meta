package br.com.restcontato.service;

import java.util.List;

import br.com.restcontato.dao.ContatoDAO;
import br.com.restcontato.entidade.Contato;
import br.com.restcontato.exceptions.ContatoException;

public class ContatoService {

	ContatoDAO dao = new ContatoDAO();
	
	public Contato create(Contato contato) throws ContatoException {
		try {
			return dao.create(contato);
		}  catch (Exception ex) {
			throw new ContatoException(String.format("Ocorreu um problema ao cadastrar contato", ex.getMessage()));
		}
	}
	
	public List<Contato> getAll(Integer page, Integer size) throws ContatoException {
		try {
			return dao.getAll(page, size);
		}  catch (Exception ex) {
			throw new ContatoException(String.format("Ocorreu um problema ao listar contatos", ex.getMessage()));
		}
	}
	
	public Contato getById(Integer id) throws ContatoException {
		try {
			return dao.getById(id);
		}  catch (Exception ex) {
			throw new ContatoException(String.format("Ocorreu um problema ao buscar contato", ex.getMessage()));
		}
	}
	
	public void remove(Integer id) throws ContatoException {
		try {
			dao.remove(id);
		}  catch (Exception ex) {
			throw new ContatoException(String.format("Ocorreu um problema ao remover contato", ex.getMessage()));
		}
	}
	
	public Contato update(Contato contato, Integer id) throws ContatoException {
		try {
			return dao.update(contato, id);
		} catch (Exception ex) {
			throw new ContatoException(String.format("Ocorreu um problema ao atualizar contato: %s ", ex.getMessage()));
		}
	}
	
	
}
