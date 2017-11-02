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
 * xml��ʽ���ݵĽ�������
 */
public class XmlParseTool {
	
	private static Map<String, Map<String, String>> xmlMap = new  HashMap<String, Map<String, String>>();
	
	
	/**
	 * �����ļ�����Ϊxml��ʽ(�������Ա�ǩ��������ֵ)���ݵ��ļ�,����map�洢
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
	 * �����ļ�����Ϊxml��ʽ(�������Ա�ǩ��������ֵ)���ݵ��ַ���,����map�洢
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
	 * �����ļ�����Ϊxml��ʽ���ݵ��ļ�,����map�洢
	 * @param xmlFile
	 * @return
	 */
	public static  Map<String, Object> parseXmlFileByText(File xmlFile){
		
		try {
			Document doc = new SAXReader().read(xmlFile);
			Element  rootElement = doc.getRootElement();
			Map<String,Object> contentMap = new HashMap<String,Object>();
			return parseTextXML(rootElement,contentMap);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	/**
	 * �����ļ�����Ϊxml��ʽ���ݵ��ַ���,����map�洢
	 * @param strXml
	 * @return
	 */
	public static  Map<String, Object> parseXmlStringByText(String strXml){
		try {
			Document doc = DocumentHelper.parseText(strXml);
			Element  rootElement = doc.getRootElement();
			Map<String,Object> contentMap = new HashMap<String,Object>();
			return parseTextXML(rootElement,contentMap);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	/**
	 * ������ǩ����
	 * @param ele
	 * @return
	 */
	public static Map<String, Map<String, String>> parseAttributeXML(Element ele) {
		Map<String, String> attributeMap = null;// �洢xml��ǩ���Լ���
		for (Iterator<?> i = ele.elementIterator(); i.hasNext();) {
			Element node = (Element) i.next();
			System.out.println("parseXML node name:" + node.getName());
			// ���xml��ǩ�������Խ���
			if (node.attributes() != null && node.attributes().size() > 0) {
				attributeMap = new HashMap<String, String>();
				for (Iterator<?> j = node.attributeIterator(); j.hasNext();) {
					Attribute item = (Attribute) j.next();
					attributeMap.put(item.getName(), item.getValue());
				}
			}
			xmlMap.put(node.getName(), attributeMap);
			// ��������ĸýڵ��»��б�ǩ��������
			if (node.elementIterator().hasNext()) {
				parseAttributeXML(node);
			}
		}
		return xmlMap;
	}
	
	/**
	 * ������ǩtext
	 * @param ele
	 * @return
	 */
	public static Map<String, Object> parseTextXML(Element ele,Map<String,Object> contentMap) {
		// �洢xml��ǩtext���ݼ���
		Iterator<?> objIterator = ele.elementIterator();
		while(objIterator.hasNext()){
			Element node = (Element) objIterator.next();
			if(node.getText() != null){
				contentMap = new HashMap<String,Object>();
				contentMap.put(node.getName(), node.getText());
			}
			if(node.elementIterator().hasNext()){
				contentMap = new HashMap<String,Object>();
				contentMap = parseTextXML(node,contentMap);
				contentMap.put(node.getName(), contentMap);
			}
		}
		return contentMap;
	}
	
	

}
