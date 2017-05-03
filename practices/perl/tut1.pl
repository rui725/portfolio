# comments in PERL 
# basics of PERL

#prints hello world
print 'HI';

$myVar = 25; # this is how you call a variable 


# difference between single and double quotation
print "Hi I am Rui $myVar \n"; # outputs the value of myVar
print 'Hi I am Rui $myVar \n'; # outputs literal word "myVar" and escape sequence such as \n

# escape sequences
#
# \n new line
# \" puts " in to the string
# \a alert beeps
# \t tabs 5 spaces
# \u makes the next character into uppercase
# \l makes the next character into lowercase
# \U makes all the following character into Uppercase
# \L makes all the following character into lowercase
# \Q escapes all the alpha numeric special characters

print "HI\n";
print "\"HI\"\n";
print "BEEP\a\n";
print "Hello\tWord\n";
print "\uhello \uworld\n";
print "\lHELLO \lWORLD\n";
print "\Uhello world\n";
print "\LHELLO WORLD\n";
print "\Q!@#_";

#Scalar Variables
#can hold:
#String, integer,float, hex, octal
$num = 1;
$strm = "WAZZUP";
$short_hint = 333.23;
$octnum = 0167;  #this is how you assign octal value by placing 0 first then the octal value
$hexnum =0xabe;  # this is how you assign hexa value by placing 0x and then the hexa value

print "\nScalar variables\n";
print "Integer: $num\n";
print "String: $strm\n";
print "Float: $short_hint\n";
print "Octal: $octnum\n";
print "Hexadecimal: $hexnum\n";

