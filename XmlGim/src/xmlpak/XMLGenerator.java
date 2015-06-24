package xmlpak;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
 
public class XMLGenerator {
	private int accion;
	private String archivo;
	private ArrayList indice, valores;
 
	
    public XMLGenerator(int accion, String archivo, ArrayList<?> indice, ArrayList<?> valores) {
        accion  = this.accion;
    	archivo = this.archivo;
        indice  = this.indice;
        valores = this.valores;
         
        try {
        	switch (accion)
        	{            
        	case 1:
        		crearUsuario(archivo, indice, valores);
        		break;
        	default:
        		crear(archivo, indice, valores);
        		break;        			
        	}
        } catch (Exception e) {}
    }
 
    public static void crearUsuario(String archivo, ArrayList<Usuario> indice,ArrayList<Usuario> valores) throws Exception{
 
        if(indice.isEmpty() || valores.isEmpty() || indice.size()!=valores.size())
        {
            System.out.println("ERROR empty ArrayList");
            return;
        } else {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, archivo, null);
            document.setXmlVersion("1.0");
 
            //Main Node
            Element raiz = document.getDocumentElement();
            //Por cada key creamos un item que contendrá la key y el value
            for(int i=0; i<indice.size();i++){
                //Item Node
                Element itemNode = document.createElement("ITEM"); 
                //Key Node
                Element keyNode = document.createElement("KEY"); 
                Text nodeKeyValue = document.createTextNode(indice.get(i));
                keyNode.appendChild(nodeKeyValue);      
                //Value Node
                Element valueNode = document.createElement("VALUE"); 
                Text nodeValueValue = document.createTextNode(value.get(i));                
                valueNode.appendChild(nodeValueValue);
                //append keyNode and valueNode to itemNode
                itemNode.appendChild(keyNode);
                itemNode.appendChild(valueNode);
                //append itemNode to raiz
                raiz.appendChild(itemNode); //pegamos el elemento a la raiz "Documento"
            }                
            //Generate XML
            Source source = new DOMSource(document);
            //Indicamos donde lo queremos almacenar
            Result result = new StreamResult(new java.io.File(name+".xml")); //nombre del archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        }
    }
 
}
