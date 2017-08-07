#Simple TCP Server

This project contains a simple TCP serverwhich is capable of handling multiple concurrent clients. The maximum number of client serviceable at a given time is decided by **maxConn** parameter.

When the maximum  number of client is reached, and new request are reached, such client request will be queued for a period of time until the queue size is reached.

##How to run server

execute, 

```shell
./tcpserver.sh {maxConn}
```
example

```./tcpserver.sh 100
```

##How to run client

```shell
./tcpclient.sh
```
example
```./tcpclient.sh
```







