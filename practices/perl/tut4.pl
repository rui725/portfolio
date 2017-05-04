# Hashes in PERL
# use % to create hash
# uses (key=>Value)
#
# Hash Declaration
%player = (1=>"Rui", 2=>"Rafael", 3=>"Rosillas");
%capitals =("Philippines"=>"Manila", "US"=>"Washington DC");

print "Best Player: $player{1}\n"; #key 1 indicates prints value
print "Philippines's Capital: $capitals{'Philippines'}\n"; #key Philippines indicates value

#accessing value $nameVariable{key} to get the value

@mlist = @player{2..3}; # or {2,3} to get certain range of content acts as array like{"Rafael","Rosillas"}
print "Top player @mlist\n";

#get all keys
@keyn = keys %capitals;

#get all value
@val = values %capitals;

print "Keys: @keyn\n";
print "Values; @val\n";

#obtain size of hash
$sizeh = keys %player; # not applicable @capitals;

print "Size of the has is : $sizeh\n";

# Adding and removing Hash Elements

#adding Hash element
$player{4}= "Gwapo";

#remove has element
delete $player{2};


@keyp = keys %player;
@valp = values %player;
$sizep = keys %player;

#difference between shrouded and not shrouded by string
print "Player Values: @valp"."\n"; # normal
print "Player Values: ". @valp."\n"; # not normal . is for concatenation
print "Player Key: @keyp"."\n";
print "Player Size: $sizep\n";

