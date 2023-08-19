package br.unigran.appaula.persistence;

import java.util.ArrayList;
import java.util.List;

import br.unigran.appaula.model.Pessoa;

public class PessoaImpl  implements PessoaDao{

    private List lista;
    public PessoaImpl(){
        lista = new ArrayList();
    }
    @Override
    public void salvar(Pessoa p){
        lista.add(p);
    }
    @Override
    public List listagem(){
        return  lista;
    }
}
