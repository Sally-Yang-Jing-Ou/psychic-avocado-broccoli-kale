/ *
1) Consider the input strings “AGGTAB” and “GXTXAYB”. 
Last characters match for the strings. So length of LCS can be written as:
 
L(“AGGTAB”, “GXTXAYB”) = 1 + L(“AGGTA”, “GXTXAY”)
longest-common-subsequence
2) Consider the input strings “ABCDGH” and “AEDFHR. 
Last characters do not match for the strings. So length of LCS can be written as:
L(“ABCDGH”, “AEDFHR”) = MAX ( L(“ABCDG”, “AEDFHR”), L(“ABCDGH”, “AEDFH”) )

So the LCS problem has optimal substructure property as the main problem can be solved using solutions to subproblems.
       
       0 1 2 3 
     0 A B C D  
  0  0 0 0 0 0
  A  0 1 1 1 1
  B  0 1 2 2 2
  D  0 1 2 2 3
  E  0 1 2 2 3 

1. if a.charAt(i-1)==b.charAt(j-1): dp[i][j] = 1 + dp[i-1][j-1]

*/


public static int getLongestCommonSubsequence(String a, String b){
	int m = a.length();
	int n = b.length();
	int[][] dp = new int[m+1][n+1];
 
	for(int i=0; i<=m; i++){
		for(int j=0; j<=n; j++){
			if(i==0 || j==0){
				dp[i][j]=0;
			}else if(a.charAt(i-1)==b.charAt(j-1)){
				dp[i][j] = 1 + dp[i-1][j-1];
			}else{
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
	}
 
	return dp[m][n];
}