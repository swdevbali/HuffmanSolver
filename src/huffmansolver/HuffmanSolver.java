/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmansolver;

/**
 *
 * @author ekowibowo
 */
public class HuffmanSolver {
    public static final String KASUS_ENCEP = "temui aku di kampus jam 7 pagi";
    public static final String KASUS_REFERENSI = "111112222222333333333344444444444444455555555555555555555666666666666666666666666666666666666666666666";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Huffman huffman = new Huffman(KASUS_ENCEP);
        huffman.buildTree();
        huffman.find("t");
    }
}
