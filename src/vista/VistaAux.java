package vista;

import controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VistaAux extends JDialog {
    private JLabel nombre;
    private JLabel telefono;
    private JButton okBtn;
    private JButton cancelarBtn;
    private JTextField campoNombre;
    private JTextField campoTelefono;
    private Controlador controlador;

    public JButton getOkBtn() {
        return okBtn;
    }

    public JButton getCancelarBtn() {
        return cancelarBtn;
    }

    public JTextField getCampoNombre() {
        return campoNombre;
    }

    public JTextField getCampoTelefono() {
        return campoTelefono;
    }

    public VistaAux(Controlador controlador) {
        // Crear la ventana y establecerla
        setTitle("Añadir Contacto");
        setBounds(200, 200, 400, 180);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setLayout(null); // No se recomienda, pero se mantiene por compatibilidad
        setResizable(false);

        inicializarVariables();

        setVisible(true);
    }

    private void inicializarVariables() {
        // Crear los componentes y configurarlos
        nombre = new JLabel("Nombre");
        nombre.setBounds(60, 20, 80, 20);

        campoNombre = new JTextField();
        campoNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int key = e.getKeyChar();
                // Donde empiezan y terminan los numeros en asci
                boolean mayusculas = key >= 65 && key <= 90;
                boolean minusculas = key >= 97 && key <= 122;
                boolean espacio = key == 32;
                // letras con acento en mayúsculas y minúsculas
                boolean vocalesAcentuadas = (key == 'á' || key == 'é' || key == 'í' || key == 'ó' || key == 'ú' ||
                        key == 'Á' || key == 'É' || key == 'Í' || key == 'Ó' || key == 'Ú');
                // letra ñ
                boolean nN = (key == 'ñ' || key == 'Ñ');
                // Si se introduce algo que no sean letras o espacio
                if (!(mayusculas || minusculas || espacio || vocalesAcentuadas || nN)) {
                    e.consume();
                }

            }
        });

        campoNombre.setBounds(140, 20, 150, 20);
        //quitar el borde
        campoNombre.setBorder(null);

        telefono = new JLabel("Teléfono");
        telefono.setBounds(60, 60, 80, 20);

        campoTelefono = new JTextField();

        campoTelefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int key = e.getKeyChar();
                // Donde empiezan y terminan los números en ASCII
                boolean numeros = key >= 48 && key <= 57;
                // Si se introduce algo que no sea número no se introduce
                if (!numeros) {
                    e.consume();
                }
                // No se pueden introducir más de 9 números en el campo
                if(campoTelefono.getText().length()>=9) {
                    e.consume();
                }
            }
        });

        campoTelefono.setBounds(140, 60, 150, 20);
        //quitar el borde
        campoTelefono.setBorder(null);

        okBtn = new JButton("Ok");
        okBtn.setBounds(180, 105, 100, 30);
        okBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ponemos el cursor de la mano al pasar por el boton
        okBtn.addActionListener(controlador);

        cancelarBtn = new JButton("Cancelar");
        cancelarBtn.setBounds(270, 105, 100, 30);
        cancelarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ponemos el cursor de la mano al pasar por el boton
        cancelarBtn.addActionListener(controlador);

        getContentPane().add(nombre);
        getContentPane().add(campoNombre);
        getContentPane().add(telefono);
        getContentPane().add(campoTelefono);
        getContentPane().add(okBtn);
        getContentPane().add(cancelarBtn);
    }

    public void establecerListeners(Controlador controlador) {
        okBtn.addActionListener(controlador);
    }
}
