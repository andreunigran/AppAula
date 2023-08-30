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
    public void editar(Pessoa p) {
        if(lista.contains(p)){
            lista.add(lista.indexOf(p),p);
        }
    }

    @Override
    public void remove(Pessoa p) {
        lista.remove(p);
    }

    @Override
    public List listagem(){
        return  lista;
    }
}
