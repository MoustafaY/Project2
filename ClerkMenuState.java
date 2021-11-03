/*****************************************************************
ClerkMenuState.java
Responsible individual:
(a)    Add a client. Gets details of new client; calls method on Façade.
(b)    Show list of products with quantities and sale prices.  The state invokes a method on Facade to get an iterator, and then extracts the needed information.
(c)     Show list of clients. The state invokes a method on Facade to get an iterator, and then extracts the needed information.
(d)    Show list of clients with outstanding balance. The state invokes a method on Facade to get an iterator, and then extracts the needed information.
(e)    Become a client. The actor will be asked to input a ClientID; if valid, this ID will be stored in Context, and the system transitions to the  ClientMenuState.
(f)      Display the waitlist for a product. The state asks the actor for productid; calls method on Façade to get an iterator.
(g)    Receive a shipment. The state asks the actor for productid and quantity; calls method on Façade to get an iterator. Displays each waitlisted order and performs operation requested by actor (skip or fill).
(h)    Logout. System transitions to the previous  state, which has to be remembered in the context. (If previous state was the OpeningState, it goes there; otherwise it goes to ManagerMenuState.)
*****************************************************************/
