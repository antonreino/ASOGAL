package com.example.tone.asogal.ViewModel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.tone.asogal.Adapters.MyAdapter;
import com.example.tone.asogal.Firebase.FirebaseReferences;
import com.example.tone.asogal.Model.Partido;
import com.example.tone.asogal.Model.Persona;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by antonio.reino on 09/03/2018.
 */

public class PartidoViewModel extends ViewModel {
    
    
    private MutableLiveData<List<Partido>> listapartidos;
    FirebaseDatabase db;
    DatabaseReference partidos;
    FirebaseAuth auth;
    DatabaseReference persona;
    private MutableLiveData<Persona> usrRegistrado;

    public LiveData<Persona> getUsrRegistrado(){
        if(usrRegistrado==null){
            usrRegistrado=new MutableLiveData<>();
        }
        return usrRegistrado;
    }



    private MutableLiveData<Bitmap> fotoUsr;

    public LiveData<Bitmap> getFotoUsr(){
        if(fotoUsr==null){
            fotoUsr=new MutableLiveData<>();
        }
        return fotoUsr;
    }


    public PartidoViewModel() throws IOException {
        db=FirebaseDatabase.getInstance();
        partidos=db.getReference(FirebaseReferences.Partido);
        auth = FirebaseAuth.getInstance();
        persona =db.getReference(FirebaseReferences.Persona);


        ObtenerPartidos();
        ObtenerPersona();






    }
    

    private void ObtenerPartidos() {
        FirebaseUser user =auth.getCurrentUser();
        partidos.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Datos", "onDataChange Firebase");

                List<Partido> listpartidos= new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Partido partido = new Partido();
                    partido.setIdPartido((String) ((HashMap) postSnapshot.getValue()).get("IdPartido"));
                    partido.setLiga((String) ((HashMap) postSnapshot.getValue()).get("Liga"));
                    partido.setIdEquipoLocal((String) ((HashMap) postSnapshot.getValue()).get("IdEquipoLocal"));
                    partido.setIdEquipoVisitante((String) ((HashMap) postSnapshot.getValue()).get("IdEquipoVisitante"));
                    partido.setIdActa((String) ((HashMap) postSnapshot.getValue()).get("IdActa"));
                    partido.setIdArbitro((String) ((HashMap) postSnapshot.getValue()).get("IdArbitro"));
                    partido.setCampo((String) ((HashMap) postSnapshot.getValue()).get("Campo"));
                    partido.setIdResultado((String) ((HashMap) postSnapshot.getValue()).get("IdResultado"));
                    partido.setHoraInicio((String) ((HashMap) postSnapshot.getValue()).get("HoraInicio"));
                    partido.setHoraFinal((String) ((HashMap) postSnapshot.getValue()).get("HoraFinal"));


                    listpartidos.add(partido);

                    Log.i("Datos", partido.getIdEquipoLocal() + ", " + listpartidos.size());


                }
                listapartidos.setValue(listpartidos);





            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void ObtenerPersona() {

        FirebaseUser user =auth.getCurrentUser();
        persona.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Persona persona2= new Persona();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    persona2.setNombre((String)((HashMap)postSnapshot.getValue()).get("Nombre"));
                    persona2.setTeléfono((String)((HashMap)postSnapshot.getValue()).get("Telefono"));
                    persona2.setIdpersona((String)((HashMap)postSnapshot.getValue()).get("idPersona"));
                    persona2.setFoto((String)((HashMap)postSnapshot.getValue()).get("Foto"));
                    persona2.setIdEquipo((String)((HashMap)postSnapshot.getValue()).get("idEquipo"));
                    persona2.setApellido1((String)((HashMap)postSnapshot.getValue()).get("apellido1"));
                    persona2.setApellido2((String)((HashMap)postSnapshot.getValue()).get("apellido2"));
                    persona2.setIdpersona((String)((HashMap)postSnapshot.getValue()).get("idPersona"));

                }
                usrRegistrado.setValue(persona2);
                new DownloadImageTask().execute(persona2.getFoto());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    public LiveData<List<Partido>> getPartidos() {
        if (listapartidos== null){
            listapartidos = new MutableLiveData<>();
        }
        return listapartidos;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {




        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            Log.i("Datos", "imagen descargada");

            fotoUsr.setValue(result);

        }
    }
}
