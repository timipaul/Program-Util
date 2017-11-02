package com.superman.xmlparse;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



public class xmlTest {

	public static void main(String[] args) {
		Map<String, Map<String, String>> xmlResult = XmlParseTool.parseXmlFileByAttribute(new File("E:\\myeclipse2015\\workspace\\common\\xml.txt"));
		//Map<String, Map<String, String>> xmlResult = XmlParseTool.parseXmlFileByText(new File("E:\\myeclipse2015\\workspace\\common\\testxml.txt"));
		Set<String> s = xmlResult.keySet();
		Iterator<String> it = s.iterator();
		String key;
		Map<String, String> value;
		String secondValue, secondeKey;
		while (it.hasNext()) {
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

	}

}
