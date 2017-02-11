
def prime(value):
    txt = '1 '
    for index in range(3,value+1):
        for j in range(2,index+1):
            if (index%j==0 and j==index):
                txt = txt + str(j) + ' '
            elif (index%j==0 and j!=index):
                break
            else:
                continue
    return txt
val = input()
print prime(val)

#====================================================================
#recursion limited
#====================================================================
def rots(value,index):
    if index == value:
        return True
    else:
        if value % index ==0:
            return False
        else:
            return rots(value,index+1)

def recurprime(value):
    if value ==2:
        return str(1)
    else:
        if rots(value,2):
            return str(value) +' '+ str(recurprime(value-1))
        else:
            return str(recurprime(value-1)) + " "
#=====================================================================
#print recurprime(val)