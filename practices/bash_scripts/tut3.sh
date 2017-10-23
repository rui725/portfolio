#ARITHMETIC OPS in BASH
#usage of let save the value to the variable

#example 1:
let a=2*3
echo $a

#example 2:
let "b = 4 / 2"
echo $b

#example 3:
let b++
echo $b

#example 4:
let "a = $b * 4"
echo $a

#OPS
# / , + , - , * , %
# var++ , var--


#using expr does not save the value into a variable
#example 1:
expr "5 + 4 - 1 * 5"

#example 2:
expr 5 * 5 / 5 + 1 - 4

#example 3:
expr 4-4

#example 4:


