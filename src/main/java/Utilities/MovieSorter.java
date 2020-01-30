package Utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import DatabaseConnection.MovieDbController;
import DatabaseConnection.movies;

public class MovieSorter {
    int pass;
    MovieDbController db = new MovieDbController();
    public List<movies> ls;
    public  List bubbleSort(List<movies> list) {
        movies temp;

        for (int i = 0; i < list.size(); i++) {
            boolean swap = false;
            for (int j = 0; j < list.size() - i - 1; j++) {
                //If a[j] is greater than a[j+1] then we still need to iterate,
                // but if not then it means that the array is already sorted
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    temp = list.get(j);
                    list.set(i, list.get(j + 1));
                    list.set(j + 1, temp);
                    ls = list;
                }
            }
            pass++;
            if (!swap)
                break;

        }
    return list;

    }


    public List sorted(List<movies> list){
        bubbleSort(list);
        Collections.reverse(list);
        return list;
    }
}
