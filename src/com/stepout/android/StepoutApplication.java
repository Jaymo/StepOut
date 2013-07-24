package com.stepout.android;

import com.WazaBe.HoloEverywhere.app.Application;
import com.stepout.android.data.StepoutDatabase;

public class StepoutApplication  extends Application {
	
	 public static StepoutDatabase mDb;
	 public static final String TAG = "StepOut";
	 
	@Override
   public void onCreate() {
       super.onCreate();

       mDb = new StepoutDatabase(this);
       mDb.open();
   }
	@Override
   public void onTerminate() {
       mDb.close();
      super.onTerminate();
   }

}
