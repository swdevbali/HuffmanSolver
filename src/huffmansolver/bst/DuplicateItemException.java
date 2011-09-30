/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmansolver.bst;

/**
 *
 * @author ekowibowo
 */

/**
 * Exception class for duplicate item errors
 * in search tree insertions.
 * @author Mark Allen Weiss
 */
public class DuplicateItemException extends RuntimeException {
    /**
     * Construct this exception object.
     */
    public DuplicateItemException( ) {
        super( );
    }
    /**
     * Construct this exception object.
     * @param message the error message.
     */
    public DuplicateItemException( String message ) {
        super( message );
    }
}
