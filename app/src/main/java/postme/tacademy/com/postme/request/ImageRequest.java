package postme.tacademy.com.postme.request;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wonhochoi on 16. 9. 5..
 */
public class ImageRequest {
    ImageRequest instance;

    public ImageRequest getInstance() {
        if (instance == null) {
            instance = new ImageRequest();
        }
        return instance;
    }

    public ImageRequest() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public Bitmap getBitmapImg(String imgStr) {

        Bitmap bitmapImg = null;

        try {
            URL url = new URL(imgStr);
            // Character is converted to 'UTF-8' to prevent broken

            HttpURLConnection conn = (HttpURLConnection) url
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();
            bitmapImg = BitmapFactory.decodeStream(is);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return bitmapImg;
    }
}