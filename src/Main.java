import java.util.Scanner;

public class Main {

	static int m;
	static int[] needTime;
	static String[] names;
	static Solution[][] cache;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		int n=sc.nextInt();
		cache = new Solution[n][n];
		needTime = new int[n];
		names = new String[n];
		sc.nextLine();
		for (int i=0;i<n;i++) {
			names[i]=sc.nextLine();
			needTime[i] = sc.nextInt();
			sc.nextLine();
		}
		Solution bestSolution = getBestSolution(0,n);
		System.out.println(bestSolution.time);
		
		printSolutionNames(bestSolution);
	}
	
	private static void printSolutionNames(Solution solution) {
		// TODO Auto-generated method stub
		
		for (int i=solution.index1;i<solution.index2;i++) {
			System.out.print(names[i]+" ");
		}
		System.out.println();
		printSolutionNames(getBestSolution(solution.index2,solution.index3));
		
	}

	private static Solution getBestSolution(int start, int end) {
		// TODO Auto-generated method stub
		//exit point
		Solution solution = new Solution();
		int maxTime = Integer.MIN_VALUE;
		if (end-start<=m) {
			for (int i=0;i<end-start;i++) {
				if (needTime[start+i] > maxTime) {
					maxTime=needTime[start+i];
				}
			}
			solution.time=maxTime;
			solution.index1=start;
			solution.index2=solution.index3=end;
			return solution;
		}
		
		int minimumTime=Integer.MAX_VALUE;
		
		for (int i=1;i<=m;i++) {
			Solution part1 = getBestSolution(start,start+i);
			Solution part2 = getBestSolution(start+i,end);
			if (part1.time+part2.time<minimumTime) {
				minimumTime = part1.time+part2.time;
				solution.time = minimumTime;
				solution.index1 = start;
				solution.index2 = start+i;
				solution.index3 = end;
			}
		}
		return solution;
	}

}

class Solution {
	int time;
	int index1,index2,index3;
}
