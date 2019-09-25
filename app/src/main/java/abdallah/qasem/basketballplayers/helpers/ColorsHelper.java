package abdallah.qasem.basketballplayers.helpers;




import java.util.HashMap;

import abdallah.qasem.basketballplayers.R;

public class ColorsHelper {


    /*
    * this class have HashMap color
    * it have all background color
    * adapter use getColor to get correct color
    */



    private static ColorsHelper instance;
    private static HashMap<String, Integer> color = new HashMap<String, Integer>();



    public static ColorsHelper getInstance() {
        if (instance == null) {
            instance = new ColorsHelper();
            addColorToHashMap();

        }
        return instance;
    }

    private static void addColorToHashMap() {
        color.put("G-F", R.color.colorGreen);
        color.put("C", R.color.colorOrange);
        color.put("F", R.color.colorPurple);
        color.put("G",R.color.colorRed);
    }


    public static int getColor(String id) {

        if (id != null & id != "") {
            if (color.containsKey(id)) {
                return color.get(id);
            }
            return R.color.colorBlue;
        } else {

            return R.color.colorBlue;
            }
        }


    }

