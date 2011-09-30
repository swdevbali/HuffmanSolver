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
    List<String> sortedChar = new ArrayList();
    BinarySearchTree huffmanTree = new BinarySearchTree();
    
    
    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    Huffman(String originalText) {
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
            System.out.println(k + " " + map.get(k));
        }
        return keys;
    }

    void buildTree() {
        //#1 take two topmost item
        sortedChar = sort(mapFreq);
        String a = sortedChar.get(0);
        String b = sortedChar.get(1);
        String c = a+b;
        sortedChar.remove(0);
        sortedChar.remove(0);
        
        int freqA = mapFreq.get(a);
        int freqB = mapFreq.get(b);
        int freqC = freqA + freqB;
        mapFreq.remove(a);
        mapFreq.remove(b);
        
        System.out.println(a + " dijadikan tree dgn " + b);
        System.out.println(freqA + " dijadikan tree dgn " + freqB);
        
        HuffmanNode huffA = new HuffmanNode(a,freqA);
        HuffmanNode huffB = new HuffmanNode(b,freqB);        
        HuffmanNode huffC = new HuffmanNode(c,freqC);
        huffmanTree.insert(huffA);
        huffmanTree.insert(huffB);
        huffmanTree.insert(huffC);
        
        HuffmanNode result = (HuffmanNode) huffmanTree.find(huffB);
        System.out.println(result.token);
        
        
    }

    class ValueComparator implements Comparator {

        Map base;

        public ValueComparator(Map base) {
            this.base = base;
        }

        public int compare(Object left, Object right) {
            String leftKey = (String) left;
            String rightKey = (String) right;

            Integer leftValue = (Integer) base.get(leftKey);
            Integer rightValue = (Integer) base.get(rightKey);
            return leftValue.compareTo(rightValue);
        }
    }
    
    class HuffmanNode implements Comparable
    {
        String token;
        int frequency;
        
        public HuffmanNode(String token, int frequency)
        {
            this.token = token;
            this.frequency = frequency;
        }
        
        @Override
        public int compareTo(Object t) {
            HuffmanNode huffmanNode = (HuffmanNode) t;
            if(huffmanNode.frequency<frequency) return -1;
            else if(huffmanNode.frequency==frequency) return 0;
            else return 1;
        }
        
    }
}
