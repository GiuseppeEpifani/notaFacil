package com.example.sliderejemlo;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TableRow;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {
    int progress = -1;
    int resultado = 0;
    int primarValor;
    int longitud;
    int porcentajeAntiguo;
    int puerta=0;


    SeekBar seek;
    EditText nota1, nota2, nota3, nota4, nota5, nota6, nota7, nota8, nota9, nota10, txtprogreso, result, totalPorcentaje;
    EditText porcentaje1, porcentaje2, porcentaje3, porcentaje4, porcentaje5, porcentaje6, porcentaje7, porcentaje8, porcentaje9, porcentaje10;
    ImageButton btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10;
    TableRow tabla1,tabla2,tabla3,tabla4,tabla5,tabla6,tabla7,tabla8,tabla9,tabla10;
    ScrollView barra;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//Creamos los componentes
        seek = (SeekBar) findViewById(R.id.seekBar);
        nota1 = (EditText) findViewById(R.id.txt1);
        nota2 = (EditText) findViewById(R.id.txt2);
        nota3 = (EditText) findViewById(R.id.txt3);
        nota4 = (EditText) findViewById(R.id.txt4);
        nota5 = (EditText) findViewById(R.id.txt5);
        nota6 = (EditText) findViewById(R.id.txt6);
        nota7 = (EditText) findViewById(R.id.txt7);
        nota8 = (EditText) findViewById(R.id.txt8);
        nota9 = (EditText) findViewById(R.id.txt9);
        nota10 = (EditText) findViewById(R.id.txt10);
        porcentaje1 = (EditText) findViewById(R.id.porcentaje1);
        porcentaje2 = (EditText) findViewById(R.id.porcentaje2);
        porcentaje3 = (EditText) findViewById(R.id.porcentaje3);
        porcentaje4 = (EditText) findViewById(R.id.porcentaje4);
        porcentaje5 = (EditText) findViewById(R.id.porcentaje5);
        porcentaje6 = (EditText) findViewById(R.id.porcentaje6);
        porcentaje7 = (EditText) findViewById(R.id.porcentaje7);
        porcentaje8 = (EditText) findViewById(R.id.porcentaje8);
        porcentaje9 = (EditText) findViewById(R.id.porcentaje9);
        porcentaje10 = (EditText) findViewById(R.id.porcentaje10);
        result = (EditText) findViewById(R.id.txtTotalPromedio);
        totalPorcentaje = (EditText) findViewById(R.id.txtTotalPorcentaje);
        barra= (ScrollView)findViewById(R.id.barra);
        txtprogreso = (EditText) findViewById(R.id.txtProgreso);

        //Creamos los botones para llamarlos en la funcion eliminarCeldas
        btn2 = (ImageButton) findViewById(R.id.btn2);
        btn3 = (ImageButton) findViewById(R.id.btn3);
        btn4 = (ImageButton) findViewById(R.id.btn4);
        btn5 = (ImageButton) findViewById(R.id.btn5);
        btn6 = (ImageButton) findViewById(R.id.btn6);
        btn7 = (ImageButton) findViewById(R.id.btn7);
        btn8 = (ImageButton) findViewById(R.id.btn8);
        btn9 = (ImageButton) findViewById(R.id.btn9);
        btn10 = (ImageButton) findViewById(R.id.btn10);

        //Creamos las tablas

        tabla2= (TableRow)findViewById(R.id.tabla2);
        tabla3= (TableRow)findViewById(R.id.tabla3);
        tabla4= (TableRow)findViewById(R.id.tabla4);
        tabla5= (TableRow)findViewById(R.id.tabla5);
        tabla6= (TableRow)findViewById(R.id.tabla6);
        tabla7= (TableRow)findViewById(R.id.tabla7);
        tabla8= (TableRow)findViewById(R.id.tabla8);
        tabla9= (TableRow)findViewById(R.id.tabla9);
        tabla10= (TableRow)findViewById(R.id.tabla10);






//Ponemos invisibles los componenetes
 tabla2.setVisibility(View.INVISIBLE);
        tabla3.setVisibility(View.INVISIBLE);
        tabla4.setVisibility(View.INVISIBLE);
        tabla5.setVisibility(View.INVISIBLE);
        tabla6.setVisibility(View.INVISIBLE);
        tabla7.setVisibility(View.INVISIBLE);
        tabla8.setVisibility(View.INVISIBLE);
        tabla9.setVisibility(View.INVISIBLE);
        tabla10.setVisibility(View.INVISIBLE);


//limitar rango de numeros




    }


    protected void onStart() {
        super.onStart();
        //Creamos el arreglo
        final TableRow[] tablas = {tabla1,tabla2,tabla3,tabla4,tabla5,tabla6,tabla7,tabla8,tabla9,tabla10};
        final EditText[] notas = {nota1, nota2, nota3, nota4, nota5, nota6, nota7, nota8, nota9, nota10};
        final EditText[] porcentajes = {porcentaje1, porcentaje2, porcentaje3, porcentaje4, porcentaje5, porcentaje6, porcentaje7, porcentaje8, porcentaje9, porcentaje10};

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (progress == -1) {

                        for (int x = 1; x <= i; x++) {
                            TableRow tabla = tablas[x];
                            tabla.setVisibility(View.VISIBLE);
                        }

                } else if (progress < i) {
                    for (int x = 1; x <= i; x++) {
                        TableRow tabla = tablas[x];
                        tabla.setVisibility(View.VISIBLE);
                    }
                } else if (progress > i) {
                    for (int a = i + 1; a <= progress; a++) {
                        TableRow tabla = tablas[a];
                        EditText nota = notas[a];
                        EditText porcentaje = porcentajes[a];
                        porcentaje.setText(null);
                        nota.setText(null);
                        tabla.setVisibility(View.INVISIBLE);


                    }
                }
                txtprogreso.setText(i + 1 + "");
                progress = i;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //Calculo automatico de Notas
        nota1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (nota1.length() > 2 || nota1.length() == 0) {
                        result.setText(resultado());
                        Aprobacion();
                        if (nota1.length() > 2)
                            if (porcentaje1.length()==0){
                            porcentaje1.requestFocus();
                        }else{
                            if (Integer.parseInt(txtprogreso.getText().toString())!=1){
                                nota2.requestFocus();
                            }
                        }
                }




            }
            @Override
            public void afterTextChanged(Editable editable) {
                DetectarEliminacionDeTeclado(nota1);
                    LimitarIngresoDePunto(nota1);

            }
        });
        nota2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (nota2.length()>2 || nota2.length()==0) {
                    result.setText(resultado());
                    Aprobacion();
                    if (nota2.length() > 2)
                        if (porcentaje2.length()==0){
                        porcentaje2.requestFocus();
                    }else{
                        if (Integer.parseInt(txtprogreso.getText().toString())!=2){
                            nota3.requestFocus();
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                LimitarIngresoDePunto(nota2);
                DetectarEliminacionDeTeclado(nota2);

            }
        });
        nota3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (nota3.length() > 2 || nota3.length() == 0) {
                    result.setText(resultado());
                    Aprobacion();
                    if (nota3.length() > 2)
                    if (porcentaje3.length() == 0) {
                        porcentaje3.requestFocus();
                    } else {
                        if (Integer.parseInt(txtprogreso.getText().toString())!=3){
                            nota4.requestFocus();
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                LimitarIngresoDePunto(nota3);
                DetectarEliminacionDeTeclado(nota3);

            }
        });
        nota4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (nota4.length() > 2 || nota4.length() == 0) {
                    result.setText(resultado());
                    Aprobacion();
                    if (nota4.length() > 2)
                        if (porcentaje4.length() == 0) {
                        porcentaje4.requestFocus();
                    } else {
                        if (Integer.parseInt(txtprogreso.getText().toString())!=4){
                            nota5.requestFocus();
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                LimitarIngresoDePunto(nota4);
                DetectarEliminacionDeTeclado(nota4);

            }
        });
        nota5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (nota5.length() > 2 || nota5.length() == 0) {
                    result.setText(resultado());
                    Aprobacion();
                    if (nota5.length() > 2)
                        if (porcentaje5.length() == 0) {
                        porcentaje5.requestFocus();
                    } else {
                        if (Integer.parseInt(txtprogreso.getText().toString())!=5){
                            nota6.requestFocus();
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                LimitarIngresoDePunto(nota5);
                DetectarEliminacionDeTeclado(nota5);

            }
        });
        nota6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (nota6.length() > 2 || nota6.length() == 0) {
                    result.setText(resultado());
                    Aprobacion();
                    if (nota6.length() > 2)
                        if (porcentaje6.length() == 0) {
                        porcentaje6.requestFocus();
                    } else {
                        if (Integer.parseInt(txtprogreso.getText().toString())!=6){
                            nota7.requestFocus();
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                LimitarIngresoDePunto(nota6);
                DetectarEliminacionDeTeclado(nota6);

            }
        });
        nota7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (nota7.length() > 2 || nota7.length() == 0) {
                    result.setText(resultado());
                    Aprobacion();
                    if (nota7.length() > 2)
                        if (porcentaje7.length() == 0) {
                        porcentaje7.requestFocus();
                    } else {
                        if (Integer.parseInt(txtprogreso.getText().toString())!=7){
                            nota8.requestFocus();
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                LimitarIngresoDePunto(nota7);
                DetectarEliminacionDeTeclado(nota7);

            }
        });
        nota8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (nota8.length() > 2 || nota8.length() == 0) {
                    result.setText(resultado());
                    Aprobacion();
                    if (nota8.length() > 2)
                        if (porcentaje8.length() == 0) {
                        porcentaje8.requestFocus();
                    } else {
                        if (Integer.parseInt(txtprogreso.getText().toString())!=8){
                            nota9.requestFocus();
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                LimitarIngresoDePunto(nota8);
                DetectarEliminacionDeTeclado(nota8);

            }
        });
        nota9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (nota9.length() > 2 || nota9.length() == 0) {
                    result.setText(resultado());
                    Aprobacion();
                    if (nota9.length() > 2)
                        if (porcentaje9.length() == 0) {
                        porcentaje9.requestFocus();
                    } else {
                        if (Integer.parseInt(txtprogreso.getText().toString())!=9){
                            nota10.requestFocus();
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                LimitarIngresoDePunto(nota9);
                DetectarEliminacionDeTeclado(nota9);

            }
        });
        nota10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (nota10.length() > 2 || nota10.length() == 0) {
                    result.setText(resultado());
                    Aprobacion();
                    if (nota10.length() > 2)
                        if (porcentaje10.length() == 0) {
                        porcentaje10.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                LimitarIngresoDePunto(nota10);
                DetectarEliminacionDeTeclado(nota10);

            }
        });


        //Calculo automatico de porcentajes
        porcentaje1.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                longitud = porcentaje1.length();
                porcentajeAntiguo= Integer.parseInt(porcentaje1.getText().toString());
            } catch (Exception e) {
            }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                        result.setText(resultado());
                        ValidarPorcentaje(porcentaje1);
                        Aprobacion();
                   if (porcentaje1.length()>1)
                    if (nota1.length()==0){
                        nota1.requestFocus();
                    }else {
                        if (Integer.parseInt(txtprogreso.getText().toString()) != 1) {
                            if (nota2.length() == 0) {
                                nota2.requestFocus();
                            } else {
                                porcentaje2.requestFocus();
                            }
                        }
                    }


                } catch (Exception e) {
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        porcentaje2.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    longitud = porcentaje2.length();
                    porcentajeAntiguo= Integer.parseInt(porcentaje2.getText().toString());
                } catch (Exception e) {
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    result.setText(resultado());
                    ValidarPorcentaje(porcentaje2);
                    Aprobacion();
                    if (porcentaje2.length()>1)
                    if (nota2.length()==0){
                        nota2.requestFocus();
                    }else {
                        if (Integer.parseInt(txtprogreso.getText().toString()) != 2) {
                            if (nota3.length() == 0) {
                                nota3.requestFocus();
                            } else {
                                porcentaje3.requestFocus();
                            }
                        }
                    }

                } catch (Exception e) {
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        porcentaje3.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    longitud = porcentaje3.length();
                    porcentajeAntiguo= Integer.parseInt(porcentaje3.getText().toString());
                } catch (Exception e) {
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    result.setText(resultado());
                    ValidarPorcentaje(porcentaje3);
                    Aprobacion();
                    if (porcentaje3.length()>1)
                    if (nota3.length()==0){
                        nota3.requestFocus();
                    }else {
                        if (Integer.parseInt(txtprogreso.getText().toString()) != 3) {
                            if (nota4.length() == 0) {
                                nota4.requestFocus();
                            } else {
                                porcentaje4.requestFocus();
                            }
                        }
                    }

                } catch (Exception e) {
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        porcentaje4.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    longitud = porcentaje4.length();
                    porcentajeAntiguo= Integer.parseInt(porcentaje4.getText().toString());
                } catch (Exception e) {
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    result.setText(resultado());
                    ValidarPorcentaje(porcentaje4);
                    Aprobacion();
                    if (porcentaje4.length()>1)
                    if (nota4.length()==0){
                        nota4.requestFocus();
                    }else {
                        if (Integer.parseInt(txtprogreso.getText().toString()) != 4) {
                            if (nota5.length() == 0) {
                                nota5.requestFocus();
                            } else {
                                porcentaje5.requestFocus();
                            }
                        }
                    }


                } catch (Exception e) {
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        porcentaje5.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    longitud = porcentaje5.length();
                    porcentajeAntiguo= Integer.parseInt(porcentaje5.getText().toString());
                } catch (Exception e) {
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    result.setText(resultado());
                    ValidarPorcentaje(porcentaje5);
                    Aprobacion();
                    if (porcentaje4.length()>1)
                    if (nota5.length()==0){
                        nota5.requestFocus();
                    }else {
                        if (Integer.parseInt(txtprogreso.getText().toString()) != 5) {
                            if (nota6.length() == 0) {
                                nota6.requestFocus();
                            } else {
                                porcentaje6.requestFocus();
                            }
                        }
                    }

                } catch (Exception e) {
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        porcentaje6.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    longitud = porcentaje6.length();
                    porcentajeAntiguo= Integer.parseInt(porcentaje6.getText().toString());
                } catch (Exception e) {
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    result.setText(resultado());
                    ValidarPorcentaje(porcentaje6);
                    Aprobacion();
                    if (porcentaje6.length()>1)
                    if (nota6.length()==0){
                        nota6.requestFocus();
                    }else {
                        if (Integer.parseInt(txtprogreso.getText().toString()) != 6) {
                            if (nota7.length() == 0) {
                                nota7.requestFocus();
                            } else {
                                porcentaje7.requestFocus();
                            }
                        }
                    }

                } catch (Exception e) {
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        porcentaje7.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    longitud = porcentaje7.length();
                    porcentajeAntiguo= Integer.parseInt(porcentaje7.getText().toString());
                } catch (Exception e) {
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    result.setText(resultado());
                    ValidarPorcentaje(porcentaje7);
                    Aprobacion();
                    if (porcentaje7.length()>1)
                    if (nota7.length()==0){
                        nota7.requestFocus();
                    }else {
                        if (Integer.parseInt(txtprogreso.getText().toString()) != 7) {
                            if (nota8.length() == 0) {
                                nota8.requestFocus();
                            } else {
                                porcentaje8.requestFocus();
                            }
                        }
                    }

                } catch (Exception e) {
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        porcentaje8.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    longitud = porcentaje8.length();
                    porcentajeAntiguo= Integer.parseInt(porcentaje8.getText().toString());
                } catch (Exception e) {
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    result.setText(resultado());
                    ValidarPorcentaje(porcentaje8);
                    Aprobacion();
                    if (porcentaje8.length()>1)
                    if (nota8.length()==0){
                        nota8.requestFocus();
                    }else {
                        if (Integer.parseInt(txtprogreso.getText().toString()) != 8) {
                            if (nota9.length() == 0) {
                                nota9.requestFocus();
                            } else {
                                porcentaje9.requestFocus();
                            }
                        }
                    }

                } catch (Exception e) {
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        porcentaje9.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    longitud = porcentaje9.length();
                    porcentajeAntiguo= Integer.parseInt(porcentaje9.getText().toString());
                } catch (Exception e) {
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    result.setText(resultado());
                    ValidarPorcentaje(porcentaje9);
                    Aprobacion();
                    if (porcentaje9.length()>1)
                    if (nota9.length()==0){
                        nota9.requestFocus();
                    }else {
                        if (Integer.parseInt(txtprogreso.getText().toString()) != 9) {
                            if (nota10.length() == 0) {
                                nota10.requestFocus();
                            } else {
                                porcentaje10.requestFocus();
                            }
                        }
                    }

                } catch (Exception e) {
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        porcentaje10.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    longitud = porcentaje10.length();
                    porcentajeAntiguo= Integer.parseInt(porcentaje10.getText().toString());
                } catch (Exception e) {
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    result.setText(resultado());
                    ValidarPorcentaje(porcentaje10);
                    Aprobacion();
                    if (porcentaje10.length()>1)
                        if (nota9.length()==0) {
                            nota10.requestFocus();
                        }
                } catch (Exception e) {
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    //Creamos los eventos de los botones para eliminar las celdas
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    seek.setProgress(0);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seek.setProgress(1);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    seek.setProgress(2);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    seek.setProgress(3);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    seek.setProgress(4);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    seek.setProgress(5);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    seek.setProgress(6);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    seek.setProgress(7);
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seek.setProgress(8);
            }
        });

        barra.setOnTouchListener( new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (Integer.parseInt(txtprogreso.getText().toString())>5){
                    return false;


                }else{
                    return true;
                }
            }
        });
        seek.setProgress(3);


    }


    public void Borrar(View view) {
        final EditText[] notas = {nota1, nota2, nota3, nota4, nota5, nota6, nota7, nota8, nota9, nota10};
        final EditText[] porcentajes = {porcentaje1, porcentaje2, porcentaje3, porcentaje4, porcentaje5, porcentaje6, porcentaje7, porcentaje8, porcentaje9, porcentaje10};
        for (int a = 0; a <= 9; a++) {
            EditText nota = notas[a];
            EditText porcentaje = porcentajes[a];
            porcentaje.setText(null);
            nota.setText(null);
            nota1.requestFocus();

        }
    }

    private String resultado() {
        Double n1, n2, n3, n4, n5, n6, n7, n8, n9, n10;
        int p1, p2, p3, p4, p5, p6, p7, p8, p9, p10;
        if (nota1.getText().toString() != "" && nota1.getText().length() > 0) {
            n1 = Double.parseDouble(nota1.getText().toString());
        } else {
            n1 = 0.0;
        }
        if (porcentaje1.getText().toString() != "" && porcentaje1.getText().length() > 0) {
            p1 = Integer.parseInt(porcentaje1.getText().toString());
        } else {
            p1 = 0;
        }
        if (nota2.getText().toString() != "" && nota2.getText().length() > 0) {
            n2 = Double.parseDouble(nota2.getText().toString());
        } else {
            n2 = 0.0;
        }
        if (porcentaje2.getText().toString() != "" && porcentaje2.getText().length() > 0) {
            p2 = Integer.parseInt(porcentaje2.getText().toString());
        } else {
            p2 = 0;
        }
        if (nota3.getText().toString() != "" && nota3.getText().length() > 0) {
            n3 = Double.parseDouble(nota3.getText().toString());
        } else {
            n3 = 0.0;
        }
        if (porcentaje3.getText().toString() != "" && porcentaje3.getText().length() > 0) {
            p3 = Integer.parseInt(porcentaje3.getText().toString());
        } else {
            p3 = 0;
        }
        if (nota4.getText().toString() != "" && nota4.getText().length() > 0) {
            n4 = Double.parseDouble(nota4.getText().toString());
        } else {
            n4 = 0.0;
        }
        if (porcentaje4.getText().toString() != "" && porcentaje4.getText().length() > 0) {
            p4 = Integer.parseInt(porcentaje4.getText().toString());
        } else {
            p4 = 0;
        }
        if (nota5.getText().toString() != "" && nota5.getText().length() > 0) {
            n5 = Double.parseDouble(nota5.getText().toString());
        } else {
            n5 = 0.0;
        }
        if (porcentaje5.getText().toString() != "" && porcentaje5.getText().length() > 0) {
            p5 = Integer.parseInt(porcentaje5.getText().toString());
        } else {
            p5 = 0;
        }
        if (nota6.getText().toString() != "" && nota6.getText().length() > 0) {
            n6 = Double.parseDouble(nota6.getText().toString());
        } else {
            n6 = 0.0;
        }
        if (porcentaje6.getText().toString() != "" && porcentaje6.getText().length() > 0) {
            p6 = Integer.parseInt(porcentaje6.getText().toString());
        } else {
            p6 = 0;
        }
        if (nota7.getText().toString() != "" && nota7.getText().length() > 0) {
            n7 = Double.parseDouble(nota7.getText().toString());
        } else {
            n7 = 0.0;
        }
        if (porcentaje7.getText().toString() != "" && porcentaje7.getText().length() > 0) {
            p7 = Integer.parseInt(porcentaje7.getText().toString());
        } else {
            p7 = 0;
        }
        if (nota8.getText().toString() != "" && nota8.getText().length() > 0) {
            n8 = Double.parseDouble(nota8.getText().toString());
        } else {
            n8 = 0.0;
        }
        if (porcentaje8.getText().toString() != "" && porcentaje8.getText().length() > 0) {
            p8 = Integer.parseInt(porcentaje8.getText().toString());
        } else {
            p8 = 0;
        }
        if (nota9.getText().toString() != "" && nota9.getText().length() > 0) {
            n9 = Double.parseDouble(nota9.getText().toString());
        } else {
            n9 = 0.0;
        }
        if (porcentaje9.getText().toString() != "" && porcentaje9.getText().length() > 0) {
            p9 = Integer.parseInt(porcentaje9.getText().toString());
        } else {
            p9 = 0;
        }
        if (nota10.getText().toString() != "" && nota10.getText().length() > 0) {
            n10 = Double.parseDouble(nota10.getText().toString());
        } else {
            n10 = 0.0;
        }
        if (porcentaje10.getText().toString() != "" && porcentaje10.getText().length() > 0) {
            p10 = Integer.parseInt(porcentaje10.getText().toString());
        } else {
            p10 = 0;
        }

        Double promedio =((n1 * ((float) p1 / 100)) +  (n2 * ((float) p2 / 100))
                +  (n3 * ((float) p3 / 100)) +  (n4 * ((float) p4 / 100))
                +  (n5 * ((float) p5 / 100)) +  (n6 * ((float) p6 / 100))
                +  (n7 * ((float) p7 / 100)) +  (n8 * ((float) p8 / 100))
                +  (n9 * ((float) p9 / 100)) + (n10 * ((float) p10 / 100)));

        return Double.toString((double)Math.round( promedio * 100d) / 100);

    }

    public void ValidarPorcentaje(EditText porce) {
        ValidarEliminacion(porce);
        if (porce.length() == 1) {
                primarValor = Integer.parseInt(porce.getText().toString());
            }
            if (porce.length() == 2) {
                totalPorcentaje.setText(String.valueOf(Integer.parseInt(totalPorcentaje.getText().toString()) - primarValor));

            }
            resultado = Integer.parseInt(totalPorcentaje.getText().toString()) + Integer.parseInt(porce.getText().toString());
            if (resultado <= 100) {
                totalPorcentaje.setText(String.valueOf(resultado));
            } else {
                //esta variable queda recargada con este valor para que en la funcion ValidarEliminacion no entra a realizar el proceso de resta
                puerta=1;
                porce.setText(null);
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast_3,
                        (ViewGroup)findViewById(R.id.layout_base3));
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL,0,0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();

            }

    }

    public void ValidarEliminacion(EditText porce) {
            if (porce.length() < longitud) {
                if (puerta==0){
                    int resta= Integer.parseInt(totalPorcentaje.getText().toString()) - porcentajeAntiguo;
                    totalPorcentaje.setText(String.valueOf(resta));
                }
                puerta=0;
            }
    }

    public void Aprobacion(){
        final EditText[] notas = {nota1, nota2, nota3, nota4, nota5, nota6, nota7, nota8, nota9, nota10};
        int progreso = Integer.parseInt(txtprogreso.getText().toString());
        EditText nota= notas[progreso-1];
            if (!nota.getText().toString().equals("")) {
                if (Integer.parseInt(totalPorcentaje.getText().toString()) == 100) {
                    if (nota.length()>2) {
                        if (Double.parseDouble(result.getText().toString()) >= 3.95) {
                            LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.custom_toast_1,
                                    (ViewGroup)findViewById(R.id.layout_base1));
                            Toast toast = new Toast(getApplicationContext());
                            toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL,0,0);
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(layout);
                            toast.show();
                        } else {
                            LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.custom_toast_2,
                                    (ViewGroup)findViewById(R.id.layout_base2));
                            Toast toast = new Toast(getApplicationContext());
                            toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL,0,0);
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(layout);
                            toast.show();
                        }
                    }
                }
            }

    }



    //Detecta si se esta eliminando el caracter de nota
    public void DetectarEliminacionDeTeclado(final EditText nota){
        nota.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if(keyCode == KeyEvent.KEYCODE_DEL) {
                    nota.setText(null);
                }
                return false;
            }
        });
    }

    public void LimitarIngresoDePunto(EditText nota) {
        nota.setSelection(nota.length());
            if (nota.length() == 1) {
                String valor = nota.getText().toString();
                nota.setText(valor + ".");
            }
    }






}
