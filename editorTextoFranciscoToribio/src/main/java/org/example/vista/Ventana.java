package org.example.vista;

import org.example.controlador.Controller;
import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private final Controller controlador;
    private JMenuItem menuArchivoOpcionNuevo, menuArchivoOpcionAbrir, menuArchivoOpcionGuardar,
            menuArchivoOpcionGuardarComo, menuArchivoOpcionImprimir, menuArchivoOpcionSalir, menuEdicionOpcionDeshacer,
            menuEdicionOpcionCopiar, menuEdicionOpcionCortar, menuEdicionOpcionPegar, menuEdicionOpcionEliminar,
            menuAyudaOpcionAcercaDe, menuVerOpcionBarraEstado, menuAyudaOpcionVerAyuda;

    private JTextArea pantalla;
    private JLabel barraEstado;
    public Ventana()
    {
        initComponents();
        initActions();
        controlador = new Controller(menuArchivoOpcionNuevo, menuArchivoOpcionAbrir, menuArchivoOpcionGuardar,
                menuArchivoOpcionGuardarComo, menuArchivoOpcionImprimir, menuArchivoOpcionSalir, menuEdicionOpcionDeshacer,
                menuEdicionOpcionCopiar, menuEdicionOpcionCortar, menuEdicionOpcionPegar, menuEdicionOpcionEliminar,
                menuAyudaOpcionAcercaDe, menuVerOpcionBarraEstado, menuAyudaOpcionVerAyuda, pantalla, barraEstado);
    }

    private void initComponents()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800,500));

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500,500));
        setTitle("Bloc de notas de Fran");


        BorderLayout layout = new BorderLayout();
        pantalla = new JTextArea();

        JMenuBar menu = new JMenuBar();
        barraEstado = new JLabel();

        JMenu menuArchivo = new JMenu();
        JMenu menuEdicion = new JMenu();
        JMenu menuVer = new JMenu();
        JMenu menuAyuda = new JMenu();

        menuArchivo.setText("Archivo");

        menuArchivoOpcionNuevo = new JMenuItem();
        menuArchivoOpcionNuevo.setText("Nuevo");
        menuArchivo.add(menuArchivoOpcionNuevo);

        menuArchivoOpcionAbrir = new JMenuItem();
        menuArchivoOpcionAbrir.setText("Abrir");
        menuArchivo.add(menuArchivoOpcionAbrir);

        menuArchivoOpcionGuardar = new JMenuItem();
        menuArchivoOpcionGuardar.setText("Guardar");
        menuArchivo.add(menuArchivoOpcionGuardar);

        menuArchivoOpcionGuardarComo = new JMenuItem();
        menuArchivoOpcionGuardarComo.setText("Guardar como");
        menuArchivo.add(menuArchivoOpcionGuardarComo);

        menuArchivoOpcionImprimir = new JMenuItem();
        menuArchivoOpcionImprimir.setText("Imprimir");
        menuArchivo.add(menuArchivoOpcionImprimir);

        menuArchivoOpcionSalir = new JMenuItem();
        menuArchivoOpcionSalir.setText("Salir");
        menuArchivo.add(menuArchivoOpcionSalir);

        menuEdicion.setText("Edicion");

        menuEdicionOpcionDeshacer = new JMenuItem();
        menuEdicionOpcionDeshacer.setText("Deshacer");
        menuEdicion.add(menuEdicionOpcionDeshacer);

        menuEdicionOpcionCopiar = new JMenuItem();
        menuEdicionOpcionCopiar.setText("Copiar");
        menuEdicion.add(menuEdicionOpcionCopiar);

        menuEdicionOpcionCortar = new JMenuItem();
        menuEdicionOpcionCortar.setText("Cortar");
        menuEdicion.add(menuEdicionOpcionCortar);

        menuEdicionOpcionPegar = new JMenuItem();
        menuEdicionOpcionPegar.setText("Pegar");
        menuEdicion.add(menuEdicionOpcionPegar);

        menuEdicionOpcionEliminar = new JMenuItem();
        menuEdicionOpcionEliminar.setText("Eliminar");
        menuEdicion.add(menuEdicionOpcionEliminar);

        menuVer.setText("Ver");

        menuVerOpcionBarraEstado = new JMenuItem();
        menuVerOpcionBarraEstado.setText("Barra de estado");
        menuVer.add(menuVerOpcionBarraEstado);

        menuAyuda.setText("Ayuda");

        menuAyudaOpcionAcercaDe = new JMenuItem();
        menuAyudaOpcionAcercaDe.setText("Acerca de");
        menuAyuda.add(menuAyudaOpcionAcercaDe);

        menuAyudaOpcionVerAyuda = new JMenuItem();
        menuAyudaOpcionVerAyuda.setText("Ver ayuda");
        menuAyuda.add(menuAyudaOpcionVerAyuda);

        menu.add(menuArchivo);
        menu.add(menuEdicion);
        menu.add(menuVer);
        menu.add(menuAyuda);

        this.setJMenuBar(menu);
        this.setLayout(layout);
        this.add(pantalla,BorderLayout.CENTER);
        this.add(barraEstado,BorderLayout.SOUTH);

        pack();
    }
    private void initActions()
    {
        menuArchivoOpcionNuevo.addActionListener(e -> controlador.nuevo());
        menuArchivoOpcionAbrir.addActionListener(e -> controlador.abrir());
        menuArchivoOpcionGuardar.addActionListener(e -> controlador.opcionGuardar());
        menuArchivoOpcionGuardarComo.addActionListener(e -> controlador.guardarComo());
        menuArchivoOpcionImprimir.addActionListener(e -> controlador.imprimir());
        menuArchivoOpcionSalir.addActionListener(e -> controlador.salir());
        menuEdicionOpcionDeshacer.addActionListener(e -> controlador.deshacer());
        menuEdicionOpcionCopiar.addActionListener(e -> controlador.copiar());
        menuEdicionOpcionCortar.addActionListener(e -> controlador.cortar());
        menuEdicionOpcionPegar.addActionListener(e -> controlador.pegar());
        menuEdicionOpcionEliminar.addActionListener(e -> controlador.eliminar());
        menuAyudaOpcionAcercaDe.addActionListener(e -> controlador.acercaDe());
        menuVerOpcionBarraEstado.addActionListener(e -> controlador.barraEstado());
        menuAyudaOpcionVerAyuda.addActionListener(e -> controlador.verAyuda());
    }
}
