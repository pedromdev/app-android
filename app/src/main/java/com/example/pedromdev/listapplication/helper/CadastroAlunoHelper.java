package com.example.pedromdev.listapplication.helper;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.pedromdev.listapplication.CadastroAlunoActivity;
import com.example.pedromdev.listapplication.R;
import com.example.pedromdev.listapplication.modelo.bean.Aluno;

/**
 * Created by pedromdev on 06/04/17.
 */

public class CadastroAlunoHelper {

    private ImageView foto;

    private EditText nome,
        telefone,
        site,
        email,
        endereco;

    private SeekBar nota;

    private Aluno aluno;

    public CadastroAlunoHelper(CadastroAlunoActivity activity) {
        foto = (ImageView) activity.findViewById(R.id.foto);
        nome = (EditText) activity.findViewById(R.id.nome_aluno);
        telefone = (EditText) activity.findViewById(R.id.telefone_aluno);
        site = (EditText) activity.findViewById(R.id.site_aluno);
        email = (EditText) activity.findViewById(R.id.email_aluno);
        endereco = (EditText) activity.findViewById(R.id.endereco_aluno);
        nota = (SeekBar) activity.findViewById(R.id.nota_aluno);

        aluno = new Aluno();
    }

    public Aluno getAluno() {
        aluno.setNome(nome.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setEmail(email.getText().toString());
//        aluno.setFoto(foto.toString());
        aluno.setNota(Double.valueOf(nota.getProgress()));
        return aluno;
    }
}
