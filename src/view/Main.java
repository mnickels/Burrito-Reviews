/*
 * TCSS 445 B - Group Project
 */
package view;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This is the Main class that will run the front end of the program.
 * 
 * @author Thomas Schmidt
 * @version 1.0
 */
public final class Main {

    /**
     * This is a private constructor that prevents instantiation of this class.
     */
    private Main() {
        throw new IllegalStateException();
    }

    /**
     * Set the look and feel for the GUI program.
     */
    private static void setLookAndFeel() {       
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");        
        } catch (final UnsupportedLookAndFeelException e) {
            System.out.println("UnsupportedLookAndFeelException");
        } catch (final ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        } catch (final InstantiationException e) {
            System.out.println("InstantiationException");
        } catch (final IllegalAccessException e) {
            System.out.println("IllegalAccessException");
        }        
    }
    
    /**
     * This is the main method that invokes the GUI.
     * Command line arguments are ignored.
     * 
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        setLookAndFeel();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                    new GUI().start();   
            }
        });
    }
}