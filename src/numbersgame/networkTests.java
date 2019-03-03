package numbersgame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class networkTests {
    public static void main(String[] args) throws IOException {
        List<String> inputList = new ArrayList<>();

        inputList.add("NAME");
        inputList.add("Albert");
        inputList.add("Barry");
        inputList.add("Chris");
        inputList.add("Derek");

        System.out.println(inputList.toString());
        inputList.remove(0);
        String[] names = new String[inputList.size()];
        names = inputList.toArray(names);

        for(int i = 0; i < names.length ; i++){
            System.out.println(names[i]);
        }
    }
}
