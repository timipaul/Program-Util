package com.superman.database.MongoDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.model.InsertOneOptions;

public class MongoDBJDBC {
	
	/**
	 * mongo�ͻ��˲�������
	 */
	private static MongoClient mongoClient = null;
	
	/**
	 * ���ݿ����
	 */
	private static MongoDatabase mongoDatabase = null;
	
	
	/**
	 * �����û���������֤
	 */
	public static void connectionWithoutAccount(String addressIp,Integer port,String database) {
		try {
			// ����mongodb����
			mongoClient = new MongoClient(addressIp, port);
			
			// �������ݿ�
			mongoDatabase = mongoClient.getDatabase(database);
			System.out.println("Connection to database Successfuly");
		} catch (Exception e) {
			System.out.println("ExceptionName"+e.getClass().getName()+"ExceptionInfo---"+e.getMessage());
		}

	}
	
	/**
	 * ���û���������֤
	 */
	public static void connectionWithAccount(String addressIp,Integer port,String userName,String database,String password){
		try{
			//���ӵ�MongoDB���� �����Զ�����ӿ����滻��localhost��Ϊ����������IP��ַ  
	        //ServerAddress()���������ֱ�Ϊ ��������ַ �� �˿�  
			ServerAddress serverAddress = new ServerAddress(addressIp, port);
			List<ServerAddress> addrs = new ArrayList<ServerAddress>();
			addrs.add(serverAddress);
			
			//MongoCredential.createScramSha1Credential()���������ֱ�Ϊ �û��� ���ݿ����� ����
			MongoCredential crednetial = MongoCredential.createCredential(userName, database, password.toCharArray());
			List<MongoCredential> credentials = new ArrayList<MongoCredential>();  
	        credentials.add(crednetial); 
	        
	        //ͨ��������֤��ȡMongoDB����  
	        mongoClient = new MongoClient(addrs,credentials); 
	        //���ӵ����ݿ�  
            mongoDatabase = mongoClient.getDatabase("databaseName");  
	        System.out.println("Connection to database Successfuly");
		}catch(Exception e){
			System.out.println("ExceptionName"+e.getClass().getName()+"ExceptionInfo---"+e.getMessage());
		}
        
	}
	
	/**
	 * ��������
	 */
	public static void createCollection(String collectionName){
		try{
			mongoDatabase.createCollection(collectionName);
			System.out.println("creates collection  successfuly");
		}catch(Exception e){
			System.out.println("ExceptionName"+e.getClass().getName()+"ExceptionInfo---"+e.getMessage());
		}
		
	}
	
	/**
	 * ��ȡ����
	 * @param collectionName
	 * @return
	 */
	public static MongoCollection<Document> getCollection(String collectionName){
		MongoCollection<Document> mongoCollection = null;
		try{
			mongoCollection = mongoDatabase.getCollection(collectionName);
			System.out.println("get collection  successfuly");
		}catch(Exception e){
			System.out.println("ExceptionName"+e.getClass().getName()+"ExceptionInfo---"+e.getMessage());
		}
		return mongoCollection;
		
	}
	
	/**
	 * ɾ������
	 * @param collectionName
	 */
	public static void deleteCollection(String collectionName){
		try{
			mongoDatabase.getCollection(collectionName).drop();
			System.out.println("drop collection  successfuly");
		}catch(Exception e){
			System.out.println("ExceptionName"+e.getClass().getName()+"ExceptionInfo---"+e.getMessage());
		}
	}
	
	/**
	 * ���ݼ������������ĵ�
	 * @param collectionName
	 * @param objList
	 * @param insertOptions ����ѡ��
	 */
	public static void insertDocuments(String collectionName,List<Document> objList,InsertManyOptions insertOptions){
		try{
			MongoCollection<Document> mongoCollection = getCollection(collectionName);
			if(insertOptions != null ){
				mongoCollection.insertMany(objList, insertOptions);
			}else{
				mongoCollection.insertMany(objList);
			}
			System.out.println("insert Documents  successfuly");
		}catch(Exception e){
			System.out.println("ExceptionName"+e.getClass().getName()+"ExceptionInfo---"+e.getMessage());
		}
		
	}
	
	/**
	 * ���ݼ��������뵥���ĵ�
	 * @param collectionName
	 * @param document
	 * @param insertOptions
	 */
	public static void insertDocument(String collectionName,Document document,InsertOneOptions insertOptions){
		try{
			MongoCollection<Document> mongoCollection = getCollection(collectionName);
			System.out.println(mongoCollection.toString());
			if(insertOptions != null ){
				mongoCollection.insertOne(document, insertOptions);
			}else{
				mongoCollection.insertOne(document);
			}
			System.out.println("insert Document  successfuly");
		}catch(Exception e){
			System.out.println("ExceptionName"+e.getClass().getName()+"ExceptionInfo---"+e.getMessage());
		}
	}
	
	/**
	 * ɾ���ĵ�
	 * @param collectionName
	 * @param OneOrMany(true��ʾɾ�����������ĵ�һ���ĵ���false��ʾɾ�����������������ĵ�)
	 * @param bson
	 */
	public static void deleteDocument(String collectionName,Boolean OneOrMany,Bson bson){
		try{
			MongoCollection<Document> mongoCollection = getCollection(collectionName);
			if(OneOrMany == true){
				mongoCollection.deleteOne(bson);
			}else if(OneOrMany == false){
				mongoCollection.deleteMany(bson);
			}
			
		}catch(Exception e){
			System.out.println("ExceptionName"+e.getClass().getName()+"ExceptionInfo---"+e.getMessage());
		}
	}
	
	/**
	 * ���������������ĵ�
	 * @param collectionName
	 * @return
	 */
	public static MongoCursor<Document> findDocument(String collectionName){
		MongoCursor<Document> mongoCursor = null;
		try{
			MongoCollection<Document> mongoCollection = getCollection(collectionName);
			FindIterable<Document> findIterable = mongoCollection.find();
			mongoCursor = findIterable.iterator();
			System.out.println("find All Document  successfuly");
		}catch(Exception e){
			System.out.println("ExceptionName"+e.getClass().getName()+"ExceptionInfo---"+e.getMessage());
		}
		return mongoCursor;	
	}
	
	/**
	 * �õ������е��ĵ�����
	 * @param collectionName
	 * @return
	 */
	public static Long queryDocumentCounts(String collectionName){
		long documetCounts = 0;
		try{
			MongoCollection<Document> mongoCollection = getCollection(collectionName);
			documetCounts = mongoCollection.count();
			System.out.println("����---"+collectionName+"---�ĵ�����Ϊ��"+documetCounts);
		}catch(Exception e){
			System.out.println("ExceptionName"+e.getClass().getName()+"ExceptionInfo---"+e.getMessage());
		}
		return documetCounts;
		
	}
	
	
	public static void main(String[] args) {
		MongoDBJDBC.connectionWithoutAccount("localhost",27017,"superman");
		/*Map<String, Object> linkHashMap =new  HashMap<String,Object>();
		linkHashMap.put("titile", "mongoDB");
		Document document = new Document(linkHashMap); 
		MongoDBJDBC.insertDocument("superman", document, null);*/
		MongoDBJDBC.deleteDocument("superman", true, new Document("title","Mongodb"));
		MongoCursor<Document> mongoCursor = MongoDBJDBC.findDocument("superman");
		MongoDBJDBC.queryDocumentCounts("superman");
		while(mongoCursor.hasNext()){
			System.out.println(mongoCursor.next());
		}

	}

}
