package controlador;

import vista.Vista;
import vista.VistaAux;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {
    Vista vista;
    VistaAux vistaAux;

    public Controlador(Vista vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getAgregarBtn()) {
            if (vistaAux == null) {
                vistaAux = new VistaAux(this);
                vistaAux.setTitle("Añadir contacto");
                vistaAux.establecerListeners(this);
            } else {
                resetFields();
                vistaAux.setTitle("Añadir contacto");
                vistaAux.setVisible(true);
            }
        }

        if (e.getSource() == vistaAux.getOkBtn()) {
            String nombre = vistaAux.getCampoNombre().getText();
            String telefono = vistaAux.getCampoTelefono().getText();

            DefaultTableModel tableModel = vista.getTableModel();
            tableModel.addRow(new String[]{nombre, telefono});
            vistaAux.dispose();
            resetFields();
        }

        if (e.getSource() == vistaAux.getCancelarBtn()) {
            vistaAux.dispose();
        }

        if (e.getSource() == vista.getEditarBtn()) {
            int selectedRow = vista.getRowSelected();

            if (selectedRow >= 0) {
                if(vistaAux==null) {
                    vistaAux = new VistaAux(this);
                } else {
                    vistaAux.setVisible(true);
                }
                vistaAux.setTitle("Editar contacto");
                insertarDatosTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "Tiene que seleccionar un contacto.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (e.getSource() == vista.getEliminarBtn()) {
            deleteContact();
        }
    }

    public void resetFields() {
        vistaAux.getCampoNombre().setText(null);
        vistaAux.getCampoTelefono().setText(null);
    }

    public void deleteContact() {
        int filaSeleccionada = vista.getRowSelected();

        if (filaSeleccionada >= 0) {
            DefaultTableModel tableModel = vista.getTableModel();
            tableModel.removeRow(filaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(vista, "Selecciona un contacto para eliminar.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void insertarDatosTabla() {
        int selectedRow = vista.getRowSelected();

        if (selectedRow >= 0) {
            // Seleccionar la fila se encuentra en 'selectedRow'
            // Puedes acceder a los datos de esa fila a través del modelo de tabla
            String nombre = vista.getTableModel().getValueAt(selectedRow, 0).toString();
            String telefono = vista.getTableModel().getValueAt(selectedRow, 1).toString();

            // Asignar los valores a los campos de edición en vistaEditar
            vistaAux.getCampoNombre().setText(nombre);
            vistaAux.getCampoTelefono().setText(telefono);
        }
    }
}
