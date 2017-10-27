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
	 * mongo客户端操作对象
	 */
	private static MongoClient mongoClient = null;
	
	/**
	 * 数据库对象
	 */
	private static MongoDatabase mongoDatabase = null;
	
	
	/**
	 * 无需用户名密码验证
	 */
	public static void connectionWithoutAccount(String addressIp,Integer port,String database) {
		try {
			// 连接mongodb服务
			mongoClient = new MongoClient(addressIp, port);
			
			// 连接数据库
			mongoDatabase = mongoClient.getDatabase(database);
			System.out.println("Connection to database Successfuly");
		} catch (Exception e) {
			System.out.println("ExceptionName"+e.getClass().getName()+"ExceptionInfo---"+e.getMessage());
		}

	}
	
	/**
	 * 需用户名密码验证
	 */
	public static void connectionWithAccount(String addressIp,Integer port,String userName,String database,String password){
		try{
			//连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址  
	        //ServerAddress()两个参数分别为 服务器地址 和 端口  
			ServerAddress serverAddress = new ServerAddress(addressIp, port);
			List<ServerAddress> addrs = new ArrayList<ServerAddress>();
			addrs.add(serverAddress);
			
			//MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
			MongoCredential crednetial = MongoCredential.createCredential(userName, database, password.toCharArray());
			List<MongoCredential> credentials = new ArrayList<MongoCredential>();  
	        credentials.add(crednetial); 
	        
	        //通过连接认证获取MongoDB连接  
	        mongoClient = new MongoClient(addrs,credentials); 
	        //连接到数据库  
            mongoDatabase = mongoClient.getDatabase("databaseName");  
	        System.out.println("Connection to database Successfuly");
		}catch(Exception e){
			System.out.println("ExceptionName"+e.getClass().getName()+"ExceptionInfo---"+e.getMessage());
		}
        
	}
	
	/**
	 * 创建集合
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
	 * 获取集合
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
	 * 删除集合
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
	 * 根据集合名插入多个文档
	 * @param collectionName
	 * @param objList
	 * @param insertOptions 插入选项
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
	 * 根据集合名插入单个文档
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
	 * 删除文档
	 * @param collectionName
	 * @param OneOrMany(true表示删除符合条件的第一个文档，false表示删除符合条件的所有文档)
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
	 * 检索集合中所有文档
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
	 * 得到集合中的文档总数
	 * @param collectionName
	 * @return
	 */
	public static Long queryDocumentCounts(String collectionName){
		long documetCounts = 0;
		try{
			MongoCollection<Document> mongoCollection = getCollection(collectionName);
			documetCounts = mongoCollection.count();
			System.out.println("集合---"+collectionName+"---文档总数为："+documetCounts);
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
