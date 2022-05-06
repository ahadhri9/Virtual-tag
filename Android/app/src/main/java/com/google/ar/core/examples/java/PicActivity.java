package com.google.ar.core.examples.java;

import androidx.appcompat.app.AppCompatActivity;


import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.ar.core.examples.java.helloar.R;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PicActivity extends AppCompatActivity {
    private static final int RETOUR_PRENDRE_PHOTO = 1;
    private Button btnPrendrePhoto;
    private ImageView imageAffichePhoto;
    private Button btnEnreg;
    private String photoPath = null;
    private Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        initActivity();
    }
    private void initActivity(){
        //recuperation des objets graphiques
        btnPrendrePhoto =(Button)findViewById(R.id.btnPrendPhoto);
        imageAffichePhoto =(ImageView)findViewById(R.id.imgAffichePhoto);
        btnEnreg = (Button)findViewById(R.id.btnEnreg);
        //methode pour gerer l'evenement
        createOnClickBtnPrendrePhoto();
        creteOnclickBtnEnreg();
    }
    private void creteOnclickBtnEnreg(){
        btnEnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaStore.Images.Media.insertImage(getContentResolver(), image, "nom image", "image enregistre");

            }
        });


    }
    private void createOnClickBtnPrendrePhoto(){
        btnPrendrePhoto.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                prendreUnePhoto();

            }
        });

    }
    private void prendreUnePhoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File photoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            try {
                File photoFile = File.createTempFile("photo"+time,".jpg",photoDir);
                //enregistrer le chemin complet
                photoPath = photoFile.getAbsolutePath();
                Uri photoUri = FileProvider.getUriForFile(PicActivity.this,
                        PicActivity.this.getApplicationContext().getPackageName()+".provider",photoFile );
                //transfert uri vers l'intent pour enregistrement dans un fichier temporaire
                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                //ouvrir l'activity par rapport a l'intent
                startActivityForResult(intent,RETOUR_PRENDRE_PHOTO);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RETOUR_PRENDRE_PHOTO && resultCode==RESULT_OK){
            //recuperer l'image
            image = BitmapFactory.decodeFile(photoPath);
            //afficher l'image
            imageAffichePhoto.setImageBitmap(image);
        }
    }
}


