def gcd(n,m):
    if (m==0):
        return n
    else:

        return gcd(m,n%m)

print 'enter n value: '
n = input()
print 'enter m value: '
m = input()
print gcd(n,m)