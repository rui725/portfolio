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

