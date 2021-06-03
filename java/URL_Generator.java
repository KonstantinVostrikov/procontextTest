import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class URL_Generator {
    private static final String template = "http://www.cbr.ru/scripts/XML_daily_eng.asp?date_req=";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public List<String> listOfURLS(){
        List<String> urlList = new ArrayList<>(90);
        LocalDate date = LocalDate.now();

        for (int i = 0; i < 90; i++) {
            LocalDate past = date.minusDays(i);
            String url = template + formatter.format(past);
            urlList.add(url);

        }

        return urlList;
    }

}
