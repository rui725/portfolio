#Array variable using Perl
# use @ for array variable
# use $ for simple variable

#array declaration
@nums = (1,2,3);
@strm = ("Rui","Rafael", "Rosillas");


print "Array nums: @nums\n";
print "Array strm: @strm\n";

print "Array with index: @nums[0]\n";
print "Array with index: @strm[1]\n";

# using range and assigning it to an array
#   example: 
@count = (1..10); #automatically fills range from 1 to 10
@alphabets = (a..z); #automatically fills range from a to z

print "array count: @count\n";
print "array alphabets: @alphabets\n";

$sizec = @count;    # gets the size of the array of @count
$sizea = @alphabets; # get the size of the array of alphabet

print "Size of array count: $sizec\n";
print "Size of array alphabets: $sizea\n";

#Adding and removing array
# push to add
# shift to remove


print "\nArray Adding and Removing\n";
@name = ("Rui","Rafael","Rosillas");
print "Name: @name\n";

push(@name, " period"); # adds at the end of the array
print "(PUSH) Name: @name\n";

unshift(@name, "My name is"); # adds at the beginning of the array
print "(UNSHIFT) Name: @name\n";

pop(@name); # removes at the end of the array
print "(POP) Name: @name\n";

shift(@name); #removes at he beginning of the array
print "(SHIFT) Name: @name\n";




