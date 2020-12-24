
ATM_machine is the top level module and it
is where you should run the program from. You will interact with the GUI to use this
program.

When first running the program, you will need to login as bankManager using the 
username: manager and password: password. From the bank manager's account you have the 
option to create new users accounts and to approve different types of accounts that users
have requested. After creating a user you can logout from bankmanager and login to that 
user's account if you wish.

The deposits.txt file is given as lines of integers seperated by commas.
It is in the following format:

Amount to deposit, 0 for cash deposit or 1 for check deposit, number of fives to deposit,
number of tens to deposit, number of twenties to deposit, number of fifties to deposit

If the deposit is a check, then number of bills deposited should all be set to zero. Ex:

535,0,3,5,6,7
232,1,0,0,0,0

The first line is a 535 dollar deposit consisting of 3 fives, five tens, 6 twenties and 
7 fifties.
The second line is a cheque deposit of 232 dollars. To do a deposit by file, you will need 
update the deposits.txt file with your own file before running the program.

This virtual ATM supports many activities. The various account types available include Chequing, Savings, Credit Card,
Credit Line, Joint, and Stock. We have also included functionality such that foreign currency can be traded from any
debt account.

Some classes will have compile errors unless the library needed to run the code is imported from Maven.
Import the following libraries from Maven:
com.google.code.gson:gson:2.8.5
org.mockito:mockito-all:1.10.19
