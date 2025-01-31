# comments goes here

# prints output in bash scripts
echo HI THIS IS YOUR FIRST BASH SCRIPT

#reserved variables list:

echo $0 #name of the bash script

#$1 - $9 are arguements when running scripts
echo $1 $3

#$# counts how many arguements run in the command
echo $#

#$@ all the arguements supplied to the bash script
echo $@

#$? the exit status of the most recently run process
echo $?

#$$ process ID of the current script.
echo $$ 

#$USER - the username of the user running who is running the script
echo $USER

#HOSTNAME - the host name of the running script
echo $HOSTNAME

#$SECONDS - number in seconds since the script was started
echo $SECONDS

#$RANDOM - returns a different number each time it is called
echo $RANDOM

#$LINENO - returns the line number in bash script
echo $LINENO

#variables declaration
myvar=RUI
echo My name is $myvar

#difference with single quote and double quote
echo 'my variable name is $myvar'
echo "I am $myvar"

#Substitution for commands
myvar=$( ls ../.. )
echo $myvar

#Exporting variables you may use the value inside a variable
# in another script
myexvar="Rui"

echo "myexvar = $myexvar"

export myexvar
. tut2.sh


