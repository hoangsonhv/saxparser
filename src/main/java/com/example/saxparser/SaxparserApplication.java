package com.example.saxparser;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SaxparserApplication {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            Document doc = builder.parse("https://vnexpress.net/rss/giai-tri.rss");
            NodeList nList = doc.getElementsByTagName("item");
            List<Article> articleList = new ArrayList<>();
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String title = eElement
                            .getElementsByTagName("title")
                            .item(0)
                            .getTextContent();
                    String publicDate = eElement
                            .getElementsByTagName("pubDate")
                            .item(0)
                            .getTextContent();
                    String link = eElement
                            .getElementsByTagName("link")
                            .item(0)
                            .getTextContent();
                    Article article = new Article(title, publicDate, link);
                    articleList.add(article);
                }
            }
            System.out.println(articleList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
