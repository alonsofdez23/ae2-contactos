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
                vistaAux.setVisible(true);
            } else {
                resetFields();
                vistaAux.setTitle("Añadir contacto");
                vistaAux.setVisible(true);
            }
        }

        if (e.getSource() == vistaAux.getOkBtn() && "Añadir contacto".equals(vistaAux.getTitle())) {
            insertarNewDatosTabla();
            vistaAux.dispose();
            resetFields();
        } else if (e.getSource() == vistaAux.getOkBtn() && "Editar contacto".equals(vistaAux.getTitle())) {
            System.out.println("Entra por aqui");
            insertarDatosTabla();
            vistaAux.dispose();
            resetFields();
        }

        if (e.getSource() == vistaAux.getCancelarBtn()) {
            vistaAux.dispose();
        }

        if (e.getSource() == vista.getEditarBtn()) {
            vistaAux.setTitle("Editar contacto");

            int selectedRow = vista.getRowSelected();

            if (!(selectedRow >= 0)) {
                JOptionPane.showMessageDialog(vista, "Tiene que seleccionar un contacto.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                extraerDatosRow();
                vistaAux.setVisible(true);
            }

//            if (selectedRow >= 0) {
//                if(vistaAux == null) {
//                    vistaAux = new VistaAux(this);
//                } else {
//                    vistaAux.setVisible(true);
//                }
//                vistaAux.setTitle("Editar contacto");
//                extraerDatosRow();
//                insertarDatosTabla();
//            } else {
//                JOptionPane.showMessageDialog(vista, "Tiene que seleccionar un contacto.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
//            }
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

    public void insertarNewDatosTabla() {
        String nombre = vistaAux.getCampoNombre().getText();
        String telefono = vistaAux.getCampoTelefono().getText();

        DefaultTableModel tableModel = vista.getTableModel();
        tableModel.addRow(new String[]{nombre, telefono});
    }

    public void insertarDatosTabla() {
        int selectedRow = vista.getRowSelected();

        String nombre = vistaAux.getCampoNombre().getText();
        String telefono = vistaAux.getCampoTelefono().getText();

        if (selectedRow >= 0) {
            vista.getTableModel().setValueAt(nombre, selectedRow, 0);
            vista.getTableModel().setValueAt(telefono, selectedRow, 1);
        }
    }

    public void extraerDatosRow() {
        int selectedRow = vista.getRowSelected();

        System.out.println(vista.getRowSelected());

        String selectedNombre = vista.getTableModel().getValueAt(selectedRow, 0).toString();
        String selectedTelefono = vista.getTableModel().getValueAt(selectedRow, 1).toString();

        vistaAux.getCampoNombre().setText(selectedNombre);
        vistaAux.getCampoTelefono().setText(selectedTelefono);

//        String editNombre = vistaAux.getCampoNombre().getText();
//        String editTelefono = vistaAux.getCampoTelefono().getText();
//
//        vista.getTableModel().setValueAt(editNombre, selectedRow, 0);
//        vista.getTableModel().setValueAt(editTelefono, selectedRow, 1);
    }
}
