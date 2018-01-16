/*
UNITED ENGINEERS
Activity for the radio layout XML. Mostly logic for the PAUSE and PLAY button
*/
package com.engineers.united.unitedengineers;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class RadioActivity extends Activity {

    String fileName[];
    Button b_play;
    MediaPlayer mediaPlayer;
    boolean prepared = false;
    boolean started = false;
    String name, streamLink, description, imageURL;
    ImageView mImageView;
    URL url1;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
        Bundle bundle = getIntent().getExtras();

        Toast.makeText(this, getResources().getString(R.string.radioToast), Toast.LENGTH_LONG).show();

        streamLink = bundle.getString(getString(R.string.link));
        imageURL = bundle.getString(getString(R.string.imageurl));
        description = bundle.getString(getString(R.string.description));

        TextView descripView = (TextView) findViewById(R.id.descripView);
        descripView.setText(description);

        b_play = (Button) findViewById(R.id.b_play);
        b_play.setEnabled(false);
        b_play.setText(R.string.LOADING);
        
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        new PlayerTask().execute(streamLink);


        try {
            url1  = new URL(imageURL);
        } catch (MalformedURLException e) {

            e.printStackTrace();
        }
        new DownloadFilesTask().execute(url1);

        b_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(started){
                    started = false;
                    mediaPlayer.pause();
                    b_play.setText(R.string.PLAY);
                } else {
                    started = true;
                    mediaPlayer.start();
                    b_play.setText(R.string.PAUSE);
                }
            }
        });
    }

  /*  public void CancelDownload(View view){
        Toast.makeText(this, "CancelDownload", Toast.LENGTH_LONG).show();
        DownloadFilesTask.cancel(true);

    }*/

    class PlayerTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String...strings){

            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.prepare();
                prepared = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return prepared;
        }
        @Override
        protected void onPostExecute(Boolean aBoolean){
            super.onPostExecute(aBoolean);
            b_play.setEnabled(true);
            b_play.setText(R.string.PLAY);

        }
    }

    private class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {

        @Override
        protected Long doInBackground(URL... urls) {
            int count = urls.length;
            fileName = new String[count];
            long totalSize = 0;
            for (int i = 0; i < count; i++) {
                totalSize += DownloadFile(urls[i], i);
                publishProgress((int)((i / (float) count) * 100));

                if (isCancelled()) break;
            }
            return totalSize;
        }

        private long DownloadFile(URL url, int j) {

            int total = 0;
            try {
                URLConnection conn = url.openConnection();
                if (!(conn instanceof HttpURLConnection))
                    throw new IOException("Not an HTTP connection");


                HttpURLConnection httpConn  = (HttpURLConnection) conn;

                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setRequestMethod("GET");
                httpConn.connect();

                int response = httpConn.getResponseCode();
                if (response == HttpURLConnection.HTTP_OK) {
                    InputStream input = httpConn.getInputStream();


                    String[] path = url.getPath().split("/");
                    String imageName = path[path.length - 1];
                    String PATH = getFilesDir() + "/Download/" ;
                    File folder = new File(PATH);
                    folder.mkdirs();
                    fileName[j] = folder + "/" + imageName;
                    String fileName = imageName;
                    OutputStream output = new FileOutputStream(folder+"/"+fileName);

                    byte data[] = new byte[1024];

                    int count;
                    while ((count = input.read(data)) != -1) {
                        total += count;
                        output.write(data, 0, count);
                    }
                    output.flush();
                    output.close();
                    input.close();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return total;
        }

        protected void onPostExecute(Long result) {
            mImageView = (ImageView) findViewById(R.id.im1);
            Bitmap myBitmap = BitmapFactory.decodeFile(fileName[0]);
            mImageView.setImageBitmap(myBitmap);
        }


       /* protected void onProgressUpdate(Integer... values) {progress.incrementProgressBy(values[0]);}*/

       /* protected void onCancelled() {

            Toast.makeText(this, "on Cancelled called", Toast.LENGTH_LONG).show();

        }*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(started){
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(started){
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(prepared){
            mediaPlayer.release();
        }
    }
}