package com.example.pedromdev.listapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.pedromdev.listapplication.helper.CadastroAlunoHelper;
import com.example.pedromdev.listapplication.modelo.bean.Aluno;

public class CadastroAlunoActivity extends AppCompatActivity {

    private ImageView foto;

    private EditText nomeAluno,
        telefoneAluno,
        siteAluno,
        emailAluno,
        enderecoAluno;

    private SeekBar notaAluno;

    private Button btnSalvarAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        btnSalvarAluno = (Button) findViewById(R.id.btn_salvar_aluno);

        final CadastroAlunoHelper helper = new CadastroAlunoHelper(this);

        btnSalvarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno = helper.getAluno();
                finish();
            }
        });
    }
}
