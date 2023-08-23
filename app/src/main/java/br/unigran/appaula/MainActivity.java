package br.unigran.appaula;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.unigran.appaula.model.Pessoa;
import br.unigran.appaula.persistence.DBHelper;
import br.unigran.appaula.persistence.PessoaDao;
import br.unigran.appaula.persistence.PessoaImpl;
import br.unigran.appaula.persistence.PessoaImplBD;

public class MainActivity extends AppCompatActivity {
    EditText nome;
    EditText idade;
    TextView msg;
    Button botao;

    ListView listagem;
    ArrayAdapter adapter;

    PessoaDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao= new PessoaImplBD(this);

        setContentView(R.layout.activity_main);
        nome=findViewById(R.id.idNomePessoa);

        idade=findViewById(R.id.idIdadePessoa);

        botao=findViewById(R.id.idBtnOk);

        listagem=findViewById(R.id.idLista);//listagem

        adapter= new ArrayAdapter(getApplicationContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                dao.listagem());// cria o adapter
        listagem.setAdapter(adapter);//seta o adapter para listagem
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //acao do botão
                Pessoa p = new Pessoa();
                p.setNome(nome.getText().toString());
                p.setIdade(Integer.parseInt(idade.getText().toString()));


                dao.salvar(p);
                adapter.notifyDataSetChanged();
            }
        });
    }
    public void cancelar(View view){
        //permite vinculo no xml
        AlertDialog.Builder acao = new AlertDialog.Builder(this);
        acao.setTitle("Você quer sair");
        acao.setItems(new CharSequence[]{"Sair"},new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
            }
        });
        acao.create().show();
    }
}