package ly.generalassemb.drewmahrt.shoppinglistver2;

/**
 * Created by Joe on 10/25/16.
 */

public class GroceryItem {
    private String mName;
    private String mDescription;
    private String mType;

    public GroceryItem(String name, String description, String type) {
        mName = name;
        mDescription = description;
        mType = type;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
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
