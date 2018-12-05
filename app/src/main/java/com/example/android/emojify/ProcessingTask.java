package com.example.android.emojify;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

public class ProcessingTask extends AsyncTask<Bitmap,Void,Bitmap> {

    @SuppressLint("StaticFieldLeak")
    Context context;
    private ProgressDialog pd;

    public ProcessingTask(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(context);
        pd.setMessage("Emojifying image, please wait...");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected Bitmap doInBackground(Bitmap... bitmaps) {

        Bitmap image = bitmaps[0];

        image = Emojifier.detectFacesAndOverlay(context, image);

        return image;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        pd.dismiss();
        if(bitmap!=null) {
            MainActivity.toggleVisibility(bitmap);
        }
        else {
            Toast.makeText(context, "No faces detected!", Toast.LENGTH_SHORT).show();
        }
    }
}
