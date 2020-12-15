package triangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CountingDuplicates {
    static int duplicateCount(String text) {
        text = text.toLowerCase();
        char[] charArray = text.toCharArray();
        List<Character> charList = new ArrayList<>();
        for (char character : text.toCharArray()) {
            charList.add(character);
        }
        int count;
        int duplicates = 0;
        for (int i = 0; i < charList.size(); i++) {
            count = 1;
            for (int j = i + 1; j < charList.size(); j++) {
                if (charList.get(i) == charList.get(j) && charList.get(i) != null) {
                    count++;
                    charList.set(j, ' ');
                }
            }
            if (count > 1 && charList.get(i) != ' ')
                duplicates++;
        }
        return duplicates;

    }
}
