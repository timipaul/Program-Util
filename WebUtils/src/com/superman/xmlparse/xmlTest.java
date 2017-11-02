package com.superman.xmlparse;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class xmlTest {

	public static void main(String[] args) {
		//Map<String, Map<String, String>> xmlResult = XmlParseTool.parseXmlFileByAttribute(new File("E:\\myeclipse2015\\workspace\\common\\xml.txt"));
		Map<String, Object> xmlResult = XmlParseTool.parseXmlFileByText(new File("E:\\myeclipse2015\\workspace\\common\\testxml.txt"));
		Set<String> s = xmlResult.keySet();
		Iterator<String> it = s.iterator();
		String key;
		Object value;
		String secondValue, secondeKey;
		/*while (it.hasNext()) {
			key = it.next();
			value = xmlResult.get(key);
			System.out.println(key + "¼ü");
			if(value != null){
				for (Map.Entry<String, String> entry : value.entrySet()) {
					secondeKey = entry.getKey();
					secondValue = entry.getValue();
					System.out.println(secondeKey + ":\t" + secondValue);
				}
			}
			
		}
		*/
		/*Document doc;
		try {
			doc = new SAXReader().read(new File("E:\\myeclipse2015\\workspace\\common\\TestXml.xml"));
			Element  rootElement = doc.getRootElement();
			List<Element> le = rootElement.elements();
			Map<String,Object> ss = new HashMap<String,Object>();
			for(Element e:le){
				System.out.println(e.getName());
				System.out.println(e.elements().size());
				if(e.elements().size()>0){
					List<Element> ls = e.elements();
					for(Element es:ls){
						ss.put(es.getName(), es.getText());
					}
					ss.put(e.getName(), ss);
				}
			}
			
			Set<String> s = ss.keySet();
			Iterator<String> it = s.iterator();
			String key;
			Object value;
			Object secondValue;
			String secondeKey;
			while (it.hasNext()) {
				key = it.next();
				value = ss.get(key);
				System.out.println(key + "¼ü");
				if(value != null){
					for (Entry<String, Object> entry : ((Map<String, Object>) value).entrySet()) {
						secondeKey = entry.getKey();
						secondValue = entry.getValue();
						System.out.println(secondeKey + ":\t" + secondValue);
					}
				}
				
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		

	}

}
