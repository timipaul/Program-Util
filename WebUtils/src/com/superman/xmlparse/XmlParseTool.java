package com.superman.xmlparse;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
 * @author Ningsc(supermanNingsc)
 * xml格式数据的解析工具
 */
public class XmlParseTool {
	
	private static Map<String, Map<String, String>> xmlMap = new  HashMap<String, Map<String, String>>();
	
	private static Map<String, Object> xmlMapText = new  HashMap<String, Object>();
	
	/**
	 * 解析文件内容为xml格式(数据是以标签属性来赋值)数据的文件,并以map存储
	 * @param xmlFile
	 * @return
	 */
	public static  Map<String, Map<String, String>> parseXmlFileByAttribute(File xmlFile){
		
		try {
			Document doc = new SAXReader().read(xmlFile);
			Element  rootElement = doc.getRootElement();
			return parseAttributeXML(rootElement);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	/**
	 * 解析文件内容为xml格式(数据是以标签属性来赋值)数据的字符串,并以map存储
	 * @param strXml
	 * @return
	 */
	public static  Map<String, Map<String, String>> parseXmlStringByAttribute(String strXml){
		
		try {
			Document doc = DocumentHelper.parseText(strXml);
			Element  rootElement = doc.getRootElement();
			return parseAttributeXML(rootElement);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	/**
	 * 解析文件内容为xml格式数据的文件,并以map存储
	 * @param xmlFile
	 * @return
	 */
	public static  Map<String, Object> parseXmlFileByText(File xmlFile){
		
		try {
			Document doc = new SAXReader().read(xmlFile);
			Element  rootElement = doc.getRootElement();
			return parseTextXML(rootElement);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	/**
	 * 解析文件内容为xml格式数据的字符串,并以map存储
	 * @param strXml
	 * @return
	 */
	public static  Map<String, Object> parseXmlStringByText(String strXml){
		try {
			Document doc = DocumentHelper.parseText(strXml);
			Element  rootElement = doc.getRootElement();
			return parseTextXML(rootElement);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	/**
	 * 解析标签属性
	 * @param ele
	 * @return
	 */
	public static Map<String, Map<String, String>> parseAttributeXML(Element ele) {
		Map<String, String> attributeMap = null;// 存储xml标签属性集合
		for (Iterator<?> i = ele.elementIterator(); i.hasNext();) {
			Element node = (Element) i.next();
			System.out.println("parseXML node name:" + node.getName());
			// 如果xml标签中有属性解析
			if (node.attributes() != null && node.attributes().size() > 0) {
				attributeMap = new HashMap<String, String>();
				for (Iterator<?> j = node.attributeIterator(); j.hasNext();) {
					Attribute item = (Attribute) j.next();
					attributeMap.put(item.getName(), item.getValue());
				}
			}
			xmlMap.put(node.getName(), attributeMap);
			// 如果解析的该节点下还有标签继续解析
			if (node.elementIterator().hasNext()) {
				parseAttributeXML(node);
			}
		}
		return xmlMap;
	}
	
	/**
	 * 解析标签text
	 * @param ele
	 * @return
	 */
	public static Map<String, Object> parseTextXML(Element ele) {
		Map<String, Object> contentMap =  new HashMap<String, Object>();;// 存储xml标签text内容集合
		for (Iterator<?> i = ele.elementIterator(); i.hasNext();) {
			Element node = (Element) i.next();
			System.out.println("parseXML node name:" + node.getName());
			// 如果xml标签中文本内容解析
			if (node.elementIterator().hasNext() == false  && node.getText().length() > 0) {
				contentMap.put(node.getName(), node.getText());
			}
			// 如果解析的该节点下还有标签继续解析
			if (node.elementIterator().hasNext()) {
				parseTextXML(node);
			}
		}
		return null;
	}
	
	public static Map<String,String> deepParseXml(Element ele){
		Map<String, String> contentMap =  new HashMap<String, String>();;// 存储xml标签text内容集合
		for (Iterator<?> i = ele.elementIterator(); i.hasNext();) {
			Element node = (Element) i.next();
			System.out.println("parseXML node name:" + node.getName());
			// 如果xml标签中文本内容解析
			if (node.elementIterator().hasNext() == false  && node.getText().length() > 0) {
				
				contentMap.put(node.getName(), node.getText());
			}	
		}
		return contentMap;
	}

}
