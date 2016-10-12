import random

correct = random.randrange(10000,99999)
correct = correct.__str__()
print 'Guess the correct 4 digit number from 1000-9999:\n'
choice = 'y'
attempts=0
gue = []
knows =[]
for n in correct:
	knows.append('-')
while(choice=='y'):
	print '\n\nEnter Digit:'
	while(True):
		index = 0
		guess = random.randrange(10000,99999)
		guess = guess.__str__()
		g=[]
		for me in guess:
			g.append(me)			
		if guess in gue:
			continue
		else:
			for n in knows:
				if n.isdigit():
					g[index]=n
				index+=1
			guess = ''.join(g)
			gue.append(guess)
			break
	index = 0
	aster =''
	print guess
	for cha in guess:
		if correct[index] == cha:
			aster+='*'
			knows[index] = cha
		index+=1
	print aster
	attempts +=1
	print 'Total Attempts: ' + attempts.__str__()+'\n\n'
	if aster.__len__() == correct.__len__():
		print 'congrats the number is '+guess
		choice = 'n'
	else:
		print 'would you like to try again[y/n]?'
		choice = 'y'

