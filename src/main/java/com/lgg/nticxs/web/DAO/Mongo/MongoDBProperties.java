package com.lgg.nticxs.web.DAO.Mongo;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import coolsense.movasim.persistence.util.PropertiesFile;

abstract class MongoDBProperties {
	
	private static final String HOST_MONGODB = PropertiesFile.getInstance().getHostMongoDB();
	private static final Integer PORT_MONGODB = PropertiesFile.getInstance().getPortMongoDB();
	private static final Boolean USE_SSL_MONGODB = PropertiesFile.getInstance().getUseSSLMongoDB();
	
	private static final String USER_MONGODB = PropertiesFile.getInstance().getUserMongoDB();
	private static final String PASSWORD_MONGODB = PropertiesFile.getInstance().getPasswordMongoDB();
	
	protected abstract String getDatabaseName();
	
	protected MongoClient getMongoClient() {
		MongoCredential mongoCredential = getMongoCredential();
		return new MongoClient(getServerAddress(), mongoCredential, getMongoClientOptions());
	}
	
	private List<ServerAddress> getServerAddress() {
		ServerAddress serverAddress = new ServerAddress(HOST_MONGODB, PORT_MONGODB);
		
		List<ServerAddress> listServerAddress = new ArrayList<ServerAddress>();
		listServerAddress.add(serverAddress);
		
		return listServerAddress;
	}
	
	private MongoClientOptions getMongoClientOptions() {
		Builder builder = new Builder();
		builder.sslEnabled(USE_SSL_MONGODB);
		MongoClientOptions mongoOptions = builder.build();
		return mongoOptions;
	}
	
	private MongoCredential getMongoCredential() {
		return MongoCredential.createScramSha1Credential(USER_MONGODB, getDatabaseName(), PASSWORD_MONGODB.toCharArray());
	}
}
