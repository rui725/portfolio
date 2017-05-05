#Arithmetic Ops
# + - * / % 
# ** for power 
print 5**2;

#relational operator
# < > <= >= == != 

if (2<4){
	print "TRUE\n";
}else{
	print "FALSE\n";
}

# string comparison
# lt = less than
# gt = greater than
# le = less than equal to
# ge = greater than equal to
# eq = equal to
# ne = not equal to

$h = "Hello";
$w = "World";
if($h lt $w){
	print "$h less than $w";
}elsif($h gt $w){
	print "$h greater than $w";
}elsif ($h le $w){
	print "$h less than equal to $w";
}elsif($h ge $w){
	print "$h greater than equal to $w";
}elsif($h eq $w){
	print "$h equal to $w";
}else{
	print "$h not equal to $w";
}

# Assigning Operator
# -=, *=, +=, /=, %=
# for power
#  example: **=
$num =3;
$num **= 2;
print "\n\n$num\n";

#Bitwise Operator
# to use bitwise put %b
# Example
$n1= 49;
$n2=90;
printf "$n1 is : %b\n",$n1; # converts the value 49 to binary 
printf "$n2 is : %b\n",$n2; # printf can only be used in bitwise operator

$and = $n1&$n2; # & AND bitwise operator
printf "Result of AND OP = %b dec = $and\n",$and;

$or = $n1|$n2; # | OR bitwise operator
printf "Result of OR OP = %b dec = $or\n", $or;

$xor = $n1^$n2; # ^ XOR bitwise operator
printf "Result of XOR OP = %b dec = $xor\n",$xor;

$comp = (~$n1);
printf "Result of complement OP = %b dec = $comp\n",$comp;

# Logical Op
# and or 
# perl operator (not) acts like ! 
if(not($n1>50)){
	print "$n1 is less than 50\n";
}

# OTHER OPERATORS
# Repeats Strings
$str = "Rui!!";
print ($str x 10);

# range operator
@nlist = (1..20);
print "\n@nlist\n";

#Increment
#pre ++var;
#post var++;

#Decrement
#pre --var;
#post var--;


