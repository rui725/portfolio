# IF CONDITIONS using PERL

#exists only works if your checking array or Hash variable
if (exists($yes{0})){
	print "\$yes exist\n";
}else{
	print "\$yes does not exist\n";
}

$num = 1;
if(num==0){
	print "num is zero\n";
}elsif (num==1){
	print "num is one\n";
}else{
	print "num is not one or zero\n";
}

#unless statement = works only if the condition is fall
$num = 6;
unless($num!=6){
	print "num is 6\n";
}else{
	print "num is not 6\n";
}

#ternary condition
$val = ($num == 6)? "The number is 6 " : "The number is not 6";
print "$val\n";


