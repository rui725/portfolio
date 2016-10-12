ok = raw_input()
maxcnt =0
maxlet =''
ctr =0
cnt=0
maxctr=0
maxtempctr= 0
while (ctr!= len(ok)-1):
    if ok[ctr] == ok[ctr+1]:
        if cnt ==0:
            maxtempctr=ctr
        cnt+=1
    else:
        if maxcnt < cnt:
            maxcnt=cnt
            maxlet = ok[ctr]
            maxctr=maxtempctr
        cnt=0
    if(ctr<=len(ok)-1):
        if maxcnt <cnt:
            maxcnt=cnt
            maxlet= ok[ctr]
            maxctr=maxtempctr
    ctr+=1
print 'position {0} sequences {1}'.format(maxctr,maxcnt+1)