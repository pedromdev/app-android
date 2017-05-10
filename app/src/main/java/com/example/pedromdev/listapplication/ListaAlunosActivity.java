package com.example.pedromdev.listapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    private final String TAG = "CADASTRO_ALUNO";

    private final String ALUNOS_KEY = "LISTA";

    private EditText edtNome;

    private Button btnAdd;

    private ListView listNomes;

    private List<String> nomes;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        edtNome = (EditText) findViewById(R.id.edtNome);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        listNomes = (ListView) findViewById(R.id.listNomes);
        nomes = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes);
        listNomes.setAdapter(adapter);

        edtNome.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                        case KeyEvent.FLAG_EDITOR_ACTION:
                            btnAdd.callOnClick();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        edtNome.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE:
                    case EditorInfo.IME_ACTION_SEND:
                        btnAdd.callOnClick();
                        return true;
                }
                return false;
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNome.getText().toString();

                if (nome.isEmpty()) {
                    Toast.makeText(
                        v.getContext(),
                        "Informe um valor no campo de texto",
                        Toast.LENGTH_LONG
                    ).show();
                    return;
                }
                nomes.add(nome);
                edtNome.setText("");
                adapter.notifyDataSetChanged();
            }
        });

        listNomes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nome = parent.getItemAtPosition(position).toString();
                Toast.makeText(view.getContext(), nome, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList(ALUNOS_KEY, (ArrayList<String>) nomes);
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState(): " + nomes);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        nomes = savedInstanceState.getStringArrayList(ALUNOS_KEY);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes);
        listNomes.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Log.i(TAG, "onRestoreInstanceState(): " + nomes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_novo:
                Intent intent = new Intent(this, CadastroAlunoActivity.class);
                startActivity(intent);
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
