#!/usr/bin/env ruby

#loops note they don't have do loops
ZERO = 0
number = ZERO

# while loop 
while number < 10
	puts "#{number} while"
	number += 1
end

puts 

number = ZERO

#until loops
until number > 10
    puts  "#{number} until "
	number += 1
end

# for loops
for i in 0..10
	puts "#{i} for loop"
end

# unique in ruby times does loop and then blocks
10.times { |i| puts "#{i} times"}

a = (1..4)
a.each{|var| puts var}
