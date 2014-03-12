
def anagrammiko(sana1, sana2): return sorted(sana1) == sorted(sana2)

print anagrammiko('mikko', 'komik')
print anagrammiko('jannu', 'janu')

def fibo(n):
 def fibo_in(n):
  if n <= 1: return 0, n
  ed, nyk = fibo_in(n - 1)
  return nyk, ed+nyk
 return fibo_in(n)[1]

print fibo(6)
 

