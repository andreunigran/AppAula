package br.unigran.appaula.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.unigran.appaula.model.Pessoa;

public class PessoaImplBD implements PessoaDao{
    DBHelper db;
    @Override
    public void salvar(Pessoa p) {
        ContentValues dados = new ContentValues();
        dados.put("nome",p.getNome());
           db.getWritableDatabase().insertOrThrow("pessoa",null,dados);

    }
    @Override
    public void editar(Pessoa p) {
        ContentValues dados = new ContentValues();
        dados.put("nome",p.getNome());
        db.getWritableDatabase().
       update("pessoa",dados,"id=?",new String[]{p.getId()+""});

    }

    @Override
    public void remove(Pessoa p) {
    db.getWritableDatabase()
    .delete("pessoa","id=?",new String[]{p.getId()+""} );
    }

    public PessoaImplBD(Context context){
        this.db=new DBHelper(context);
    }

    @Override
    public List listagem() {
        List retorno = new ArrayList();
        String colunas[]= {"id","nome"};
        Cursor cursor = db.getReadableDatabase().
                query("pessoa", colunas, null,
                        null, null, null, "nome"
                );
       // cursor.moveToFirst();
        final int COLUMN_ID=0, COLUMN_NOME=1;
        while (cursor.moveToNext()){
            Pessoa p = new Pessoa();
            p.setId(cursor.getInt(COLUMN_ID));
            p.setNome(cursor.getString(COLUMN_NOME));
            retorno.add(p);
        }

        return retorno;
    }
}
