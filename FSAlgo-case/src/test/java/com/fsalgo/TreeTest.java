package com.fsalgo;

import com.fsalgo.core.tree.AvlTree;
import com.fsalgo.core.tree.BinaryIndexedTree;
import com.fsalgo.core.tree.HuffmanTree;
import org.junit.Test;

import java.util.*;

/**
 * @Author: root
 * @Date: 2023/2/25 14:03
 * @Description:
 */
public class TreeTest {

    @Test
    public void AvlTreeDemo() {
        int[] nums = {3, 1, 2, 4, 6, 0, 9, 7, 8, 5};

        AvlTree<Integer> avlTree = new AvlTree<>();
        AvlTree.Node<Integer> root = null;

        for (int num : nums) {
            root = avlTree.add(root, num);
        }

        System.out.println(root);
        System.out.println(avlTree.isBalance(root));
    }

    @Test
    public void HuffmanTreeDemo() {
        HuffmanTree<Character> huffmanTree = new HuffmanTree<>();

        String content = "HuffmanTree";

        // 创建huffman树
        HuffmanTree.Node<Character> treeNode = huffmanTree.createHuffmanTree(content.chars().mapToObj(c -> (char) c).toArray(Character[]::new));

        Map<Character, String> huffmanCodeMap = new HashMap<>(16);
        huffmanTree.huffmanCode(treeNode, huffmanCodeMap, "");

        for (Character code : huffmanCodeMap.keySet()) {
            System.out.print("[" + code + ":" + huffmanCodeMap.get(code) + "], ");
        }
        System.out.println();

        // 字符串转huffman编码
        List<Byte> hfCode = new ArrayList<>();
        for (int i = 0; i < content.length(); i++) {
            String code = huffmanCodeMap.get(content.charAt(i));
            for (int j = 0; j < code.length(); j++) {
                hfCode.add((byte) (code.charAt(j) - 48));
            }
        }
        System.out.println("huffman编码压缩后的二进制长度：");
        System.out.println(hfCode.toString().replace(", ", "").replace("[", "").replace("]", ""));

        // huffman编码还原字符串
        StringBuilder hfDecode = new StringBuilder();
        huffmanTree.huffmanDecode(treeNode, treeNode, hfCode, 0, hfDecode);
        System.out.println("huffman编码还原字符串：\n" + hfDecode);

        // 转ascii转二进制
        System.out.println(Arrays.toString(content.getBytes()));
        System.out.println("常规转二进制长度：");
        for (int i = 0; i < content.length(); i++) {
            System.out.print(Integer.toBinaryString(content.getBytes()[i]));
        }
    }

    @Test
    public void BinaryIndexedTree() {
        double[] nums = {new Double(1.0), new Integer(2), new Float(3.0), 4, 5, 6, 7, 8};
        BinaryIndexedTree bit = new BinaryIndexedTree(nums);

        bit.updateNodeVal(4, 6);
        bit.updateNodeVal(0, 2);
        bit.updateNodeVal(0, 9);
        System.out.println(bit.sumRange(4, 4));
        bit.updateNodeVal(3, 8);
        System.out.println(bit.sumRange(0, 4));
        bit.updateNodeVal(4, 1);
        System.out.println(bit.sumRange(0, 3));
        System.out.println(bit.sumRange(0, 4));

        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(bit.getTree()));


    }
}
