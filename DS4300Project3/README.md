####Brendan Rejevich & Jeff Alhassan
####DS4300 Large Scale Information Storage & Retrieval

#####Directory Overview:
| File                 | Description                                                                                                     |
|----------------------|-----------------------------------------------------------------------------------------------------------------|
| SimpleGraph          | Simple graph with BFS for shortest path                                                                         |
| KeyValStore          | generic key value store for type T, which is a Redis-like  in-memory storethat stores a List[T] at a string key |
| LabeledPropertyGraph | DirectedGraph implementation with weighted edges.                                                               |
| hw03a.sc             | scala notebook with the warmup excersizes and first part of the assignment                                      |


##To test
From top level directory ``DS4300Project3``, run the following:

` -- $ mvn validate`

` -- $ mvn compile`

` -- $ mvn test`