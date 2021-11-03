/*****************************************************************
ClientMenuState.java
Responsible individual: Reuben Koop
1.	In the ClientMenuState, the Context has stored the ClientID for the current client; all operations are for that ClientID. The state will have operations for the following:
(a)	Show client details. The state invokes a method on Facade to get the Client object and then gets the client details. Note that the ClientID is available in the Context.
(b)	Show list of products with sale prices.  The state invokes a method on Facade to get an iterator, and then extracts the needed information.
(c)	Show client transactions. The state invokes a method on Facade to get the Client object and then gets the transaction details for the client. Note that the ClientID is available in the Context.
(d)	Modify client’s shopping cart. System displays contents. Actor can add product, remove product or change quantity.
(e)	Display client waitlist.
(f)	Logout. System transitions to the previous  state, which has to be remembered in the context. (If previous state was the OpeningState, it goes there; otherwise it goes to ClientMenuState.)
*****************************************************************/
