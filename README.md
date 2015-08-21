# microservice-demo
The sample application consists of microservices backed by spring and netflix components to build a simple catalog service. 

The catalog service communicates with :

1. Product-service : Simulates generating product. 
2. Price-service : Simulates price calculation for a given product.
3. Comment-service : Simulates comments generation for a given product.

Apart from these there are:

1. Eureka-service : Acts as a service registry.
2. Zuul-service : Acts as a proxy.

# Dependecies
Docker and docker-compose, refer to https://docs.docker.com for installation

#For Mac users 
If you are on mac then please replace localhost with IP address allocated to your docker machine.
For e.g. If you are using `docker toolbox` then you can execute `docker-machine ip <machine-name>` and you will get something like `192.168.59.103`

#Launching 
Build all projects by executing
```
sh run-all.sh
```

then deploy all services via 
```
docker-compose up -d
```

Once all services are up and running , You can visit http://localhost:8761/ to monitor all services registered with eureka.

#Sample request

```
curl -s localhost:8765/catalog-service/catalog/100
```

You will get response like this: 
```json
{
  "product": {
    "price": {
      "amount": 1000.2
    },
    "comments": [
      {
        "content": "I like this product very much!!!",
        "author": "John",
        "productId": 100,
        "id": 1
      },
      {
        "content": "A must buy, worth the price",
        "author": "Bob",
        "productId": 100,
        "id": 2
      }
    ],
    "description": "super cool product",
    "name": "Product-A",
    "id": 100
  },
  "id": 100
}
```
Now If you will take the comment-service down, and execute 
```
curl -s localhost:8765/catalog-service/catalog/100
```
You will notice that only comments will be missing from the response and still your application will work. E.g.

```json
{
  "product": {
    "price": {
      "amount": 1000.2
    },
    "comments": [],
    "description": "super cool product",
    "name": "Product-A",
    "id": 100
  },
  "id": 100
}
```


