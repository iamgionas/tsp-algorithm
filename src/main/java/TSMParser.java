import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TSMParser {

    public static List<City> Parse(File file) {

        List<City> temp = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String str;
            Boolean skipInfo = false;

            while ((str = br.readLine()) != null) {

                if (skipInfo && !str.equals("EOF")) {
                    String[] citySplitted = str.split(" ");
                    City city = new City(Integer.parseInt(citySplitted[0]),
                            Double.parseDouble(citySplitted[1]),
                            Double.parseDouble(citySplitted[2]));
                    temp.add(city);
                }

                if (str.equals("NODE_COORD_SECTION")) {
                    skipInfo = true;
                }
            }

            return temp;
        } catch (IOException e) {
            System.out.println("File not found!");
            return null;
        }
    }
}
