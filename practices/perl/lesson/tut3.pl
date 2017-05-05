# ARRAY MANIPULATION
#
# Slicing Array
@months = ("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
$sizeM = @months;
print "Months: @months\n";
print "Size: $sizeM\n";

#manual slice
@winter = @months[10,11,0,1];
@summer = @months[5..7];

print "Winter Months: @winter\n";
print "Summer Months: @summer\n";

# replacing value in a array
@name = ("Rui","M","Rosillas");
@name[1] = "R";
#manual replace
print "name: @name\n";
# splice function to replace a range of array
@rep = ("Rafael");
splice (@name,1,1,@rep); # splice(arrayToChange,startIndex,Length, arrayValueToSet)
print "name: @name\n";

#merging array into one string
@games = ("LOL","DOTA2", "CS", "TEKKEN");
#join(delimeter, array)
$mstring = join("-",@games); # - is delimiter to combine the string in array
print "String: $mstring\n";

#splitting a string to array
#split(delimiter, string variable);
@marr=split("-",$mstring);
print "Array: @marr[1..2]\n";

#SORT ARRAY
@sorta = sort(@games);
print "Sorted Array: @sorta\n";
# mergin array
@merga = (1..5,6..10);
print "Merge array: @merga\n";

