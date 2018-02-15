#!/usr/bin/env ruby

#regex
my_regex = /[a-z]+/
my_string = "123ASFaf23f"

#get index of the matching value
my_ind_res = my_string =~ my_regex
puts my_ind_res

# get the matchings
my_findings = my_string[my_regex]
puts my_findings
# get all matches
my_finding_all = my_string.scan(my_regex)
puts "#{my_finding_all} all matches"

#range in ruby show 1 to 10
my_range = (1..10).to_a
puts "#{my_range} arrays range"
# range length -1 show 1 to 9
my_range = (1...10).to_a
puts "#{my_range} arrays range"
 