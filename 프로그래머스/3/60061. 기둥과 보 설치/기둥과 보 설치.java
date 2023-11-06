import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
	static int size;
	static int[][] map;
	static List<Build> buildList;
	public int[][] solution(int n, int[][] build_frame) {
		size = n;
		map = new int[n+1][n+1];
        
		buildList = new ArrayList<>();
		for(int i=0; i<build_frame.length; i++	) {
			int x = build_frame[i][0];
			int y = build_frame[i][1];
			int type = build_frame[i][2];
			int option = build_frame[i][3];
    		
			if(option==1) { // 설치 
				if(canBuild(x,y,type)) {
					buildList.add(new Build(x,y,type));
				}
			}else { // 삭제 
				buildList.remove(new Build(x,y,type));
				if(!isSustainable(buildList)) {
					buildList.add(new Build(x,y,type));
				}
			}
		}
        
		Collections.sort(buildList);
		int[][] answer = new int[buildList.size()][3];
		int idx=0;
		for(Build b : buildList) {
			answer[idx][0] = b.x;
			answer[idx][1] = b.y;
			answer[idx++][2] = b.type;
		}
		return answer;
	}
    // 현재 전체 건축물이 유지가능한지 테스트 
	static boolean isSustainable(List<Build> list) {
		for(Build b : list) {
			if(!canBuild(b.x, b.y, b.type)) return false;
		}
		return true;
	}
    
    // (x,y,type) 건축물이 설치 가능한지 테스트 
	static boolean canBuild(int x, int y, int type) {
		// 기둥 
		if(type ==0) {
			// 바닥 | 보 한쪽 끝 | 기둥 위  
			if(y==0 || buildList.contains(new Build(x,y-1,0)) || 
					buildList.contains(new Build(x-1,y,1)) ||
					buildList.contains(new Build(x,y,1))){
				return true;
			}
		}
		// 보 
		else {
			// 한쪽 끝 부분이 기둥 위 | 양쪽 끝 부분 다른 보 
			if(buildList.contains(new Build(x,y-1,0)) ||  
					buildList.contains(new Build(x+1,y-1,0)) ||
					(buildList.contains(new Build(x-1,y,1)))&& 
						buildList.contains(new Build(x+1,y,1))){
				return true;
			}
		}
		return false;
	}
}

class Build implements Comparable<Build>{
	int x;
	int y;
	int type;
	
	public Build(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type =type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + type;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Build other = (Build) obj;
		if (type != other.type)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public int compareTo(Build o) {
		if(this.x > o.x) {
			return 1;
		}else if(this.x == o.x){
			if(this.y > o.y) {
				return 1;
			}
			else if(this.y == o.y) {
				return this.type - o.type;
			}
			else {
				return -1;
			}
		}
		else {
			return -1;
		}
	}
}