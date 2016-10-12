import pdb
def make_bricks(small,big,goal):
	pbig = goal/5
	psmall = goal%5
	if pbig<=big and psmall<=small:
		return True
	pdb.set_trace()
	return False
print make_bricks(6,1,11)

