package com.mongodb.test;


import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoDB {
	
	static MongoClient mongoClient = new MongoClient( "10.211.55.51" , 27017 );
	public static void main(String[] args) {
		 MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol"); 
		// mongoDatabase.createCollection("test");
		 MongoCollection<Document> collection = mongoDatabase.getCollection("test");
		 delete(collection,"title","数据");
		 FindIterable<Document> findIterable = collection.find(Filters.eq("title", "MongoDB"));  
         MongoCursor<Document> mongoCursor = findIterable.iterator();  
         while(mongoCursor.hasNext()){  
            System.out.println(mongoCursor.next());  
         }  
	}
	
	
	public static void insert( MongoCollection<Document> collection,String key,Object value){
		Document document = new Document(key, value);
		 List<Document> documents = new ArrayList<Document>();  
         documents.add(document);  
         collection.insertMany(documents); 
	}
	
	public static void database(){
		List<String> dataBase= mongoClient.getDatabaseNames();
		for (String string : dataBase) {
			System.out.println(string);
		}
	}
	
	public static void delete(MongoCollection<Document> collection,String key,Object value){
		collection.deleteMany(Filters.eq(key, value));  
	}
	
	
	public static void update(MongoCollection<Document> collection,String oldKey,Object oldValue,String newKey,Object newValue){
		 collection.updateMany(Filters.eq(oldKey, oldValue), new Document("$set",new Document(newKey,newValue)));  
	}
}
