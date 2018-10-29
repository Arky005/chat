package com.arky.chat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
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

        layout = (LinearLayout)findViewById(R.id.layoutscroll);
        scrow = (ScrollView)findViewById(R.id.scrow);
        enviar = (Button)findViewById(R.id.button);
        final EditText escrita = (EditText) findViewById(R.id.editText);
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



        new mensagem("Oi! Tudo bem? Como vai a familia?", false).criar().setCor(Color.RED);
        //layout.addView( new mensagem("Sim! Tudo bem graças a Deus!", 1, true).getView() );


    }


    public Context getContext(){
        return getApplicationContext();
    }

    //Classe para criar mensagens personalizadas
    class mensagem {

        private TextView texto;
        private int id, cor, tamanho;
        private boolean usuario;

        public mensagem(String texto, boolean usuario){


            this.texto=new TextView(getContext());
            this.texto.setTextIsSelectable(true); //pode copiar as mensagens?
            this.setTexto(texto);
            this.setCor(Color.BLACK);
            this.setId(idGlobal);
            this.setTamanho(17);
            this.setUsuario(usuario);
            this.texto.setHeight(80+(this.texto.getText().toString().length()*2));
            this.texto.setY(20);

            //muda o layout dos textos pra quebrar as linhas depois do 15 caracter
            // this.texto.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            //this.texto.setMaxEms(15); //quebra a linha depois do 15 caracter

            if(usuario){
                this.texto.setGravity(Gravity.RIGHT);
                this.texto.setWidth(size.x-(size.x/4));
            } else {
                this.texto.setGravity(Gravity.LEFT);
            }

            idGlobal++;
        }

        public mensagem(String texto, int cor, int tamanho, boolean usuario){
            this.texto=new TextView(getContext());

            this.texto.setTextIsSelectable(true); //pode copiar as mensagens?
            this.setTexto(texto);
            this.setCor(cor);
            this.setId(idGlobal);
            this.setTamanho(tamanho);
            this.setUsuario(usuario);

            //muda o layout dos textos pra quebrar as linhas depois do 15 caracter
            // this.texto.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            //this.texto.setMaxEms(15); //quebra a linha depois do 15 caracter

            if(usuario){
                this.texto.setGravity(Gravity.RIGHT);
                this.texto.setWidth(size.x-(size.x/4));
            } else {
                this.texto.setGravity(Gravity.LEFT);
                // this.texto.setBackgroundColor(Color.LTGRAY);
            }

            idGlobal++;
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

        public int getTamanho() {
            return tamanho;
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

        public void setTamanho(int tamanho) {
            this.texto.setTextSize(tamanho);
            this.tamanho = tamanho;
        }

        public void setTexto(String texto) {
            this.texto.setText(""+texto);
        }

    }




}
