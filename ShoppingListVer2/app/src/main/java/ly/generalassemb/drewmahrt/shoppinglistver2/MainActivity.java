package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuWrapperFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ly.generalassemb.drewmahrt.shoppinglistver2.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {
    public static final String TAG ="Grocery";
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    GroceryDatabaseHelper mHelper;
    GroceryViewAdapter mGroceryViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        mHelper = GroceryDatabaseHelper.getInstance(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mGroceryViewAdapter = new GroceryViewAdapter(mHelper.getItemAsList());
        mRecyclerView.setAdapter(mGroceryViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_button).getActionView();
        ComponentName componentName = new ComponentName(this,MainActivity.class);
        searchView.setSearchableInfo(manager.getSearchableInfo(componentName));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    public void handleIntent(Intent intent){
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            List<GroceryItem> list = mHelper.searchItemByNameOrType(query);
            mGroceryViewAdapter.replaceData(list);
        }
    }
}
