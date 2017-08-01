#exporting var part 2
echo "myexvar in tut2 is $myexvar"

#input in bash scripts
echo Enter your name 

read name

echo Your name is $name


#simple login form using bash
read -p 'Username:' uname # -p means prompt 
read -sp 'Password:' upass # -sp means silent input

echo 

echo Thank you $uname for logging in

# Reading Multiple inputs
echo "Enter games you will buy"

read game1 game2 game3

echo "you inputted $game1"
echo "you inputted $game2"
echo you inputted $game3



