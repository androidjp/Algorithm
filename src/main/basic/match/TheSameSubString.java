package main.basic.match;

/**
 * Created by androidjp on 16/9/21.
 */
public class TheSameSubString {

    /**
     * @param a
     * @param b
     * @return a b串中相同的子串
     */
    public static String sameSubString(String a, String b){
        if (a.equals(b))
            return new String(a);

        String max = (a.length()>=b.length())?a:b;
        String min = (max.equals(a))?b:a;

        String sub = null;
        for(int i=0;i<min.length();i++){
            for (int from = i,to=min.length()-i;to<min.length()+1;from++,to++){
                sub = min.substring(from,to);
                if (max.contains(sub)){
                    return sub;
                }
            }
        }
        return null;
    }
}
