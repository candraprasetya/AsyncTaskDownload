package com.kardusinfo.asynctaskdownload;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ProgressDialog mProgressDialog;
    Context context;
    ImageView image;

    public DownloadImageTask(Context context, ImageView imageView) {
        this.context = context;
        this.image = imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setTitle("Downloading Image");
        mProgressDialog.setMessage("Downloading Image....");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.show();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        image.setImageBitmap(bitmap);
        mProgressDialog.dismiss();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
     }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String urlLink = strings[0];
        InputStream in;
        try {
            URL myUrl = new URL(urlLink);
            HttpURLConnection myConn = (HttpURLConnection) myUrl.openConnection();
            myConn.connect();
            in = myConn.getInputStream();
            Bitmap myMap = BitmapFactory.decodeStream(in);
            return myMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}