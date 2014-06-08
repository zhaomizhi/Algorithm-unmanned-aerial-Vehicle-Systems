###Algorithm Unmanned Aerial Vehicle Systems

---
#### Introduction:

This project is written in Java, using Java Swing to present. This project used multi-thread to complete different tasks at the same time. 

---
####Object: 
This system could help UAVs (unmanned aerial vehichle ) find the most optimal path automatically. Monitor panel provides a way for users to monitor the status of the flight. 

---
####Environment 
The graph represents a cities map, any vertex in the graph is a city. The edge between vertexes are the path between them. If the vertexes are connected, the weight of the edges means the distances of the path.


---
#### Process( Algorithm)

1. find the city which stores the greatest quantity of the demanded products.

2. determine the optimal path. (Consider the weight of each path to be represented the risk factor.)

3. determine whether the city where the plane will fly over is in good condition. If the city is not available for the plane to fly, the plane will wait until the city is available.

4. determine whether the path for the plane to fly has encounter thunderstorm. If yes, the plane will circle the thunderstorm area.

5. the quantity of the product stored in each city will be refreshed after the plane has arrived the destination.

![test](https://raw.githubusercontent.com/zhaomizhi/Algorithm-unmanned-aerial-Vehicle-Systems/master/pictures/activity.png "Activity diagram")

----
#### How to get around the bad weather area 

![test](https://raw.githubusercontent.com/zhaomizhi/Algorithm-unmanned-aerial-Vehicle-Systems/master/pictures/circle.png "image")

