package com.arky.chat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.InputType;
import android.util.Size;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

    LinearLayout layout;
    ScrollView scrow;
    Button enviar;
    Point size;
    int idGlobal=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        Random rand = new Random();

        layout = findViewById(R.id.layoutscroll);
        scrow = findViewById(R.id.scrow);
        enviar = findViewById(R.id.button);
        final EditText escrita = findViewById(R.id.editText);
        String[] bomdia ={"Bom dia", "Boom diaa", "bomm diaa"};
        String[] apelido ={"amigão", "brother", "irmão", "patrão", "xuxu"};
        String[] boanoite={"Boa noite", "Boa noitee", "Booa noiteee"};
        int  n = rand.nextInt(boanoite.length);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!escrita.getText().toString().equals("")) {
                    new mensagem(escrita.getText().toString(), true).criar();
                    escrita.setText("");
                    scrow.scrollTo(0, scrow.getBottom());
                }
            }
        });

        new mensagem("Oi! Tudo bem? Como vai a familia?", false).criar();

    }

    //Classe para criar mensagens personalizadas
    class mensagem {

        private TextView texto;
        private int id, cor, tamanhoFonte;
        private boolean usuario;

        public mensagem(String texto, boolean usuario){


            this.texto=new TextView(getApplicationContext());
            this.texto.setTextIsSelectable(true); //pode copiar as mensagens?
            this.setTexto(texto);
            this.setCor(Color.BLACK);
            this.setId(idGlobal);
            this.setTamanhoFonte(17);
            this.setUsuario(usuario);
            this.texto.setHeight( this.getTamanhoFonte()*3*numeroLinhas() );


            if(usuario){
                this.texto.setGravity(Gravity.RIGHT);
            } else {
                this.texto.setGravity(Gravity.LEFT);
            }

            idGlobal++;
        }

        public mensagem(String texto, int cor, int tamanho, boolean usuario){
            this.texto=new TextView(getApplicationContext());

            this.texto.setTextIsSelectable(true); //pode copiar as mensagens?
            this.setTexto(texto);
            this.setCor(cor);
            this.setId(idGlobal);
            this.setTamanhoFonte(tamanho);
            this.setUsuario(usuario);


            if(usuario){
                this.texto.setGravity(Gravity.RIGHT);
            } else {
                this.texto.setGravity(Gravity.LEFT);
            }

            idGlobal++;
        }

        private int numeroLinhas(){
            int tamanho=this.texto.getText().length();
            int resp=(tamanho/25)+1;//25 caracteres em uma linha
            return resp;
        }

        public mensagem criar(){
            layout.addView(texto);
            return this;
        }
        public TextView getView() {

            return texto;
        }

        public String getTexto(){

            return ""+texto.getText();
        }

        public boolean isUsuario() {

            return usuario;
        }

        public int getCor() {

            return cor;
        }

        public int getId() {

            return id;
        }

        public int getTamanhoFonte() {
            return tamanhoFonte;
        }

        public void setCor(int cor) {
            this.texto.setTextColor(cor);
            this.cor = cor;
        }

        public void setUsuario(boolean usuario) {

            this.usuario = usuario;
        }

        public void setId(int id) {

            this.id = id;
        }

        public void setTamanhoFonte(int tamanho) {
            this.texto.setTextSize(tamanho);
            this.tamanhoFonte = tamanho;
        }

        public void setTexto(String texto) {
            this.texto.setText(""+texto);
        }

    }




}
