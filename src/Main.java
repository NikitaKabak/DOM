import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        File inputFile = new File("books.xml");
        System.out.println("имя файла " + inputFile.getName());
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(inputFile);
        NodeList list = doc.getChildNodes();
        System.out.println("Количество нодов: " + list.getLength());
        for (int i = 0; i < list.getLength(); i++) {
            Node childNode = list.item(i);

            System.out.println("название ноды " + childNode.getNodeName());
            if(childNode.getChildNodes().getLength() > 0){
                System.out.println("Количество дочерних нодов у " + childNode.getNodeName() + " = " + childNode.getChildNodes().getLength());

                visit(childNode,1);
            }
        }

    }

    private static void visit(Node childNode,int level) {
        NodeList listChildNodes = childNode.getChildNodes();
        for (int i = 0; i < listChildNodes.getLength(); i++) {
            Node childNoda = listChildNodes.item(i);

            if (childNoda.getChildNodes().getLength() > 1) {
                process(level);
                System.out.print( childNoda.getNodeName() + '\t');

                System.out.println("Количество дочерних нодов у " + childNoda.getNodeName() + " = " + childNoda.getChildNodes().getLength());
                visit(childNoda, level + 1);
            } else {
                if (childNoda instanceof Element) {
                    Element e = (Element) childNoda;
                    process(level);
                    System.out.print(e.getTagName() + '\t');
                    System.out.print(e.getTextContent());
                    System.out.println();
                }
                /*System.out.println("название ноды " + childNoda.getNodeName());
                System.out.println("Количество нодов у " + childNoda.getNodeName() + " = " + childNoda.getChildNodes().getLength());  */

            }
        }
    }

    private static void process(int level) {
        for(int i=0; i < level; i++){
            System.out.print('\t');
        }
    }

}

