import random
import pdb
import sets
b =[]
randex=[]
def rands():
	check = True
	while(check):
		global randex
		randex =[]
		for n in range(0,11):
			randex.append(random.randint(1,101))
		randexSize=sets.Set(randex).__len__()
		if randexSize == 10:
			check = False
		

def board():
	patt='['
	for n in range(0,100):
		if (n+1)>=10 and (n+1)%10==0:
			if (n+1) in randex:
				patt+='|{0}|]'.format(n+1)
			else:
				patt+='|--|]'
			print patt
			patt='['
		else:
			#pdb.set_trace()
			if (n+1) in randex:
				if (n+1) <10:
					patt+='|0{0}|'.format(n+1)
				else:
					patt+='|{0}|'.format(n+1)
			else:
				patt+='|--|'
rands()
board()
