def solution(n, start, end, roads, traps):
    idx=dict()
    for i,trap in enumerate(traps):
        idx[trap]=i
    costs=[dict() for _ in range(n+1)]
    branch=[[] for _ in range(n+1)]
    for s,e,c in roads:
        branch[s].append([e,c])
        branch[e].append([s,-c])
    state='0'*len(traps)
    qu=[[start,state,0]]
    costs[start][state]=0
    mincost=10**9
    while qu:
        loc,state,cost=qu.pop(0)
        s=0 if loc not in idx else int(state[idx[loc]])
        for d,c in branch[loc]:
            if d in idx:
                cnt=int(state[idx[d]])
                nstate=state[:idx[d]]+str(1-cnt)+state[idx[d]+1:]
            else:
                cnt,nstate=0,state
            if c*(-1)**(s+cnt)>0:
                if nstate not in costs[d] or cost+abs(c)<costs[d][nstate]:
                    if cost+abs(c)<mincost:
                        costs[d][nstate]=cost+abs(c)
                        if d==end:
                            mincost=cost+abs(c)
                        else:
                            qu.append([d,nstate,cost+abs(c)])
    return mincost