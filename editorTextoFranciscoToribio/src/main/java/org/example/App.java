package org.example;

import com.formdev.flatlaf.FlatLightLaf;
import org.example.vista.Ventana;

import javax.swing.*;

public class App
{
    public static void main(String[] args)
    {
        try {
            UIManager.setLookAndFeel( new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println( "Failed to initialize LaF" );
        }
        SwingUtilities.invokeLater(() -> new Ventana().setVisible(true));
    }
}