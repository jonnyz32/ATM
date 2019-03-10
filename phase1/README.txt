
Jonathan Daniel Zak		File Manager Class
Vladimir Dyagilev		TimeChecker & User Subclass
Josiah Jon Friesen		ATM Class
Ignacio Acosta Hennandez	Debt and Asset Accounts
Philip Borui Cai		Account Superclass & BankManager subclass
Connor William Ferwerda		TextInterface Interface

When cloning our program, it needs to be setup such that group_0331 is the working directory. 
Otherwise, our relative file paths will not work. ATM_machine is the top level module and it
is where you should run the program from. You will interact will the console to use this 
program. It will give you prompts of what to type in. 

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