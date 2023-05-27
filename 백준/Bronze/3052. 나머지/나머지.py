import sys
result = set()
for _ in range(10):
    temp = int(sys.stdin.readline())
    result.add(temp%42)
    
sys.stdout.write("%d\n"%len(result))