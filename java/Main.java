import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Map<String, List<MyCurrency>> currencyTable = new HashMap<>(34);

        List<String> urlList = new URL_Generator().listOfURLS();


        for (String url : urlList){
            addDayDataToMap(url, currencyTable);
        }


        MyCurrency max = CurrencyEvaluator.getMaxCurrency(currencyTable);


        MyCurrency min = CurrencyEvaluator.getMinCurrency(currencyTable);



        Set<MyCurrency> myCurrencyTreeSet = CurrencyEvaluator.getAverageCurrency(currencyTable);

        new WindowUI().go(max, min, myCurrencyTreeSet);




    }


    private static Document loadTestDocument(String url) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        return factory.newDocumentBuilder().parse(new URL(url).openStream());
    }

    private static void addDayDataToMap(String url, Map<String, List<MyCurrency>> map) {

        try {
            Document doc = loadTestDocument(url);
            doc.getDocumentElement().normalize();
            Element root = doc.getDocumentElement();
//            System.out.println("Root element: " + root.getNodeName());
            NodeList nodeList = doc.getElementsByTagName("Valute");
            String dateOfTransacion = root.getAttribute("Date");
//            System.out.println(dateOfTransacion);

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;

                    String name = eElement.getElementsByTagName("Name").item(0).getTextContent();
                    String value = eElement.getElementsByTagName("Value").item(0).getTextContent().replace(",", ".");
                    BigDecimal preciseValue = new BigDecimal(value);

                    MyCurrency currency = new MyCurrency(name, preciseValue, dateOfTransacion);

                    if (map.containsKey(currency.getName())) {
                        List<MyCurrency> list = map.get(currency.getName());
                        list.add(currency);
                    } else {
                        List<MyCurrency> list = new ArrayList<>(90);
                        list.add(currency);
                        map.put(currency.getName(), list);
                    }

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
