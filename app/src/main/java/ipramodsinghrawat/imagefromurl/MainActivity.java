package ipramodsinghrawat.imagefromurl;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    Bitmap bitmap;
    ProgressDialog pDialog;
    String imageURL="https://upload.wikimedia.org/wikipedia/en/c/cb/Monkey_D_Luffy.png";
    String imageURL2="https://dw9to29mmj727.cloudfront.net/misc/newsletter-naruto3.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView)findViewById(R.id.imageView);

        new LoadImage().execute(imageURL);
    }

    /* OnClick Button function to show image */
    public void load_image(View view){
        new LoadImage().execute(imageURL2);
    }

    /* start:  class to load image to ImageView */
    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Loading Image ....");
            pDialog.show();
        }
        protected Bitmap doInBackground(String... args) {
            try{
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap image) {
            if(image != null){
                img.setImageBitmap(image);
                pDialog.dismiss();
            }else{
                pDialog.dismiss();
                Toast.makeText(MainActivity.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
