package com.example.tone.asogal.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.tone.asogal.Firebase.FirebaseReferences;
import com.example.tone.asogal.Model.Persona;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tone on 11/03/2018.
 */

public class PersonaViewModel extends ViewModel {
    private MutableLiveData<List<Persona>> listapersona;
    FirebaseDatabase DB;
    DatabaseReference persona;
    FirebaseAuth auth;


    public PersonaViewModel() {
        DB=FirebaseDatabase.getInstance();
        persona =DB.getReference(FirebaseReferences.Persona);
        auth = FirebaseAuth.getInstance();


        ObtenerPersona();

    }

    private void ObtenerPersona() {

        FirebaseUser user =auth.getCurrentUser();
        persona.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<Persona> listpersona= new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Persona persona= new Persona();
                    persona.setNombre((String)((HashMap)postSnapshot.getValue()).get("Nombre"));
                    persona.setTeléfono((String)((HashMap)postSnapshot.getValue()).get("Telefono"));
                    persona.setIdpersona((String)((HashMap)postSnapshot.getValue()).get("idPersona"));
                    persona.setFoto((String)((HashMap)postSnapshot.getValue()).get("Foto"));
                    persona.setIdEquipo((String)((HashMap)postSnapshot.getValue()).get("idEquipo"));
                    persona.setApellido1((String)((HashMap)postSnapshot.getValue()).get("apellido1"));
                    persona.setApellido2((String)((HashMap)postSnapshot.getValue()).get("apellido2"));
                    persona.setIdpersona((String)((HashMap)postSnapshot.getValue()).get("idPersona"));


                    listpersona.add(persona);

                }
             //   listapersona.setValue(listpersona);




            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public LiveData<List<Persona>> getPersona() {
        if (listapersona == null){
            listapersona = new MutableLiveData<>();
        }
        return listapersona;
    }
}
