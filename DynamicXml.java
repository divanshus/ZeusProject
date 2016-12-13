
import java.io.File;
import java.io.PrintStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DynamicXml {
    public static void main(String[] args) {
        try {
            String projectName = args[0];
            String moduleName = args[1];
            String packageName = args[2];
            String instanceName = args[3];
            String browser = args[4];
            String testcases = args[5];
            if (browser.equalsIgnoreCase("IE")) {
                browser = "Internet Explorer";
            }
            String xmlFilePath = "xml\\" + moduleName + ".xml";
            System.out.println("XmlFilePath is" + xmlFilePath);
            String[] test = testcases.split(",");
            int count1 = test.length;
            String count2 = String.valueOf(count1);
            System.out.println("Count is" + count1);
            StringBuffer sBuffer = new StringBuffer("");
            int i = 0;
            while (i < test.length) {
                sBuffer.append(String.valueOf(test[i]) + "=Windows 7||" + browser + ",");
                ++i;
            }
            System.out.println("String value is" + sBuffer);
            String value1 = sBuffer.toString();
            String newValue = value1.substring(0, value1.length() - 1);
            System.out.println("New value String is" + newValue);
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement("suite");
            document.appendChild(root);
            Attr attr = document.createAttribute("name");
            attr.setValue(projectName);
            root.setAttributeNode(attr);
            Attr attr1 = document.createAttribute("thread-count");
            attr1.setValue(count2);
            root.setAttributeNode(attr1);
            Element parameter = document.createElement("parameter");
            root.appendChild(parameter);
            Attr attr2 = document.createAttribute("name");
            attr2.setValue("prjName");
            parameter.setAttributeNode(attr2);
            Attr attr3 = document.createAttribute("value");
            attr3.setValue(projectName);
            parameter.setAttributeNode(attr3);
            Element parameter1 = document.createElement("parameter");
            root.appendChild(parameter1);
            Attr attr4 = document.createAttribute("name");
            attr4.setValue("instanceName");
            parameter1.setAttributeNode(attr4);
            Attr attr5 = document.createAttribute("value");
            attr5.setValue(instanceName);
            parameter1.setAttributeNode(attr5);
            Element parameter2 = document.createElement("parameter");
            root.appendChild(parameter2);
            Attr attr6 = document.createAttribute("name");
            attr6.setValue("sauceUser");
            parameter2.setAttributeNode(attr6);
            Attr attr7 = document.createAttribute("value");
            attr7.setValue("Local");
            parameter2.setAttributeNode(attr7);
            Element parameter4 = document.createElement("parameter");
            root.appendChild(parameter4);
            Attr attr8 = document.createAttribute("name");
            attr8.setValue("testSetName");
            parameter4.setAttributeNode(attr8);
            Attr attr9 = document.createAttribute("value");
            attr9.setValue(moduleName);
            parameter4.setAttributeNode(attr9);
            Element parameter5 = document.createElement("test");
            root.appendChild(parameter5);
            Attr attr10 = document.createAttribute("name");
            attr10.setValue(packageName);
            parameter5.setAttributeNode(attr10);
            Element parameter6 = document.createElement("parameter");
            parameter5.appendChild(parameter6);
            Attr attr11 = document.createAttribute("name");
            attr11.setValue("testEnvironment");
            parameter6.setAttributeNode(attr11);
            Attr attr12 = document.createAttribute("value");
            attr12.setValue(newValue);
            parameter6.setAttributeNode(attr12);
            Element parameter7 = document.createElement("parameter");
            parameter5.appendChild(parameter7);
            Attr attr13 = document.createAttribute("name");
            attr13.setValue("moduleName");
            parameter7.setAttributeNode(attr13);
            Attr attr14 = document.createAttribute("value");
            attr14.setValue(packageName);
            parameter7.setAttributeNode(attr14);
            Element parameter8 = document.createElement("classes");
            parameter5.appendChild(parameter8);
            int j = 0;
            while (j < test.length) {
                Element parameter9 = document.createElement("class");
                parameter8.appendChild(parameter9);
                Attr attr2j = document.createAttribute("name");
                attr2j.setValue("scripts." + packageName + "." + test[j]);
                parameter9.setAttributeNode(attr2j);
                ++j;
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            System.out.println("New xml File path is" + xmlFilePath);
            StreamResult streamResult = new StreamResult(new File("", xmlFilePath));
            transformer.transform(domSource, streamResult);
            System.out.println("Done creating XML File");
        }
        catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
        catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}