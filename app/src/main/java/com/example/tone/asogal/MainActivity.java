package com.example.tone.asogal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {
    //Importamos la clase para poder iniciar sesión.
    FirebaseAuth auth;
    EditText EtEmail,EtPass;
    Button btnIni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnIni=(Button)findViewById(R.id.btnInicio);
        EtEmail=(MaterialEditText)findViewById(R.id.etemail);
        EtPass=(MaterialEditText)findViewById(R.id.etpass);
        auth=FirebaseAuth.getInstance();

        if (auth.getCurrentUser()!=null){
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }


        btnIni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InicioSesion();
            }
        });


    }
    //Método para iniciar la sesión
    private void InicioSesion() {
        auth.signInWithEmailAndPassword(EtEmail.getText().toString(),EtPass.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        Toast.makeText(MainActivity.this, "Inicio Correcto", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }
}
