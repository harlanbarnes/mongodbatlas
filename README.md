# Instructions

## building
```
docker build -t mableton/mongodbatlas .
```

## running
```
docker run \
  -e MONGODB_HOSTNAME=<hostname> \
  -e MONGODB_USERNAME=<username> \
  -e MONGODB_PASSWORD=<password> \
  mableton/mongodbatlas
```
