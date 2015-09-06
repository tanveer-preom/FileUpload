package com.example.tanveer.imagereader;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String directory;
    private  String url ="http://192.168.0.169:8080/UploadServer/Upload";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void onBrowseClicked(View v)
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpg");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK)
        {
            if(requestCode==1)
            {
               Uri filePath = data.getData();
                final String path = getRealPathFromURI(MainActivity.this, filePath);
                final TextView directory = (TextView) findViewById(R.id.dir);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        directory.setText(path);
                    }
                });
            }
        }
    }
    public void onUploadClicked(View v)
    {
        Log.i("tanvy", "upload started");
        new Thread()
        {
          public void run()
          {
              TextView dir = (TextView) findViewById(R.id.dir);
              String directory = dir.getText().toString();
              Bitmap bm = BitmapFactory.decodeFile(directory);
              ByteArrayOutputStream baos = new ByteArrayOutputStream();
              bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
              byte[] pic = baos.toByteArray();
              String encodedImage = Base64.encodeToString(pic, Base64.DEFAULT);
              sendImageToServer(new File(directory).getName(), encodedImage);
          }
        }.start();


        //Log.i("tanvy",encodedImage);

    }
    private void sendImageToServer(final String fileName,final String encodedimage)
    {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);


// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("img_name",fileName);
                params.put("encoded_image",encodedimage);

                return params;
            }


        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }



    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
