# SP
	a server prototype combine Spring Boot 2.1 and Dubbo

## Framework:
	Spring Boot 2.1 + Dubbo + ZooKeeper + MySQL + Redis
	
## System requirements:
	JDK 8+  
	Maven 3.3+
	RocketMQ 4.4.0
	ZooKeeper 3.5.5

## Modules:
	There are 5 modules in total.  
	sp-api: service interfaces  
	sp-common: frequently used util classes and constants in RPC  
	sp-dao: MyBatis mappers, models and plugins  
	sp-service: Dubbo provider, implementation of service interfaces, RocketMQ util  
	sp-web: Dubbo consumer, controllers for http RESTful  

## distribution environment:  
	see the application*.yml in sp-web and sp-service for reference.  
	1.dev(default): used for developing and testing  
	2.prod: used for production  
	If you want to make a package for production environment, you should launch the project through following command:
                  nohup java -jar sp-service-1.0.0-SNAPSHOT.jar --spring.profiles.active=prod &  
                  nohup java -jar sp-web-1.0.0-SNAPSHOT.jar --spring.profiles.active=prod &  

## auto-generate MyBatis mappers, interfaces and models
	The project contains a plugin "mybatis-generator", which could generate MyBatis mappers, interfaces and models with a simple few clicks.
	Suppose you want to add those files according to the MySQL table "demo".   
	First of all, modifying the generatorConfig.xml through adding the following line in the area of tag <context id="MysqlContext">.  
	
		<table tableName="demo" domainObjectName="Demo"><property name="useActualColumnNames" value="false" /></table>  
		
	Then, click the idea Maven side-bar on the right, find the plugin and right click it, then select "Run Maven Build".     	   
	
	Then those tedius mapper files could be auto-generated.     

## How to launch:   
	1.switch to the root directory of the project SP/, run    
	   	   mvn -U clean install -DskipTests  
	2.switch to sp-service/target, enter the following text in the command line  
                   nohup java -jar sp-service-1.0.0-SNAPSHOT.jar &  
	3.switch to sp-web/target, enter the following text in the command line  
                   nohup java -jar sp-web-1.0.0-SNAPSHOT.jar &  
	4. then you can send http RESTful request from Restlet or Postman.  
