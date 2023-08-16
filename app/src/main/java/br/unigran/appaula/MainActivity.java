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

public class MainActivity extends AppCompatActivity {

    EditText nome;
    EditText idade;
    TextView msg;

    Button botao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nome=findViewById(R.id.idNomePessoa);

        idade=findViewById(R.id.idIdadePessoa);

        botao=findViewById(R.id.idBtnOk);


        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //acao do botão
                Toast.makeText(getApplicationContext(),"Ola mundo",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void cancelar(View view){
        //permite vinculo no xml
        AlertDialog.Builder acao = new AlertDialog.Builder(this);
        acao.setTitle("texto");
        acao.setItems(new CharSequence[]{"Ok","Cancelar"},new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        acao.create().show();
    }
}