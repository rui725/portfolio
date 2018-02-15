#!/usr/bin/env ruby

#advance assigning
a=1; b=2; c=4
k,i = 2,3

puts "Assignment"
puts "#{a} #{b} #{c}"
puts "Parallel Assignment"
a=b=c
puts "#{a} #{b} #{c}"
puts '#{a} #{b} #{c} different from double quote' 
puts ''

#command expressions
puts "Commands Expansion"
puts `ls`
puts `pwd`
puts `help`
puts ''

puts "Enter something: "
#chomp removes post whitespaces
str = gets.chomp
puts str
case str
	when '0'..'100'
	     puts "it's a number"
	when 'a'..'z'
	     puts "it's letters"
	else
	     puts "special character"
end

#loop expressions
# times until.times
9.times do |var|
 print "#{var}"
end
puts ""

# start.upto(until)
1.upto(9) do |var|
 print "#{var}"
end
puts ""

#start.step(until,increment)
1.step(9,3) do |var|
 print "#{var}"
end
puts ""
puts ""
#iterators
a=(1..5)
b=('a'..'e')
c= 'HI HELLO'
#iterate each element
a.each do |var|
  puts var
end
puts ""

b.each do |var|
  puts var
end
puts ""

#each for character element in a string
c.each_byte do |var|
  putc var
  putc ""
end

puts ""

for var in a
   puts var
end
puts ""
for var in b
   puts var
end
puts ""
# doesnt work for individual characters in string
#for var in c
#   puts var
#   putc ":"
#end
puts ""

#blocks or lambda expressions
5.times {|var| puts var}

d = (5..8)

print d
# adds 10 on each element of d
e = d.collect{|x| x+=10}

puts ""
print e
puts ""

#example blocks
def exblock
	yield 2
	yield 3
	yield 4
end
#prints out whatever we did yield does the calling
exblock {puts "test"}
# option we can add arguements that would yield |x| value
exblock {|x| puts x*2}
# this outputs x * 2 for each yield

