echo "Stopping tomcat..."
./devtools/apache-tomcat-7.0.28/bin/shutdown.sh 

echo "Stopping Mongo DB..."
mongo admin  --eval "printjson(db.shutdownServer())"

