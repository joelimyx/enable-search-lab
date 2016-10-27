package ly.generalassemb.drewmahrt.shoppinglistver2;

/**
 * Created by Joe on 10/25/16.
 */

public class GroceryItem {
    private String mName;
    private String mDescription;

    public GroceryItem(String name, String description) {
        mName = name;
        mDescription = description;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
