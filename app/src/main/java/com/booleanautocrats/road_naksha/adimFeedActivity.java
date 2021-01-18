package com.booleanautocrats.road_naksha;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.booleanautocrats.road_naksha.complaintdata.ComplaintInfo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class adimFeedActivity extends AppCompatActivity {

    String nagarName;
    TextView nagar;
    LinearLayout containingLayout;


    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.admin_feed_container);

        Bundle extras;
        Intent intent=getIntent();
        extras = intent.getExtras();
        nagarName = extras.getString("nagarName");

        containingLayout = findViewById(R.id.containing_layout_id);
        nagar=findViewById(R.id.nagarName);
        nagar.setText(nagarName);

        databaseReference = FirebaseDatabase.getInstance().getReference(nagarName);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data : snapshot.getChildren()) {
                    String key = data.getKey();
                    View view = getLayoutInflater().inflate(R.layout.admni_feed_activity, null, false);
                    ComplaintInfo complaintInfo = data.getValue(ComplaintInfo.class);
                    ImageView imageView = view.findViewById(R.id.checkText);
                    TextView complaint = view.findViewById(R.id.ComplaintTxt);
                    TextView address = view.findViewById(R.id.roadName);
                    TextView priority = view.findViewById(R.id.priorityCount);
                    TextView status = view.findViewById(R.id.status);
                    ImageView count = view.findViewById(R.id.count);
                    ImageView statusMenu = view.findViewById(R.id.statusMenu);
                    ImageView statusUpdate = view.findViewById(R.id.statusUpdate);

                    statusMenu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(statusUpdate.getVisibility()==View.VISIBLE)
                                statusUpdate.setVisibility(View.INVISIBLE);
                            else
                                statusUpdate.setVisibility(View.VISIBLE);
                        }
                    });

                    statusUpdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            databaseReference.child(key).child("done").setValue("1");
                            status.setText("Done");
                            status.setTextColor(Color.BLACK);
                            statusUpdate.setVisibility(View.INVISIBLE);
                        }
                    });

                    count.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            assert complaintInfo != null;
                            int x = Integer.parseInt(priority.getText().toString());
                            x++;
                            databaseReference.child(key).child("priority").setValue(String.valueOf(x));
                            priority.setText(String.valueOf(x));
                        }
                    });

                    assert complaintInfo != null;
                    storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://road-naksha.appspot.com/").child("RoadImage/"+complaintInfo.getImageName());
                    try {
                        final File localFile = File.createTempFile("image", "jpg");
                        storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(adimFeedActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    complaint.setText("Description:\n"+complaintInfo.getText());
                    address.setText("Address:\n"+complaintInfo.getaddress());
                    priority.setText(complaintInfo.getPriority());
                    if(complaintInfo.getDone().equals("0"))
                        status.setText("Pending");
                    else {
                        status.setText("Done");
                        status.setTextColor(Color.BLACK);
                    }

                    containingLayout.addView(view);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
