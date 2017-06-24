* You can build all the projects by running the './build-all-projects.bat' on Windows systems and then going to each individual folder and running the 
jars using the 'java -jar build/libs/<application_name>.jar' command.        
* Run the individual images as below; note that the auth-server and api-gateway images fail because of Spring Boot 1.2.x bug.
    * Config Server
        * docker run -d --name config-server -p 8888:8888 electronic-insurance-policy/config-server
        * docker logs -f config-server
    * Eureka Server
        * docker run -d --name registry-server -p 8761:8761 electronic-insurance-policy/webservice-registry
        * docker logs -f registry-server
    * OAuth Server
        * docker run -d --name auth-server -p 8899:8899 electronic-insurance-policy/auth-server
        * docker logs -f auth-server
    * Customer Webservice    
        * docker run -d --name customer-webservice electronic-insurance-policy/customer-webservice
        * docker logs -f customer-webservice
    * Internal Webservice    
        * docker run -d --name internal-webservice electronic-insurance-policy/internal-webservice
        * docker logs -f internal-webservice
    * Agent Webservice    
        * docker run -d --name agent-webservice electronic-insurance-policy/agent-webservice
        * docker logs -f agent-webservice
	* Payment Webservice    
        * docker run -d --name payment-webservice electronic-insurance-policy/payment-webservice
        * docker logs -f payment-webservice
	* Auditing Webservice    
        * docker run -d --name auditing-webservice electronic-insurance-policy/auditing-webservice
        * docker logs -f auditing-webservice
	* Reporting Webservice    
        * docker run -d --name reporting-webservice electronic-insurance-policy/reporting-webservice
        * docker logs -f reporting-webservice
	* Alert Notification Webservice    
        * docker run -d --name notification-webservice electronic-insurance-policy/notification-webservice
        * docker logs -f notification-webservice
	* Insurance policy Webservice    
        * docker run -d --name insurance-policy-webservice electronic-insurance-policy/insurance-policy-webservice
        * docker logs -f insurance-policy-webservice
    * Internal Web Portal    
        * docker run -d --name internal-web-portal electronic-insurance-policy/internal-web-portal
        * docker logs -f internal-web-portal
	* Agent Web Portal    
        * docker run -d --name agent-web-portal electronic-insurance-policy/agent-web-portal
        * docker logs -f agent-web-portal
	* Customer Web Portal    
        * docker run -d --name customer-web-portal electronic-insurance-policy/customer-web-portal
        * docker logs -f customer-web-portal
    * Zuul API Gateway    
        * docker run -d --name api-gateway -p 8080:8080 electronic-insurance-policy/api-gateway
        * docker logs -f api-gateway

* There is also have a [docker-compose](https://docs.docker.com/compose/) file that can be used to start all 
the containers together using 'docker-compose up -d'. However this doesn't work in our case since our containers need 
to be started in order e.g. config-server before everything, webservice-registry before rest of Eureka clients. 
Docker compose starts all containers together and this fails because containers like auth-server, web-portal start 
before their dependent containers have started.s        

* Note:
    * If the gradle wrapper doesn't work, then install gradle and run 'gradle wrapper' before using 'gradlew'.
    * If you need to setup the classpath correctly, then run './gradlew clean build eclipse' which would setup the 
	'.classpath' accordingly.