#subroutines AKA Function or Method

sub sayHI(){
	print "HI!!\n";
}

sayHI();

#passing arguements or parameters in subroutines

sub addMe{
   $n1 = @_[0];
   $n2 = @_[1];
   print $n1 + $n2. "\n";
}
addMe(24,20);

#dynamic Arguements and counting arguements 
sub countChar{
     #count
     $size = @_;
     print "Size of arguements is $size\n";
     #dynamic
     foreach $c (@_){
         if(ord($c)>=65 and ord($c)<=90){
	 	push(@upper,$c);
	 }elsif(ord($c)>=97 and ord($c)<=122){
	 	push(@lower,$c);
	 }elsif(ord($c)>=48 and ord($c)<=57){
	 	push(@digits,$c);
	 }else{
	 	push(@special,$c);
	 }
     }
     print "Upper: @upper\n";
     print "Lower: @lower\n";
     print "Digits: @digits\n";
     print "Specials: @special\n";
}
countChar('a','C',1,4,5,'$','#','d','0');

#passing an array to subroutine
sub passMe{
	@pass = @_;
	print "The Elements in the array are: @pass\n";
	
}
@arr = ("Rui", "Rafael", "Rosillas");
passMe(@arr);

#return in subroutine
sub getRemainder{
	return @_[0] %2;
}
$rem = getRemainder(15);
print $rem."\n";


#private variables
$num = 100;
sub test{
	my $num = 50;
	print "\$num inside subroutine is : $num\n";
}
test();
print "\$num outside subroutine is : $num\n";
