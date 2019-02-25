package com.era.abs.api.xml.data;
 
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author roman
 */
public class XMLDataBuild {
    public String getXMLData(long accountNo){
        Element root = new Element("BALANCE_INQUIRY");
        Document doc = new Document();

        Element child1 = new Element("USER_ID");
        child1.addContent("NRB");
        Element child2 = new Element("USER_PASS");
        child2.addContent("NRB$ERA#WBS@");
        Element child3 = new Element("TOKEN_NO");
        child3.addContent("1");
        Element child4 = new Element("REQ_NO");
        child4.addContent("71");
        Element child5 = new Element("AC_NO");
        child5.addContent(String.valueOf(accountNo));

        root.addContent(child1);
        root.addContent(child2);
        root.addContent(child3);
        root.addContent(child4);
        root.addContent(child5);

        doc.setRootElement(root);

        XMLOutputter outter = new XMLOutputter();
        outter.setFormat(Format.getCompactFormat().setOmitDeclaration(true));
        String xml = outter.outputString(doc); 
        return xml;
    }

    public static void main(String[] args) {  
        XMLDataBuild xmlDataBuild = new XMLDataBuild();
        System.out.println(xmlDataBuild.getXMLData(9982990000001L));
    }
}
