
ATM_machine is the top level module and it is where you should run the program from. 
You will interact with the GUI to use this program.

When first running the program, you will need to login as bankManager using the 
username: manager and password: password. From the bank manager's account you have the 
option to create new users accounts and to approve different types of accounts that users
have requested. After creating a user you can logout from bankmanager and login to that 
user's account if you wish.


This virtual ATM supports many activities. The various account types available include Chequing, Savings, Credit Card,
Credit Line, Joint, and Stock. We have also included functionality such that foreign currency can be traded from any
debt account.

Some classes will have compile errors unless the library needed to run the code is imported from Maven.
Import the following libraries from Maven:
com.google.code.gson:gson:2.8.5
org.mockito:mockito-all:1.10.19
