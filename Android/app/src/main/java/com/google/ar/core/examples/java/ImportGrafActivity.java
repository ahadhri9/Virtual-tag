package com.google.ar.core.examples.java;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import static com.google.ar.core.examples.java.MapsActivity.MLOClat;
import static com.google.ar.core.examples.java.MapsActivity.MLOClng;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.ar.core.examples.java.Model.Graffiti;
import com.google.ar.core.examples.java.MapsActivity2;
import com.google.ar.core.examples.java.helloar.HelloArActivity;
import com.google.ar.core.examples.java.helloar.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ImportGrafActivity extends AppCompatActivity {

    //Widgets

    private Button uploadBtn, returnBtn;
    private ImageView imageView;
    private ProgressBar progressBar;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Graffitis");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    private Uri grafUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_graf);

        uploadBtn = findViewById(R.id.upload_btn);
        imageView = findViewById(R.id.addImage);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

       imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 2);
            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (grafUri != null) {
                    uploadToFirebase(grafUri);
                }else{
                    Toast.makeText(ImportGrafActivity.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK && data != null){

            grafUri = data.getData();
            imageView.setImageURI(grafUri);
        }
    }

    private void uploadToFirebase(Uri uri){
        StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String graffityId = root.push().getKey();
                        Graffiti graffiti = new Graffiti(null, uri.toString(), MLOClat, MLOClng, 0,graffityId);
                        root.child(graffityId).setValue(graffiti);
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(ImportGrafActivity.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        imageView.setImageResource(R.drawable.addgrafitti);
                        Intent i = new Intent(ImportGrafActivity.this, HelloArActivity.class);
                        startActivity(i);
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(ImportGrafActivity.this, "Uploading Failed ...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }
}