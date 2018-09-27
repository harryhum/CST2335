package com.example.harryhum.androidlabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends Activity {

    protected final String ACTIVITY_NAME = "ListItemsActivity";
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        ImageButton imageButton = findViewById(R.id.imageButton);
        Switch switchItem = findViewById(R.id.switchItem);
        CheckBox checkBox = findViewById(R.id.checkBox);

        checkBox.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
            builder.setMessage(R.string.finishActivity)
                    .setTitle(R.string.finish)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent resultIntent = new Intent( );
                            resultIntent.putExtra("Response", "Here is my response");
                            setResult(Activity.RESULT_OK, resultIntent);
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    })
                    .show();

        }));

        switchItem.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            CharSequence text;
            int duration;
            if (switchItem.isChecked()) {
                text = "Switch is on";
                duration = Toast.LENGTH_SHORT;
            } else {
                text = "Switch is off";
                duration = Toast.LENGTH_LONG;
            }
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }));

        imageButton.setOnClickListener(new View.OnClickListener() {

            private void dispatchTakePictureIntent() {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }

            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageButton imageButton = findViewById(R.id.imageButton);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageButton.setImageBitmap(imageBitmap);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}