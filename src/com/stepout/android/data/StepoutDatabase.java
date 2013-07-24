package com.stepout.android.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.stepout.android.models.mainmenu;

public class StepoutDatabase {
	
	public static final String MAIN_MENU_ID = "mm_id";
	
	public static final String MAIN_MENU_IMAGE = "mm_image_url";

    public static final String MAIN_MENU_NAME = "mm_name";

    public static final String MAIN_MENU_INCLUDES = "mm_includes";
    
    public static final String MAIN_MENU_RATING = "mm_rating";
    
    public static final String[] TBL_MAIN_MENU = new String[] {MAIN_MENU_IMAGE, MAIN_MENU_NAME, MAIN_MENU_INCLUDES,MAIN_MENU_RATING};
    
    private DatabaseHelper mDbHelper;

    private SQLiteDatabase mDb;
    
    private static final int DATABASE_VERSION = 1;
    
    private static final String DATABASE_NAME = "StepOut_DB";

    private static final String MAIN_MENU_TABLE = "main_menu";
    
    private static final String MAIN_MENU_TABLE_CREATE = 
        	"CREATE TABLE IF NOT EXISTS  "
            + MAIN_MENU_TABLE 
            + " (" + MAIN_MENU_ID 
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MAIN_MENU_IMAGE 
            + " VARCHAR(200) NOT NULL, " 
            + MAIN_MENU_NAME 
            + " VARCHAR(150) NOT NULL,"
            + MAIN_MENU_INCLUDES 
            + " VARCHAR(150) NOT NULL,"
            + MAIN_MENU_RATING
            +" VARCHAR(10) NOT NULL)";
    
    private final Context mContext;
    
    private static final String TAG = "StepOut";
    
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            
        }
        
        @Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(MAIN_MENU_TABLE_CREATE);
			
		}

		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion
                    + " which destroys all old data");
			
			//So begineth Upgrade of Main Menu Table in our Current Database
			List<String> MainMenuColumns;
			db.execSQL(MAIN_MENU_TABLE_CREATE);
			MainMenuColumns =  StepoutDatabase.getColumns(db, MAIN_MENU_TABLE);
			db.execSQL("ALTER TABLE " + MAIN_MENU_TABLE + " RENAME TO temp_" + MAIN_MENU_TABLE);
			db.execSQL(MAIN_MENU_TABLE_CREATE);
			MainMenuColumns.retainAll(StepoutDatabase.getColumns(db, MAIN_MENU_TABLE));
			String cols = StepoutDatabase.join(MainMenuColumns, ",");
			db.execSQL(String.format("INSERT INTO %s (%s) SELECT %s FROM temp_%s", MAIN_MENU_TABLE, cols, cols, MAIN_MENU_TABLE));
			db.execSQL("DROP TABLE IF EXISTS temp_" + MAIN_MENU_TABLE);
			onCreate(db);
			
		}
     }


		
		/**
	     * Credits http://goo.gl/7kOpU
	     * @param db
	     * @param tableName
	     * @return
	     */
	    public static List<String> getColumns(SQLiteDatabase db, String tableName) {
	    	Log.v("List<String>", tableName);
	        List<String> ar = null;
	        Cursor c = null;

	        try {
	            c = db.rawQuery("SELECT * FROM " + tableName + " LIMIT 1", null);

	            if (c != null) {
	                ar = new ArrayList<String>(Arrays.asList(c.getColumnNames()));
	            }

	        } catch (Exception e) {
	            Log.v(tableName, e.getMessage(), e);
	            e.printStackTrace();
	        } finally {
	            if (c != null)
	                c.close();
	        }
	        return ar;
	    }

	    public static String join(List<String> list, String delim) {
	        StringBuilder buf = new StringBuilder();
	        int num = list.size();
	        for (int i = 0; i < num; i++) {
	            if (i != 0)
	                buf.append(delim);
	            buf.append((String)list.get(i));
	        }
	        return buf.toString();
	    }
	    
	    

		public StepoutDatabase(Context context) {
			this.mContext = context;
		}

		public StepoutDatabase open() throws SQLException {
			mDbHelper = new DatabaseHelper(mContext);
			mDb = mDbHelper.getWritableDatabase();

			return this;
		}

		public void close() {
			mDbHelper.close();
		}

		/**
		 * The Below code will Populate MAIN_MENU_TABLE
		 * 
		 * @param ContentValues
		 *            values
		 * @return
		 */
		public void populate_main_menu(ContentValues values) {
			mDb.insertOrThrow(MAIN_MENU_TABLE, null, values);

		}
		/**
	     * The Below code will Empty  MAIN_MENU_TABLE 
	     * @param 
	     * @return
	     */
	    public void empty_main_menu() {
            mDb.delete(MAIN_MENU_TABLE, null, null);

	    }
	    
      
	    /**
	     * The Below code will 
	     */
	 // Getting All Contacts
	    public List<mainmenu> BuildMainmenu() {
	        List<mainmenu> mmList = new ArrayList<mainmenu>();
	        String sql = "SELECT  * FROM " + MAIN_MENU_TABLE +"COLLATE NOCASE";
	        Cursor cursor = mDb.rawQuery(sql, null);
	        if (cursor.moveToFirst()) {
	            do {
	            	mainmenu mm = new mainmenu();
	            	mm.getmm_id(Integer.parseInt(cursor.getString(0)));
	            	mm.getmm_image_url(cursor.getString(1));
	            	mm.getmm_name(cursor.getString(2));
	            	mm.mm_rating(cursor.getString(3));
	                // Adding contact to list
	            	mmList.add(mm);
	            } while (cursor.moveToNext());
	        }
	        Log.v("List", mmList.toString());     
	        return mmList;
	    }
    
    }

