package meetcode;

/**
 * 计算 e，要求精度在 10^-5 次幂。
 * <p>
 * e1 = 1/1! + 1/2! + ... + 1/k! + ... + 1/n!
 * <p>
 * e0 = 1/1! + 1/2! + ... + 1/k!
 * <p>
 * e1 - e0 = 1/(k+1)! + 1/(k+2)! + ... + 1/n! < 10^-5
 * <p>
 * max(e1 - e0) < 10^-5
 * <p>
 * e1 - e0 < 10^-5
 *
 * @author dingdong
 * @since 2021/4/26
 */
public class E {

    public static void main(String[] args) {
        System.out.println(eval());
    }

    public static double eval() {
        int n = 1;
        int k = 1;
        double e = 1;
        double a = 0.00001;
        do {
            k *= n;
            n++;
            e += 1.0 / k;
        } while (1.0 / k >= a);
        return e;
    }
}
