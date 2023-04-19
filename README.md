# Order Management System
OMS is a spring boot application developed from the following user story

Each customer has their own customer account, delivery address and billing address. Delivery address and billing address can be the same object. The account number and account balance must be saved for an account, and the postcode, town and street must be saved for an address.
The customer object stores a reference to the account and address object(s) respectively, the account object stores a reference to the associated customer.
Customers are to be distinguished between business and private customers. A customer is therefore either a business or a private customer, never both at the same time. For each customer, the customer number and the telephone number are to be stored. In the case of a business customer, the company name is of interest; in the case of a private customer, the first name and surname. For a business customer, the name of a customer corresponds to the company name; for a private customer, the name is composed of the first and last name.
An order is always assigned to exactly one customer; conversely, a customer can place several orders. An order stores the order number and the order date and consists of at least one order item. The order object can be used to access the associated customer, but this should not be possible the other way round. A date object stores the day, the month and the year. It should be possible to determine the total order value and the profit of the order for each individual order.
An order item can only belong to one order. An object of an order item stores the integer order quantity, the purchase and sales price and the item. For each order item, the item price and the profit of this item are to be determined. Articles, for their part, have no knowledge of whether or not they belong to an order item.
An item stores the item number, the item description, the purchase price and the calculated sales price.


## Installation

Clone the repo 

Cd into the root directory and run

```bash
mvn clean install
```
This will generate a .jar file located under ```/oms/target```

Run

```bash
java -jar your-jar-file
```
The oms application will start on port 5000


## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.