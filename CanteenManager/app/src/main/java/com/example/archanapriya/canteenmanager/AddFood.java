package com.example.archanapriya.canteenmanager;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddFood extends AppCompatActivity {

    //private ImageButton foodImage;
    private ImageView imageView;
    private static final int GALLREQ =1;
    private EditText name,desc,price;
    DatabaseReference rootRef,demoRef;
    private Uri uri = null;
    private StorageReference storageReference = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        name= (EditText) findViewById(R.id.itemName);
        desc= (EditText) findViewById(R.id.itemDesc);
        price= (EditText) findViewById(R.id.itemPrice);

        imageView = (ImageView) findViewById(R.id.foodImageButton);

        rootRef = FirebaseDatabase.getInstance().getReference();
        //database reference pointing to demo node
        demoRef = rootRef.child("demo");


        // storageReference = FirebaseStorage.getInstance().getReference("Item");

    }

    public void btnClick(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLREQ);
    }

    // public void imageButtonClicked( View view)
    //{
       // Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
       // galleryIntent.setType("Image/*");
        //startActivityForResult(galleryIntent,GALLREQ);



  //  } -->
   /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==GALLREQ && resultCode==RESULT_OK) {
            uri = data.getData();
            foodImage = (ImageButton) findViewById(R.id.foodImageButton);
            foodImage.setImageURI(uri);
        }

    }

    public void addFoodButtonClicked(View view)
    {
        String name_text = name.getText().toString().trim();
        String desc_text = desc.getText().toString().trim();
        String price_text = price.getText().toString().trim();

        if(!TextUtils.isEmpty(name_text) && !TextUtils.isEmpty(desc_text) && !TextUtils.isEmpty(price_text)){
            StorageReference filepath = storageReference.child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    final Uri downloadurl = taskSnapshot.getDownloadUrl();
                    Toast.makeText(AddFood.this,"Image Uploaded", Toast.LENGTH_LONG).show();

                }
            });
        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case GALLREQ:
                if (resultCode == RESULT_OK) {
                     uri = data.getData();
                    String[] projection = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    String filepath = cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap bitmap = BitmapFactory.decodeFile(filepath);
                    Drawable drawable = new BitmapDrawable(bitmap);
                    imageView.setBackground(drawable);
                }
                break;
            default:
                break;
        }
    }

    public void addFoodButtonClicked(View view)
    {
        String name_text = name.getText().toString().trim();
        String desc_text = desc.getText().toString().trim();
        String price_text = price.getText().toString().trim();

        /*if(!TextUtils.isEmpty(name_text) && !TextUtils.isEmpty(desc_text) && !TextUtils.isEmpty(price_text)){
            StorageReference filepath = storageReference.child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    final Uri downloadurl = taskSnapshot.getDownloadUrl();
                    Toast.makeText(AddFood.this,"Image Uploaded", Toast.LENGTH_LONG).show();

                }
            });
        }*/

        demoRef.push().setValue(name_text);
    }



}