package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Joe on 10/25/16.
 */

public class GroceryDatabaseHelper extends SQLiteOpenHelper {
    private static GroceryDatabaseHelper mInstance;
    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "SHOPPING_DB";
    public static final String GROCERY_TABLE_NAME = "SHOPPING_LIST";

    public static final String COL_ID = "id";
    public static final String COL_NAME = "ITEM_NAME";
    public static final String COL_DESCRIPTION = "DESCRIPTION";
    public static final String COL_PRICE = "PRICE";
    public static final String COL_TYPE = "TYPE";
    public static final String CREATE_TABLE =
            "CREATE TABLE "+ GROCERY_TABLE_NAME + "("+
            COL_ID + " INTEGER PRIMARY KEY, "+
            COL_NAME+ " TEXT, "+
            COL_DESCRIPTION+" TEXT, "+
            COL_PRICE+" DOUBLE, "+
            COL_TYPE+" TEXT)";

    public static GroceryDatabaseHelper getInstance(Context context){
        if(mInstance==null){
            mInstance = new GroceryDatabaseHelper(context.getApplicationContext());
        }
        return mInstance;
    }
    private GroceryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+GROCERY_TABLE_NAME);
        onCreate(db);
    }

    public List<GroceryItem> getItemAsList(){
        LinkedList<GroceryItem> list = new LinkedList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(GROCERY_TABLE_NAME,
                        new String[]{COL_NAME,COL_DESCRIPTION},
                        null,null,null,null,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                list.add(new GroceryItem(cursor.getString(cursor.getColumnIndex(COL_NAME)),
                                        cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION))));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    public List<GroceryItem> searchItemByName(String name){
        LinkedList<GroceryItem> list = new LinkedList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(GROCERY_TABLE_NAME,
                null,
                COL_NAME + " LIKE ?",
                new String[]{"%"+name+"%"},
                null,null,null,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                list.add(new GroceryItem(cursor.getString(cursor.getColumnIndex(COL_NAME)),
                                        cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION))));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }
}
