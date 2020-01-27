package Utilities;

import java.util.ArrayList;
import java.util.List;

import DatabaseConnection.MovieDbController;
public class MovieSorter {
    int pass;
    public  void bubbleSort(ArrayList<movDat_parser> list) {
        movDat_parser temp;

        for (int i = 0; i < list.size(); i++) {
            boolean swap = false;
            for (int j = 0; j < list.size() - i - 1; j++) {
                //If a[j] is greater than a[j+1] then we still need to iterate,
                // but if not then it means that the array is already sorted
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    temp = list.get(j);
                    list.set(i, list.get(j + 1));
                    list.set(j + 1, temp);

                }
            }
            pass++;
            if (!swap)
                break;

        }

    }

    public static void main(String args[]){
        MovieSorter ms = new MovieSorter();
        movDat_parser parser = new movDat_parser();
        MovieDbController db = new MovieDbController();

        db.setup();
        db.query("The punisher");
        ArrayList<movDat_parser> parsers= new ArrayList<movDat_parser>();
        parsers=MovieDbController.list;
        ms.bubbleSort(parsers);


    }
}
