# jersey-example

This is just a hacked together version of jersey's maven archetype to test my template files with.

# all in one build command
mvn clean install exec:java

# get all
curl -v -X GET 'http://localhost:8080/myapp/api/todo'

# create
curl -v -X POST -H 'Content-Type: application/json' -d '{"description": "test1"}' 'http://localhost:8080/myapp/api/todo'

# update
curl -v -X POST -H 'Content-Type: application/json' -d '{"id": "2", "description": "updated test1"}' 'http://localhost:8080/myapp/api/todo/2'

# get single
curl -v -X GET 'http://localhost:8080/myapp/api/todo/2'

# delete
curl -v -X DELETE -H 'Content-Type: application/json' 'http://localhost:8080/myapp/api/todo/2'

