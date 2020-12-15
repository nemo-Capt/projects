package triangle;

import java.util.ArrayList;
import java.util.List;

public class FindDuplicates {

    public String findDuplicates(String str) {
        char[] initial = str.toCharArray();
        List<Character> result = new ArrayList<>();
        for (int i = 0; i < initial.length - 1; i++) {
            if (!result.contains(initial[i])) {
                result.add(initial[i]);
            }
        }
        return result.toString().replace(", ", "").replace("[", "").replace("]", "");

    }
}
