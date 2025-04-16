# Easy Run Script for JAX-RS-Bookstore
# run - ./run.sh
# stop - /Applications/apache-tomcat-11.0.6/bin/shutdown.sh

# Tomcat installation directory 
TOMCAT_HOME=/Applications/apache-tomcat-11.0.6

# Build the project 
mvn clean package

# Copy the WAR file to Tomcat's webapps directory
cp target/JAX-RS-Bookstore.war $TOMCAT_HOME/webapps/bookstore.war

# Start Tomcat (if not already running)
$TOMCAT_HOME/bin/startup.sh

echo ""
echo "Application deployed!"
echo "REST API endpoints available at:"
echo "http://localhost:8080/bookstore/"
echo ""
echo "Use the following command to stop Tomcat when finished:"
echo "$TOMCAT_HOME/bin/shutdown.sh"