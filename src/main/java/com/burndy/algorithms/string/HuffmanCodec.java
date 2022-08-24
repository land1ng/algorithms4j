package com.burndy.algorithms.string;

import com.burndy.algorithms.Utils;
import com.burndy.algorithms.sort.MinPQ;
import com.burndy.algorithms.string.a.CompressCodec;
import com.burndy.algorithms.string.binary.BinaryIn;
import com.burndy.algorithms.string.binary.BinaryOut;
import lombok.RequiredArgsConstructor;

/**
 * TODO 有问题
 *
 * @author dingdong
 * @since 2021/4/25
 */
public class HuffmanCodec implements CompressCodec {

    // alphabet size of extended ASCII
    private static final int R = 256;

    /**
     * 压缩
     *
     * @param bin  输入流
     * @param bout 输出流
     */
    @Override
    public void compress(BinaryIn bin, BinaryOut bout) {
        char[] input = bin.readString().toCharArray();
        // tabulate frequency counts
        int[] freq = new int[R];
        for (char c : input) freq[c]++;
        // build Huffman trie
        Node root = buildTrie(freq);
        // build code table
        String[] st = buildCode(root);
        // print trie for decoder
        writeTrie(bout, root);
        // print number of bytes in original uncompressed message
        bout.write(input.length);
        // use Huffman code to encode input
        for (char c : input) {
            String code = st[c];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '0') {
                    bout.write(false);
                } else if (code.charAt(j) == '1') {
                    bout.write(true);
                } else throw new IllegalStateException("Illegal state");
            }
        }
        // close output stream
        bout.close();
    }

    // write bitstring-encoded trie to standard output
    private static void writeTrie(BinaryOut bout, Node x) {
        if (x.isLeaf()) {
            bout.write(true);
            bout.write(x.ch, 8);
            return;
        }
        bout.write(false);
        writeTrie(bout, x.left);
        writeTrie(bout, x.right);
    }

    // 通过前缀码字段查找树构建编译表
    private static String[] buildCode(Node root) {
        String[] st = new String[R];
        buildCode(st, root, "");
        return st;
    }

    private static void buildCode(String[] st, Node x, String s) {
        if (x.isLeaf()) {
            st[x.ch] = s;
            return;
        }
        buildCode(st, x.left, s + '0');
        buildCode(st, x.right, s + '1');
    }

    // 构造一棵霍夫曼编码单词查找树
    private static Node buildTrie(int[] freq) {
        MinPQ<Node> minPQ = MinPQ.of();
        for (char c = 0; c < R; c++)
            if (freq[c] > 0) minPQ.insert(new Node(c, freq[c], null, null));
        while (minPQ.size() > 1) {
            // 合并两棵频率最小的树
            Node l = minPQ.delMin();
            Node r = minPQ.delMin();
            Node parent = new Node('\0', l.freq + r.freq, l, r);
            minPQ.insert(parent);
        }
        return minPQ.delMin();
    }

    /**
     * 解压缩
     *
     * @param bin  输入流
     * @param bout 输出流
     */
    @Override
    public void uncompress(BinaryIn bin, BinaryOut bout) {
        // read in Huffman trie from input stream
        Node root = readTrie(bin);
        // number of bytes to write
        int length = bin.readInt();
        // decode using the Huffman trie
        for (int i = 0; i < length; i++) {
            Node x = root;
            while (!x.isLeaf()) {
                boolean bit = bin.readBoolean();
                if (bit) x = x.right;
                else x = x.left;
            }
            bout.write(x.ch, 8);
        }
        bout.close();
    }

    private static Node readTrie(BinaryIn bin) {
        boolean isLeaf = bin.readBoolean();
        if (isLeaf) {
            return new Node(bin.readChar(), -1, null, null);
        } else {
            return new Node('\0', -1, readTrie(bin), readTrie(bin));
        }
    }

    // 霍夫曼单词查找树中的结点
    @RequiredArgsConstructor
    private static class Node implements Comparable<Node> {

        private final char ch;  // 内部结点不会使用该变量
        private final int freq; // 展开过程不会使用该变量
        private final Node left;
        private final Node right;

        public boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    public static void main(String[] args) {
        CompressCodec codec = new HuffmanCodec();
        codec.compress(Utils.locateSampleFile("3D拓扑图.png"), Utils.locateSampleFile("3D拓扑图-压缩.png"));
        codec.uncompress(Utils.locateSampleFile("3D拓扑图-压缩.png"), Utils.locateSampleFile("3D拓扑图-解压缩.png"));
    }
}
