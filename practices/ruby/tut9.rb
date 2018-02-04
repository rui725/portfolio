#!/usr/bin/env ruby

#string manipulations
str = "example string"
#capitalize the first leter
puts str.capitalize
#single quote
puts %q/#{str}/

#double quote
puts %Q/#{str}/

#long string 
str = <<END_OF_STRING
Test String
test again
last test

END_OF_STRING

puts str

# lowercase letters
puts str.downcase

# uppercase letters
puts str.upcase

str = "    Test    "
#removes leading whitespace
puts str.lstrip

#removes post whitespaces
puts str.rstrip

#removes whitespaces on both sides
puts str.strip

#changes the value in the variables using the !
str.lstrip!.rstrip!
puts str

#length of string
puts str.length

#inserting strings in a variables
puts str.insert(3,"INSERT")

str = "Example string manipulations"
# splits the string by the delimeter by default space
words = str.split
puts words

#substring
puts words[0].slice(0..4)

#get index of character search starts in the beginning
puts words[0].index("a")

#get index of character search starts at the end
puts words[0].downcase.rindex("e")

# replace the whole variable with the value
str.replace "string"
puts str

# include checks if it has the pattern string
puts str.include? "ng"

#replaces the first matched pattern string sub("pattern","replacement")
puts str.sub("i", "o")

#replace all matched pattern string gsub("pattern","replacement")
str = "ME ME ME"
puts str.downcase.gsub("me","You")

