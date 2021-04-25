package com.dranie.algorithms.string.a;

import com.dranie.algorithms.Utils;
import com.dranie.algorithms.string.binary.BinaryIn;
import com.dranie.algorithms.string.binary.BinaryOut;
import com.dranie.algorithms.string.binary.Binarys;

/**
 * 游程编码
 * <p>
 * 广泛用于位图，因为随着分辨率的提高它的效果也会大大的提高。
 *
 * @author dingdong
 * @since 2021/4/25
 */
public class RunLengthCompressCodec implements CompressCodec {

    /**
     * 压缩
     *
     * @param bTn  输入流
     * @param bOut 输出流
     */
    @Override
    public void compress(BinaryIn bTn, BinaryOut bOut) {
        char cnt = 0;
        boolean b, old = false;
        while (!bTn.isEmpty()) {
            b = bTn.readBoolean();
            if (b != old) {
                bOut.write(cnt);
                cnt = 0;
                old = !old;
            } else {
                if (cnt == 255) {
                    bOut.write(cnt);
                    cnt = 0; // TODO 这个0是做什么的？
                    bOut.write(cnt);
                }
            }
            cnt++;
        }
        bOut.write(cnt);
        bOut.close();
    }

    /**
     * 解压缩
     *
     * @param bTn  输入流
     * @param bOut 输出流
     */
    @Override
    public void uncompress(BinaryIn bTn, BinaryOut bOut) {
        boolean b = false;
        while (!bTn.isEmpty()) {
            char cnt = bTn.readChar();
            for (int i = 0; i < cnt; i++) {
                bOut.write(b);
            }
            b = !b;
        }
        bOut.close();
    }

    public static void main(String[] args) {
        BinaryIn bin1 = Binarys.newBinaryIn(Utils.locateSampleFile("3D拓扑图.png"));
        BinaryOut bout1 = Binarys.newBinaryOut(Utils.locateSampleFile("3D拓扑图-压缩.png"));
        CompressCodec codec = new RunLengthCompressCodec();
        // 对于一般文件，“压缩”后变的更大了！
        codec.compress(bin1, bout1);
        BinaryIn bin2 = Binarys.newBinaryIn(Utils.locateSampleFile("3D拓扑图-压缩.png"));
        BinaryOut bout2 = Binarys.newBinaryOut(Utils.locateSampleFile("3D拓扑图-解压缩.png"));
        codec.uncompress(bin2, bout2);
    }
}
