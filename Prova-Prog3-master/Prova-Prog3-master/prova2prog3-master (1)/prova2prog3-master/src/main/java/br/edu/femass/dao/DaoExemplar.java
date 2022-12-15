package br.edu.femass.dao;

import br.edu.femass.model.Exemplar;
import java.util.List;

public class DaoExemplar extends Dao<Exemplar> {
    public List<Exemplar> buscarTodos() {
        return em.createQuery("select e from Exemplar e order by e.id").getResultList();
    }
}