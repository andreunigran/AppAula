package br.unigran.appaula;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
   private EditText nome;
    private EditText idade;
    private TextView msg;
    private Button botao;
    private ListView listagem;
    private List <Pessoa>dados;
    private PessoaDao dao;
    private Pessoa p ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapeamentoXML();
        consulta();
        vinculaAdapterALista();
        acoes();

    }
    private void mapeamentoXML(){
        setContentView(R.layout.activity_main);
        nome=findViewById(R.id.idNomePessoa);

        idade=findViewById(R.id.idIdadePessoa);

        botao=findViewById(R.id.idBtnOk);
        listagem=findViewById(R.id.idLista);//listagem

    }
    private void consulta(){
        if(dao==null)
            dao= new PessoaImplBD(this);
        dados=dao.listagem();

    }

    private void vinculaAdapterALista() {

        listagem.setAdapter(
                new ArrayAdapter(getApplicationContext(),
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                        dados)// cria o adapter
        );//seta o adapter para listagem
    }

    private void acoes() {
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //acao do botão
                //cria pessoa
                if(p==null)
                    p = new Pessoa();
                p.setNome(nome.getText().toString());
                p.setIdade(Integer.parseInt(idade.getText().toString()));
                if(p.getId()==null)
                    dao.salvar(p);//salva
                else dao.editar(p);
                limpaCampos();
                atualizaDados();
            }
        });

        listagem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int indice, long l) {
             new  AlertDialog.Builder(listagem.getContext())
                     .setTitle("Realmente deseja excluir")
                     .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {

                         }
                     })
                     .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {

                         }
                     }).
                     setNeutralButton("Sim", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                             dao.remove(dados.get(indice));
                             atualizaDados();
                         }
                     })
                     .create().show();

                return false;
            }
        });
        listagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               p=dados.get(i);
                nome.setText(p.getNome());
                idade.setText(p.getIdade()+"");
            }
        });
    }

    private void atualizaDados() {
        dados.clear();
        dados.addAll(dao.listagem());
        ((ArrayAdapter) listagem.getAdapter()
        ).notifyDataSetChanged();
    }

    private void limpaCampos() {
        nome.setText("");
        idade.setText("");
        p=null;
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