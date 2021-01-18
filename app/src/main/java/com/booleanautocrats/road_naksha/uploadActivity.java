package com.booleanautocrats.road_naksha;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.booleanautocrats.road_naksha.complaintdata.ComplaintInfo;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class uploadActivity extends AppCompatActivity {

    private static final int PLACE_PICKER_REQUEST = 1;
    ImageView imageView, imageView1;
    EditText description, addressId;
    Button post;

    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    private static final int IMAGE_PICK_CODE=1000;
    Uri i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.upload_activity);

        Bundle extras;
        Intent intent=getIntent();
        extras = intent.getExtras();
        String nagarName = extras.getString("nagarName");

        imageView = findViewById(R.id.uploadPic);
        description = findViewById(R.id.descriptionID);
        addressId = findViewById(R.id.addressId);
        post = findViewById(R.id.postBtn);
        imageView1 = findViewById(R.id.addLocation);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        storageReference = FirebaseStorage.getInstance().getReference("RoadImage");

        databaseReference = FirebaseDatabase.getInstance().getReference(nagarName);


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cDescription = description.getText().toString();
                String address = addressId.getText().toString();

                if (address.isEmpty() || cDescription.isEmpty() || i == null) {
                    if (cDescription.isEmpty()) {
                        description.setError("Please Fill it!");
                    } else if (address.isEmpty())
                        addressId.setError("Please Fill it!");
                    else Toast.makeText(uploadActivity.this, "Select an Image!", Toast.LENGTH_LONG).show();
                }
                else {
                    ProgressDialog progressDialog = new ProgressDialog(uploadActivity.this);
                    progressDialog.setTitle("Uploading");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    String imageName = System.currentTimeMillis() + "." + getFileExtension(i);
                    StorageReference fileReference = storageReference.child(imageName);
                    fileReference.putFile(i).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(uploadActivity.this, "Upload Successful", Toast.LENGTH_SHORT).show();

                            ComplaintInfo complaintInfo = new ComplaintInfo();
                            complaintInfo.setDone("0");
                            complaintInfo.setPriority("1");
                            complaintInfo.setnagarName(nagarName);
                            complaintInfo.setaddress(address);
                            complaintInfo.setText(cDescription);
                            complaintInfo.setImageName(imageName);

                            String complaintID = databaseReference.push().getKey();
                            assert complaintID != null;
                            databaseReference.child(complaintID).setValue(complaintInfo);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(uploadActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        }
                    });
                }
            }
        });

    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    public void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode==IMAGE_PICK_CODE && data != null && data.getData() != null) {
            i=data.getData();
            imageView.setImageURI(i);
        }
    }
}
