package postme.tacademy.com.postme.manager;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.skp.Tmap.TMapAddressInfo;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapTapi;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by wonhochoi on 16. 9. 19..
 */
public class Geopence extends AsyncTask{
    TMapAddressInfo tMapAddressInfo;
    TMapData tMapData;
    TMapTapi tmaptapi;
    public Geopence(){
        tMapData = new TMapData();
    }

    @Override
    public String doInBackground(Object... params) {
        tmaptapi = new TMapTapi((Context)params[0]); tmaptapi.setSKPMapAuthentication ("91f5919e-a5b3-3cf2-a3f2-481b7f0b8c9f");
        try {
            tMapAddressInfo = tMapData.reverseGeocoding((double)params[1], (double)params[2], (String)params[3]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return tMapAddressInfo.strFullAddress;
    }
}
