#!/usr/bin/env ruby
#Directories
puts Dir.pwd
puts Dir.entries(Dir.pwd)

#csv
require 'csv'
filename = "data.csv"
if File.file? filename 
	CSV.foreach(filename) do |data|
	 p data
	end
else
    CSV.open(filename, 'w') do |data|
	data << ["name","ext", "room"]
	data << ["test", "123", "ok"]
	data << ["OOK", "OOP", "Ruby"]
    end	
end

#Math
# positive
i = +234
puts i
#negative
i = -123
puts i
# binary to dec
i = 0b101
puts i 
# octal to dec
i = 010
puts i
# hex to dec
i = 0x10
puts i
#hex to dec
i = 0x0F
puts i

pi = 3.14515
# rounds of number to nearest integer
new_pi = pi.round
puts new_pi

printf("pi %6.2f",pi)
puts ""
#DO NOT COMPARE floating number by each other not handy
#should be equal but not because floating is not accurate
puts (1.2-1) == 0.2
require 'bigdecimal'
# we use bigdecimal to do that
puts (BigDecimal("1.2") - BigDecimal("1.0")) == BigDecimal("0.2")

require 'mathn'

#gcd greatest common denominator
puts 36.gcd(120)
#lcm least common multiple
puts 36.lcm(120)

#prime number
pat = ""
for i in 0..30

   if i.prime?
       pat += "#{i} " 
   end
end
puts pat

#random #rand(max=number)
puts rand(100)

n = rand(100)
i = -1
while i!= n or i=='q'
    puts "Enter a number: "
	i = gets
    if i.to_i > n
	   puts "Too big"
	elsif i.to_i < n
	   puts "too small"
	elsif i.to_i == n
	       puts "you found it its #{n}"
		   break
	end
end
