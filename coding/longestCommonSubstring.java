

/*
   0 1 2 3
   a b c d 
 a 1 0 0 0
 b 0 2 0 0
 d 0 0 0 0 

*/
public static int getLongestCommonSubstring(String a, String b){
	int m = a.length();
	int n = b.length();
 
	int max = 0;
 
	int[][] dp = new int[m][n];
 
	for(int i=0; i<m; i++){
		for(int j=0; j<n; j++){
			if(a.charAt(i) == b.charAt(j)){
				if(i==0 || j==0){
					dp[i][j]=1;
				}else{
					dp[i][j] = dp[i-1][j-1]+1;
				}
 
				if(max < dp[i][j])
					max = dp[i][j];
			}
 
		}
	}
 
	return max;
}