import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by andrewwong on 5/31/17.
 */
public class Receipt {
    HashMap<String, ArrayList<String>> itemMap;
    int errors;

    public Receipt(HashMap<String, ArrayList<String>> itemMap) {
        this.itemMap = itemMap;
        this.errors = 0;
    }

    public String toString() {
        // get set of keys (names)
        // print name: key
        // print seen: timeSeen
        // print price and number of times that price occurs in the arraylist of prices
        StringBuilder sb = new StringBuilder();
        int numOfItems = itemMap.size();

        String[] nameArray = new String[numOfItems];
        itemMap.keySet().toArray(nameArray);

        HashMap<String, Integer> priceMap;

        for(int i = 0; i < numOfItems; i++) {
            try{
                sb.append(formatNameLine(nameArray, i));
                priceMap = MapFactory.createPriceMap(itemMap.get(nameArray[i]));
                sb.append(formatPriceLine(priceMap));
            } catch (NullPointerException npe) {
                errors++;
            }

        }
        sb.append("Errors       \t\tseen: " + this.errors + " times");
        return sb.toString();
    }


    // wrap name in nameArray[index] in optional, errors++ if .get is null
    private String formatNameLine(String[] nameArray, int index) throws NoNameException {
        StringBuilder nameBuilder = new StringBuilder();
        int lengthOfName = 0;
        lengthOfName = nameArray[index].length();
        int numOfSpaces = 8 - lengthOfName;
        nameBuilder.append("name:");
        for(int j = 0; j < numOfSpaces; j++) {
            nameBuilder.append(" ");
        }
        nameBuilder.append(nameArray[index]);
        nameBuilder.append("\t\tseen: ");
        nameBuilder.append(itemMap.get(nameArray[index]).size());
        nameBuilder.append(" times");
        nameBuilder.append("\n=============\t\t=============\n");
        return nameBuilder.toString();
    }

    private String formatPriceLine(HashMap<String, Integer> priceMap) throws NoPriceException{
        StringBuilder priceBuilder = new StringBuilder();
        String[] priceArray = new String[priceMap.size()];
        priceMap.keySet().toArray(priceArray);
        for (int k = 0; k < priceMap.size(); k++) {
        if(priceArray[k]!=null){
            priceBuilder.append("price:   " + priceArray[k]);
            priceBuilder.append("\t\tseen: ");
            priceBuilder.append(priceMap.get(priceArray[k]));
            priceBuilder.append(" times");
            priceBuilder.append("\n=============\t\t=============\n");
        } else errors++;
        }

        return priceBuilder.toString();
    }

}
