echo "Starting tomcat..."
./devtools/apache-tomcat-7.0.28/bin/startup.sh 

echo "Build & Deploy application..."
cd code
mvn tomcat7:deploy
cd ..

echo "Starting Mongo DB..."
mkdir db
mkdir logs
mongod --fork --dbpath ./db/ --logpath ./logs/mongod.log

echo "Opening in browser ..."
open http://localhost:8080/placement &

