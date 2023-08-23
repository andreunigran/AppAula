package br.unigran.appaula.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.unigran.appaula.model.Pessoa;

public class PessoaImplBD implements PessoaDao{
    DBHelper db;
    SQLiteDatabase le;
    @Override
    public void salvar(Pessoa p) {
        ContentValues dados = new ContentValues();
        dados.put("nome",p.getNome());
        db.getWritableDatabase().insertOrThrow("pessoa",null,dados);
    }
    public PessoaImplBD(Context context){
        this.db=new DBHelper(context);
    }

    @Override
    public List listagem() {
        return null;
    }
}
