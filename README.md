# MatrixMultiplication

Implemented two different matrix multiplication algorithms, then compared their two runtimes using JUnit.

The first method is the standard algorithm, where we iterate through 3 levels of the matrix using for loops, which should result in an O(n^3) runtime. The second method we use, Strassen’s algorithm, utilizes only 7 comparrisons rather than 8, which should result in an O(n^2.807) runtime. 


Standard Algorithm:   
![Alt text](http://i.imgur.com/zdl20mq.jpg "Standard Algorithm")

Strassen's Algorithm:   
![Alt text](http://i.imgur.com/1xyW6Jq.jpg "Strassen's Algorithm")   
(Images taken from [Wikipedia](https://en.wikipedia.org/wiki/Strassen_algorithm))


By the Master Theorem, Strassen’s algorithm should have yielded a faster runtime than the standard method. This worked up until a higher n-value was chosen. The biggest factor that may have caused the runtimes to result in this manner is due to the implementation of Strassen’s algorithm. Optimization may be needed in the code which may display closer runtimes to what is described as O(n^2.807).
