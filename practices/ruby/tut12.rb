#!/usr/bin/env ruby

#symbols
# literal 
s = :symbol
puts s
# to string
puts s.to_s

#ranges
r = 3..6
puts r
puts r.class

r.each{|x| puts x}

#time date class

print "year: "
y = gets.to_i

print "Month: "
m = gets.to_i

print "day: "
d = gets.to_i
date = Time.mktime(y,m,d)
verb=""
if date < Time.now
    verb = "was "
else
	verb = "is "
end

puts "#{y}, #{m}, #{d} #{verb} a #{date.strftime("%A")}"


#Arrays
a = []

# add element
for i in 1..5
    a << i
end
print a
puts ""
#array no element but has size Array.new(size)
arr = Array.new(3)
print arr
puts
#array repetitions * NOTE changing one element affects all the element
ar = Array.new(5, "repeating")
print ar

#do changes
ar[0].capitalize!
print ar
puts ""
# away to do that is here * NOTE changing one elmeent will not affect other elements
ar = Array.new(4) {"repeat"}

#do changes
ar[0].capitalize!
print ar

puts ""
#prints the first element
print ar.first 
puts ""
#prints the last element
print ar.last
puts ""
#prints size
print ar.size
puts ""
#prints length
print ar.length
puts ""

#override or assign new value to the array
ar[1]="changed"
print ar
puts ""
#sorting array with string and integer
ar = ["k", 2, "a", 1, "3"]
print ar.sort{ |x,y| x.to_s <=> y.to_s}
puts ""
# sorting array with string numbers and integer
ar = ['4',2,'1',9,'10',5]
print ar.sort{ |x,y| x.to_i <=> y.to_i}
puts ""
#searching through array using find which finds only one element
print ar.find{|a| a ==9 }
puts ""
#find all elements that complies with the block
print ar.find_all{|a| a.to_i%2 ==0 }
puts ""
#grep looks for the regular expression for all elements
ar = ["test","think","yes","new","no"]
print ar.grep(/e/)
puts ""
print ar.min
puts ""
print ar.max
puts ""
puts ""
#iterating arrays just use # array.each or array.reverse_each
ar.reverse_each{|a| puts a}

#hash initialization
h = Hash.[](:white => "I'm white", :black=>"I'm black", :brown=>"I'm brown")
print h
puts""
#add element to hash
h.[]=(:yellow,"I'm yellow")
print h
puts ""
#access hash
print h[:yellow]

#accessing non-element value of the hash
print h["test"]
puts ""
#deleting element of hash value
h.delete(:black)
print h
puts ""


#iterating through hash
h.each {|x| p x}
puts ""
#iterating with key value
h.each{|x,y| p "#{x} is the key and the value is #{y}"}
puts ""
#iterate only key
h.each_key {|x| p x}

#iterate only value
h.each_value{|x| p x}

#sorting Hash is not an actual hash but a sorted array
print h.sort {|x,y| x.to_s <=> y.to_s}
puts ""
#merging Hash
new= {:key=>"I'm a key", :value=>"i'm a value"}
n = new.merge(h)
print n
puts ""

#a simple manipulation of hash with array should be exactly even to set key and value
a = [1,"test",3,"t",2,"woops"] # length 6 complies with even and enough to have key value pair
yhash = Hash[*a]
print yhash