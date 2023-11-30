package vista;

import controlador.Controlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Vista extends JFrame {
    private JTable tabla;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JButton agregarBtn;
    private JButton editarBtn;
    private JButton eliminarBtn;
    private JPanel panelBotones;

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JButton getAgregarBtn() {
        return agregarBtn;
    }

    public JButton getEditarBtn() {
        return editarBtn;
    }

    public JButton getEliminarBtn() {
        return eliminarBtn;
    }

    public int getRowSelected() {
        return tabla.getSelectedRow();
    }

    public Vista() {
        // Configuración de la ventana principal
        setTitle("Contactos");
        setBounds(100, 100, 500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setResizable(false);
        inicializarVariables();

        // Configurar la visibilidad de la ventana
        setVisible(true);
    }

    private void inicializarVariables() {
        // Crear modelo de tabla con dos columnas
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Teléfono");

        // Crear tabla con el tableModel
        tabla = new JTable(tableModel);

        // Agregar barra de desplazamiento a la tabla y dimensiones
        scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(30, 30, 440, 400);
        getContentPane().add(scrollPane);

        // Crear botones
        agregarBtn = new JButton("Añadir");
        agregarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ponemos el cursor de la mano al pasar por el boton
        agregarBtn.setFocusable(false);

        editarBtn = new JButton("Editar");
        editarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ponemos el cursor de la mano al pasar por el boton
        editarBtn.setFocusable(false);

        eliminarBtn = new JButton("Eliminar");
        eliminarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ponemos el cursor de la mano al pasar por el boton
        eliminarBtn.setFocusable(false);

        // Agregar botones a un panel
        panelBotones = new JPanel();
        panelBotones.setBounds(0, 500, 500, 100);
        panelBotones.add(agregarBtn);
        panelBotones.add(editarBtn);
        panelBotones.add(eliminarBtn);

        // Agregar panel de botones a la parte inferior de la ventana
        getContentPane().add(panelBotones, BorderLayout.SOUTH);
    }

    public void establecerListeners(Controlador controlador) {
        agregarBtn.addActionListener(controlador);
    }
}
