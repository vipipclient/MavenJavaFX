package com.almasb.test.reader;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpTranlateRequest {
    String lenguage = "kk-ru";
    public String getLenguage() {
        return lenguage;
    }

    public void getXmlString(){
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse("conf.xml");

            // Получаем корневой элемент
            Node root = document.getDocumentElement();

            System.out.println("List of settings :");
            System.out.println();
            // Просматриваем все подэлементы корневого - т.е. книги
            NodeList books = root.getChildNodes();
            for (int i = 0; i < books.getLength(); i++) {
                Node book = books.item(i);
                // Если нода не текст, то это книга - заходим внутрь
                if (book.getNodeType() != Node.TEXT_NODE) {
                    NodeList bookProps = book.getChildNodes();
                    for(int j = 0; j < bookProps.getLength(); j++) {
                        Node bookProp = bookProps.item(j);
                        // Если нода не текст, то это один из параметров книги - печатаем
                        if (bookProp.getNodeType() != Node.TEXT_NODE) {
                            System.out.println(bookProp.getNodeName() + ":" + bookProp.getChildNodes().item(0).getTextContent());
                        }
                    }
                    System.out.println("===========>>>>");
                }
            }


        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void setLenguage(String lenguage) {
        this.lenguage = lenguage;
    }


    public HttpTranlateRequest(){

    }
    public  String makeTranlation(String wordToTranslate)throws Throwable{
        if ( wordToTranslate.isEmpty())
            return "";


        System.out.println("Продолжаем дальше пирп");
        try{
            String makeYaPost = "https://translate.yandex.net/api/v1/tr.json/translate?id="+((String) com.almasb.test.sample.AppSettings.get("link"))+"-2-0&srv=tr-text&lang=" +
                    lenguage+
                    "&reason=auto&format=text";

            URI dsfs = new URI(makeYaPost);
            URL hp = new URL(dsfs.toASCIIString());
            HttpsURLConnection hpCon = (HttpsURLConnection) hp.openConnection();
            hpCon.setRequestMethod("POST");
            hpCon.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            hpCon.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(hpCon.getOutputStream());
            String postData = "text="+URLEncoder.encode(wordToTranslate,StandardCharsets.UTF_8).replace("+","%20")
                    +"&options=4";

//            System.out.println(postData);
             out.writeBytes(postData);



            Map<String, List<String>> gf = hpCon.getHeaderFields();
            gf.forEach((a,b) -> {
                Iterator<String> sdf = b.listIterator();
                while (sdf.hasNext())
                    System.out.println(String.format("%15s\t%s",a,sdf.next()));

            });
            System.out.println(hpCon.getContent().toString()); ;


            InputStream asd = hpCon.getInputStream();
            byte[] buf = asd.readAllBytes();
            String Sozdik = new String(buf, StandardCharsets.UTF_8);

            JsonNode dsa = new ObjectMapper().readTree(Sozdik);
            JsonNode dsaPath = dsa.findPath("text");

            return dsaPath.get(0).getTextValue();
        }catch (IOException | URISyntaxException e){System.out.println("EROOR");}
        return "";
    }
    public static void main(String args[]) throws Throwable {

        InetAddress Address = InetAddress.getLocalHost();
        System.out.println(Address);
 //       String NameOfServer = "triggur.org";
        String NameOfServer = "mail.ru";
        Address = InetAddress.getByName(NameOfServer);
        System.out.println(Address);
        int ContentLength = 0;
        int cnt = 10;
        ArrayList<Byte> gif = new ArrayList();
        StringBuffer bufa = new StringBuffer();



       try{
           String troubleYa =    "https://translate.yandex.net/api/v1/tr.json/translate?id=ee833c16.5f130edf.01be22ae.74722d74657874-2-0&srv=tr-text&lang=kk-ru&reason=auto&format=text";
           URI dsfs = new URI(troubleYa);
           URL hp = new URL(dsfs.toASCIIString());


           System.out.println(dsfs.toASCIIString());


           HttpsURLConnection hpCon = (HttpsURLConnection) hp.openConnection();
           hpCon.setRequestMethod("POST");
           hpCon.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
           hpCon.setDoOutput(true);
           DataOutputStream out = new DataOutputStream(hpCon.getOutputStream());
           String toTranslatetxt = "шындықпен";
           String postData = "text="+URLEncoder.encode(toTranslatetxt,StandardCharsets.UTF_8).replace("+","%20")
                   +"&options=4";

           System.out.println(postData);
           out.writeBytes(postData);

           System.out.println("HttpURLConnection hpCon = (HttpURLConnection) hp.openConnection();");
///

           ContentLength = hpCon.getContentLength();
            Map<String, List<String>> gf = hpCon.getHeaderFields();
            gf.forEach((a,b) -> {
                Iterator<String> sdf = b.listIterator();
                while (sdf.hasNext())
                    System.out.println(String.format("%15s\t%s",a,sdf.next()));

           });
           System.out.println(hpCon.getContent().toString()); ;


           InputStream asd = hpCon.getInputStream();
           byte[] buf = asd.readAllBytes();
           String Sozdik = new String(buf, StandardCharsets.UTF_8);

           System.out.println(Sozdik);

           JsonNode dsa = new ObjectMapper().readTree(Sozdik);
           JsonNode dsaPath = dsa.findPath("text");
           dsaPath.forEach(element->{
               System.out.println(element.getTextValue());
               ;
           });
           System.out.println();

           System.exit(0);


           JsonNode translation = dsa.findPath("translation");
           JsonNode examples = dsa.findPath("examples");
           System.out.println(translation.findValue("text").getTextValue()) ;
           examples.forEach(exmpl->{
               System.out.println("*********************");
               System.out.println(exmpl.findValue("src").getTextValue());
               System.out.println(exmpl.findValue("dst").getTextValue());
           });

           System.exit(1);


        }catch (IOException | URISyntaxException e){System.out.println("EROOR");}

        try (FileWriter fout = new FileWriter("page.html")){

            fout.write(bufa.toString());
        }catch (IOException e){System.out.println("Ошыбка ,братан: ");}
        FileOutputStream f0 = null;
        try {
            f0 = new FileOutputStream("sdf.html");

            byte[] gifbuff = new byte[gif.size()];
            int i = 0;
            Iterator GifIt =  gif.iterator();
            while (GifIt.hasNext())
                {gifbuff[i] = (byte) GifIt.next();i++;}
            f0.write(gifbuff);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
