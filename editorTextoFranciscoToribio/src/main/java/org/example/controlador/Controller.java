package org.example.controlador;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.print.PrinterException;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class Controller extends Component {

    private final JMenuItem menuArchivoOpcionNuevo;
    private final JMenuItem menuArchivoOpcionAbrir;
    private final JMenuItem menuArchivoOpcionGuardar;
    private final JMenuItem menuArchivoOpcionGuardarComo;
    private final JMenuItem menuArchivoOpcionImprimir;
    private final JMenuItem menuEdicionOpcionDeshacer;
    private final JMenuItem menuEdicionOpcionCopiar;
    private final JMenuItem menuEdicionOpcionCortar;
    private JMenuItem menuEdicionOpcionPegar;
    private JMenuItem menuEdicionOpcionEliminar;
    private JMenuItem menuAyudaOpcionAcercaDe;
    private JMenuItem menuVerOpcionBarraEstado;
    private JMenuItem menuAyudaOpcionVerAyuda;
    private final JTextArea pantalla;
    private final JLabel barraEstado;
    private Clipboard portaPapeles;
    private UndoManager deshacer;

    public Controller(JMenuItem menuArchivoOpcionNuevo, JMenuItem menuArchivoOpcionAbrir, JMenuItem menuArchivoOpcionGuardar,
                      JMenuItem menuArchivoOpcionGuardarComo, JMenuItem menuArchivoOpcionImprimir, JMenuItem menuArchivoOpcionSalir,
                      JMenuItem menuEdicionOpcionDeshacer, JMenuItem menuEdicionOpcionCopiar, JMenuItem menuEdicionOpcionCortar,
                      JMenuItem menuEdicionOpcionPegar, JMenuItem menuEdicionOpcionEliminar, JMenuItem menuAyudaOpcionAcercaDe,
                      JMenuItem menuVerOpcionBarraEstado, JMenuItem menuAyudaOpcionVerAyuda, JTextArea pantalla, JLabel barraEstado) {
        this.menuArchivoOpcionNuevo = menuArchivoOpcionNuevo;
        this.menuArchivoOpcionAbrir = menuArchivoOpcionAbrir;
        this.menuArchivoOpcionGuardar = menuArchivoOpcionGuardar;
        this.menuArchivoOpcionGuardarComo = menuArchivoOpcionGuardarComo;
        this.menuArchivoOpcionImprimir = menuArchivoOpcionImprimir;
        this.menuEdicionOpcionDeshacer = menuEdicionOpcionDeshacer;
        this.menuEdicionOpcionCopiar = menuEdicionOpcionCopiar;
        this.menuEdicionOpcionCortar = menuEdicionOpcionCortar;
        this.menuEdicionOpcionPegar = menuEdicionOpcionPegar;
        this.menuEdicionOpcionEliminar = menuEdicionOpcionEliminar;
        this.menuAyudaOpcionAcercaDe = menuAyudaOpcionAcercaDe;
        this.menuVerOpcionBarraEstado = menuVerOpcionBarraEstado;
        this.menuAyudaOpcionVerAyuda = menuAyudaOpcionVerAyuda;
        this.pantalla = pantalla;
        this.barraEstado = barraEstado;
    }

    public void nuevo() {
        if (pantalla.getText().length() > 0) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios?",
                    "Guardar", JOptionPane.YES_NO_CANCEL_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                guardar();
            } else if (opcion == JOptionPane.NO_OPTION) {
                pantalla.setText("");
            }
        }
    }


    public void abrir() {
        if (pantalla.getText().length() > 0) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios?",
                    "Guardar", JOptionPane.YES_NO_CANCEL_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                guardar();
                pantalla.setText("");
            } else if (opcion == JOptionPane.NO_OPTION) {
                abrirArchivo();
            }
        } else {
            abrirArchivo();
        }
    }

    private void abrirArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto", "txt"));
        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();
            try {
                String s1;
                var fr = new FileReader(fichero);
                var br = new BufferedReader(fr);
                var sl = new StringBuilder(br.readLine());
                while ((s1 = br.readLine()) != null) {
                    sl.append("\n").append(s1);
                }
                pantalla.setText(sl.toString());
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(fileChooser, evt.getMessage());
            }
        }
    }

    public void opcionGuardar() {
        if (pantalla.getText().length() > 0)
            guardar();
    }

    private void guardar() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto", "txt"));
        int seleccion = fileChooser.showSaveDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();
            try {
                FileWriter fw = new FileWriter(fichero);
                fw.write(pantalla.getText());
                fw.close();
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(fileChooser, evt.getMessage());
            }
        }
    }

    public void guardarComo() {
        if (pantalla.getText().length() > 0) {
            guardar();
        }
    }


    public void imprimir() {
        try {
            pantalla.print();
        } catch (PrinterException e) {
            throw new RuntimeException(e);
        }
    }

    public void copiar() {
        portaPapeles = Toolkit.getDefaultToolkit().getSystemClipboard();
        if (pantalla.getSelectedText() != null) {
            StringSelection seleccion = new StringSelection("" + pantalla.getSelectedText());
            portaPapeles.setContents(seleccion, seleccion);
        }
    }

    public void cortar() {
        portaPapeles = Toolkit.getDefaultToolkit().getSystemClipboard();
        if (pantalla.getSelectedText() != null) {
            StringSelection seleccion = new StringSelection("" + pantalla.getSelectedText());
            portaPapeles.setContents(seleccion, seleccion);
            pantalla.replaceRange("", pantalla.getSelectionStart(), pantalla.getSelectionEnd());
        }
    }

    public void pegar() {
        portaPapeles = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable datos = portaPapeles.getContents(null);
        try {
            if (datos != null && datos.isDataFlavorSupported(DataFlavor.stringFlavor))
                pantalla.replaceSelection("" + datos.getTransferData(DataFlavor.stringFlavor));
        } catch (UnsupportedFlavorException | IOException ex) {
            System.err.println(ex);
        }
    }

    public void eliminar() {
        if (pantalla.getSelectedText() != null) {
            pantalla.replaceRange("", pantalla.getSelectionStart(), pantalla.getSelectionEnd());
        }
    }

    public void acercaDe() {
        JOptionPane.showMessageDialog(null, "Creado por Fran Toribio.", "Acerca de:",
                JOptionPane.INFORMATION_MESSAGE);

    }

    public void barraEstado() {
        pantalla.addCaretListener(e -> {
            try {
                barraEstado.setText("Línea: " + pantalla.getLineOfOffset(pantalla.getCaretPosition()) + "  Columna: "
                        + (pantalla.getCaretPosition() - pantalla.getLineStartOffset(pantalla.getLineOfOffset(pantalla.getCaretPosition()))));
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void verAyuda() {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI("https://www.google.com"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void salir() {
        if (pantalla.getText().length() > 0) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios?",
                    "Guardar", JOptionPane.YES_NO_CANCEL_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                guardar();
            }
        }
        System.exit(0);
    }


    public void deshacer()
    {
        //no he podido hacerlo
    }
}

