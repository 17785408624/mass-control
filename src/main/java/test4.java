import com.util.PublicUtil;

import java.util.Random;

public class test4 {
    public static void main(String[] args) {
        int[]i=new int[]{1,2,3,4};
        int[]i1=new int[6];
        System.arraycopy(i,0,i1,1,i.length);
        for(int ii:i){
            System.out.println("i"+ii);
        }
        for(int iii:i1){
            System.out.println("i1"+iii);
        }



    }
}
