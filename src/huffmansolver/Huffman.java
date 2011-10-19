/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmansolver;

import huffmansolver.bst.BinarySearchTree;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author ekowibowo
 */
class Huffman {

    private String originalText;
    HashMap<String, Integer> mapFreq = new HashMap<String, Integer>();
    List<String> sortedMap = new ArrayList();
    BinarySearchTree huffmanTree = new BinarySearchTree();

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    Huffman(String originalText) {
        //build map of character frequency
        this.originalText = originalText;
        //borrowed from http://www.exampledepot.com/egs/java.text/StrIter.html
        CharacterIterator it = new StringCharacterIterator(originalText);
        for (char ch = it.first(); ch != CharacterIterator.DONE; ch = it.next()) {
            if (mapFreq.containsKey(ch + "")) {
                Integer freq = mapFreq.get(ch + "");
                freq++;
                mapFreq.put(ch + "", freq);
            } else {
                Integer freq = 1;
                mapFreq.put(ch + "", freq);
            }
        }
    }

    private List<String> sort(Map map) {
        //borrowed from http://snipplr.com/view.php?codeview&id=2789
        List<String> keys = new ArrayList(map.keySet());
        Collections.sort(keys, new ValueComparator(map));
        for (Iterator i = keys.iterator(); i.hasNext();) {
            Object k = i.next();
            System.out.println(map.get(k) + ":" + k);
        }
        return keys;
    }

    void buildTree() {
        //sort map by its frequency : lowest first
        sortedMap = sort(mapFreq);
        while (sortedMap.size() != 1) {

            String a = sortedMap.get(0);//get the two topmost item from the sorted map
            String b = sortedMap.get(1);
            String c = a + "," + b;//new key for map
            sortedMap.remove(0);//remove that two topmost item
            sortedMap.remove(0);

            int freqA = mapFreq.get(a);//get the frequency
            int freqB = mapFreq.get(b);
            int freqC = freqA + freqB;//calculate total freq for this combination
            mapFreq.remove(a);
            mapFreq.remove(b);//really missing from the map of freq


            //add the two topmost item above, into the new/existing tree
            System.out.println(freqA + ":" + a + " dijadikan tree dgn " + freqB + ":" + b + " = " + freqC + ":" + c);

            HuffmanNodeInsert huffA = new HuffmanNodeInsert(a, freqA);
            HuffmanNodeInsert huffB = new HuffmanNodeInsert(b, freqB);
            HuffmanNodeInsert huffC = new HuffmanNodeInsert(c, freqC);
            huffmanTree.insert(huffA);
            huffmanTree.insert(huffB);
            huffmanTree.insert(huffC);

            //insert the new node into mapFreq
            System.out.println("the new sorted map by freq");
            mapFreq.put(c, freqC);
            sortedMap = sort(mapFreq);
        }
        //sortedMap = sort(mapFreq);


        //HuffmanNode result = (HuffmanNode) huffmanTree.find(huffB);
        //System.out.println(result.token);


    }

    void find(String token) {
        HuffmanNodeFind huffToken = new HuffmanNodeFind(token);
        System.out.print("Code = ");
        huffmanTree.find(huffToken);
        System.out.println();
    }

    class ValueComparator implements Comparator {

        Map base;

        public ValueComparator(Map base) {
            this.base = base;
        }

        @Override
        public int compare(Object left, Object right) {
            String leftKey = (String) left;
            String rightKey = (String) right;

            Integer leftValue = (Integer) base.get(leftKey);
            Integer rightValue = (Integer) base.get(rightKey);
            return leftValue.compareTo(rightValue);
        }
    }

    // use to be inserted into the tree
    class HuffmanNodeInsert implements Comparable {

        String token;
        int frequency;

        public HuffmanNodeInsert(String token, int frequency) {
            this.token = token;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Object t) {
            HuffmanNodeInsert huffmanNode = (HuffmanNodeInsert) t;
            if (huffmanNode.frequency < frequency) {
                return -1;
            } else if (huffmanNode.frequency == frequency) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    class HuffmanNodeFind implements Comparable {

        String token;

        public HuffmanNodeFind(String token) {
            this.token = token;
        }

        @Override
        public int compareTo(Object t) {
            HuffmanNodeInsert insert = (HuffmanNodeInsert) t;
            String token = insert.token;

            //NEXT : error disini, gmn nih> :)
            HuffmanNodeFind huffmanNode = (HuffmanNodeFind) t;
            if (!huffmanNode.token.contains(token)) {
                return -1;
            } else if (huffmanNode.token.equals(token)) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
