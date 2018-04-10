<b>Process Synchronization </b> <br/>

Process Synchronization means sharing system resources by processes in a such a way that, Concurrent access to shared data is handled thereby minimizing the chance of inconsistent data. Maintaining data consistency demands mechanisms to ensure synchronized execution of cooperating processes.

Process Synchronization was introduced to handle problems that arose while multiple process executions.

<b>Algorithms: </b> <br/>
1. Reader-writer
2. Producer-consumer
3. Dining Philosopher
4. Sleeping Barber 


Data Structures used for Synchronization:<br/>

<b>Mutex Locks:</b> <br/>
In this approach, in the entry section of code, a LOCK is acquired over the critical resources modified and used inside critical section (block of code where the shared resources are being modified ), and in the exit section that LOCK is released.
As the resource is locked while a process executes its critical section hence no other process can access it

<b>Semaphores:</b> <br/>

Dijkstra proposed a new and very significant technique for managing concurrent processes by using the value of a simple integer variable to synchronize the progress of interacting processes. This integer variable is called semaphore. So it is basically a synchronizing tool and is accessed only through two low standard atomic operations, wait() and signal().


<b>Wait</b> : decrement the value of its argument S as soon as it would become non-negative.<br/>
<b>Signal</b> :increment the value of its argument, S as an individual operation.<br/>

