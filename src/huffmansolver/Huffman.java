/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmansolver;

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

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    Huffman(String originalText) {
        this.originalText = originalText;
        
        //#1 = sorted by freq
        HashMap<String, Integer> lang = new HashMap<String, Integer>();
        ValueComparator bvc = new ValueComparator(lang);
        TreeMap<String, Integer> sorted_map = new TreeMap(bvc);

        //borrowed from http://www.exampledepot.com/egs/java.text/StrIter.html
        CharacterIterator it = new StringCharacterIterator(originalText);
        for (char ch = it.first(); ch != CharacterIterator.DONE; ch = it.next()) {
            if (lang.containsKey(ch + "")) {
                Integer freq = lang.get(ch + "");
                freq++;
                lang.put(ch + "", freq);
            } else {
                Integer freq = 1;
                lang.put(ch + "", freq);
            }
        }


        //borrowed from http://snipplr.com/view.php?codeview&id=2789
        List keys = new ArrayList(lang.keySet());

        //Sort keys by values.
        final Map langForComp = lang;
        Collections.sort(keys,
                new Comparator() {

                    public int compare(Object left, Object right) {
                        String leftKey = (String) left;
                        String rightKey = (String) right;

                        Integer leftValue = (Integer) langForComp.get(leftKey);
                        Integer rightValue = (Integer) langForComp.get(rightKey);
                        return rightValue.compareTo(leftValue);
                    }
                });

        //List the key value
        for (Iterator i = keys.iterator(); i.hasNext();) {
            Object k = i.next();
            System.out.println(k + " " + lang.get(k));
        }
        
        //#2 = create huffman tree
    }

    class ValueComparator implements Comparator {

        Map base;

        public ValueComparator(Map base) {
            this.base = base;
        }

        public int compare(Object a, Object b) {

            if ((Integer) base.get(a) < (Integer) base.get(b)) {
                return 1;
            } else if ((Integer) base.get(a) == (Integer) base.get(b)) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
