package  com.stepout.android.dataliberation;

import org.json.JSONObject;

import android.os.AsyncTask;

import com.stepout.android.parsers.JsonParser;

public class JsonImportTask extends AsyncTask< String, Integer, JSONObject > { 

    JSONObject jArray,JSON;
	
   @Override
    protected void onPreExecute() {
        super.onPreExecute();            

    }
	@Override
	protected JSONObject doInBackground(String... params) {
		jArray = JsonParser.getJSONfromURL(params[0]);
		 //Check for some validation stuff
		// clear DB and install new artists and  stuff
			
		return jArray;
		
	}
	
	@Override
	protected void onProgressUpdate(Integer... progress) {
		
		
	}
	
	
	
	
	
}
	



