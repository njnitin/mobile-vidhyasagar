package com.jain.vidhyasagarsant.data.local.json;

/**
 * Created by HP on 25-Dec-17.
 */

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.ArrayList;
import android.util.Log;

public class JsonParserHelper  extends Application {

    private static JsonParserHelper mInstance=null;

    private static Context myContext=null;

    private JsonParserHelper(){}

/*    private JsonParserHelper(Context myContext){
        this.myContext=myContext;
    }*/

    @Override
    public void onCreate() {
        super.onCreate();
        // Setup singleton instance
        mInstance = this;
    }

    public void setMyContext(Context myContext) {
        this.myContext = myContext;
    }

    public static JsonParserHelper getInstance() {
        if(mInstance == null)
        {
/*            synchronized(JsonParserHelper.class){
                if(mInstance == null){*/
                    mInstance = new JsonParserHelper();
/*                }
            }*/
        }
        return mInstance;
    }

    public String loadFromAsset(String filePath) {
        String contents = "";
        InputStream is = null;
        BufferedReader reader = null;
        try {
            //       return Observable.fromArray(gson.fromJson(jsonParserHelper.loadAsset("content.json"), Category.class));
            is = myContext.getAssets().open(filePath);
            reader = new BufferedReader(new InputStreamReader(is));
            contents = reader.readLine();
            String line = null;
            while ((line = reader.readLine()) != null) {
                contents += '\n' + line;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }
        return contents;
/*            try {
//            InputStream is = getActivity().getAssets().open("yourfilename.json");
            Log.d("JsonParserHelper", "File Name in JsonParserHelper :- "+ filePath);
            InputStream is = getAssets().open(filePath);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;*/
    }

/*    public String loadFromDrive(String fileName){
        String fileId = "0BwwA4oUTeiV1UVNwOHItT0xfa2M";
        OutputStream outputStream = new ByteArrayOutputStream();
        driveService.files().export(fileId, "application/pdf")
                .executeMediaAndDownloadTo(outputStream);

        return null;
    }*/

    public void loadJSONFromAsset(String filePath){
        try {
            String json = loadFromAsset(filePath);
            JSONArray m_jArry=null;
            try{
                JSONObject obj = new JSONObject(json);
                m_jArry = obj.getJSONArray("formules");
            } catch (JSONException e) {
                // throw new RuntimeException(e);
            }

            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            if (m_jArry!=null){
                for (int i = 0; i < m_jArry.length(); i++) {
                    JSONObject jo_inside = m_jArry.getJSONObject(i);
                    // Log.d("Details-->", jo_inside.getString("formule"));
                    String formula_value = jo_inside.getString("formule");
                    String url_value = jo_inside.getString("url");

                    //Add your values in your `ArrayList` as below:
                    m_li = new HashMap<String, String>();
                    m_li.put("formule", formula_value);
                    m_li.put("url", url_value);

                    formList.add(m_li);
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



}
