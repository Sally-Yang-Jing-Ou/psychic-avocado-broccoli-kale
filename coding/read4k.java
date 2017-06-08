import java.io.*;
import java.util.*;

class Solution {

  public static int read(char[] buf, int n) {
    boolean eof = false;      // end of file flag
    int total = 0;            // total bytes have read
    char[] tmpBuf = new char[4]; // temp buffer

    while (!eof && total < n) {
      int count = read4(tmpBuf);
      eof = count < 4; // check if it's the end of the file
      count = Math.min(count, n - total); // get the actual count, if read 4, but we only need 2 more, pick n - total
      for (int i = 0; i < count; i++) 
        buf[total++] = tmpBuf[i]; // copy from temp buffer to buf

    }
    return total;
  }

  /*
  read multiple times
   store the data received in previous calls. In the while loop, 
   if buffPtr reaches current buffCnt, it will be set as zero to be ready to read new data.
  */
  private int tempPtr = 0; //pointer permanent in the temp bugg
  private int tempCnt = 0; //how much it's being read each time
  private char[] tempBuff = new char[4]; //temp buff
  
  public int read(char[] buf, int n) {
    int pointer = 0; //total size read into buf
    while (ptr < n) {
      if (tempPtr == 0) {
        tempCnt = read4(tempBuff);
      }
      if (tempCnt == 0) break; //eof
      
      while (pointer < n && tempPtr < tempCnt) {
        buf[pointer++] = tempBuff[tempPtr++];
      }
      if (tempPtr == tempCnt) tempPtr = 0; //read everything in temp buff
    }
    return pointer;
  }
  
  public static void main(String[] args) {

  }
}