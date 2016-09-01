echo "------------levanto maven-----------------"
mvn clean install
echo "------------buildeando docker-------------"
docker build -t bsa .
echo "------------run docker--------------------"
docker run --link redis:db -t -i -p 8080:8080 bsa
