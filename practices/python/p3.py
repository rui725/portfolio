n=[]
sum=0
while(True):
	num = raw_input()
	if num.isdigit():
		n.append(num)
		sum+=float(num)
	else:
		break
print 'Statistical Results:'
print 'Count: {0}'.format(n.__len__())
print 'Sum: {0}'.format(sum)
print 'Average {0}'.format(sum/n.__len__())
print 'Mean: {0}'.format(sum/n.__len__())
n.sort()
if(n.__len__()%2==0):
	id1=(n.__len__()/2)-1
	id2=id1+1
	median = float(n[id1])+float(n[id2])/2
else:
	id = int(n.__len__()/2 + .5)
	median = n[id]
print 'Median: {0}'.format(median)
mode = ''
great = 0
for nu in n:
	ctr=0
	for i in range(0,n.__len__()):
		if nu == n[i]:
			ctr+=1
	if great < ctr:
		great = ctr
		mode = nu
print 'Mode: {0}'.format(mode)
