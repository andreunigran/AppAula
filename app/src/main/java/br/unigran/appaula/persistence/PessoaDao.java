package br.unigran.appaula.persistence;

import java.util.List;

import br.unigran.appaula.model.Pessoa;

public interface PessoaDao {
    public void salvar(Pessoa p);

    public void editar(Pessoa p);
    public void remove(Pessoa p);
    public List listagem();
}
