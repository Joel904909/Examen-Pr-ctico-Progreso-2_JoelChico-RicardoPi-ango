import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmDefensorEternia {
    private JPanel pGeneral;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JButton agregarDefensorButton;
    private JButton buscarDefensorPorIDButton;
    private JButton filtrarYOrdenarButton;
    private JButton conteoPorHabilidadButton;
    private JTable table1;
    private JTextArea textArea1;
    ListaSimple listaSimple = new ListaSimple();

    public FrmDefensorEternia() {
        agregarDefensorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int ID = Integer.parseInt(textField1.getText());
                    String nombre = textField2.getText();
                    String habilidad = comboBox1.getSelectedItem().toString();
                    int poder = Integer.parseInt(comboBox2.getSelectedItem().toString());
                    String region = comboBox3.getSelectedItem().toString();

                    if (listaSimple.existeID(ID)) {
                        JOptionPane.showMessageDialog(null, "Ya existe un Defensor con ese ID.");
                        return;
                    }

                    listaSimple.insertarAlFinal(ID, poder, habilidad, nombre, region);
                    JOptionPane.showMessageDialog(null, "Defensor agregado exitosamente.");
                    mostrarTabla(listaSimple);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Datos inválidos.");
                }
            }
        });
        buscarDefensorPorIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int ID = Integer.parseInt(textField1.getText());
                    DefensorEternia h = listaSimple.buscarPorID(ID);
                    if (h != null) {
                        textField2.setText(h.nombre);
                        comboBox1.setSelectedItem(h.habilidad);
                        comboBox2.setSelectedItem(String.valueOf(h.poder));
                        comboBox3.setSelectedItem(h.region);
                    } else {
                        JOptionPane.showMessageDialog(null, "Defensor no encontrado.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido.");
                }
            }
        });
        filtrarYOrdenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ciudadFiltro = comboBox3.getSelectedItem().toString();
                ListaSimple listaFiltrada = listaSimple.filtrarYOrdenar(ciudadFiltro);
                mostrarTabla(listaFiltrada);
            }
        });
        conteoPorHabilidadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
                listaSimple.contarPorPoderRecursivo(listaSimple.getPrimero(), textArea1);
            }
        });
    }

    private void mostrarTabla(ListaSimple lista) {
        String[] columnas = {"ID", "Nombre", "Habilidad", "Poder", "Region"};
        DefaultTableModel modelo = new DefaultTableModel(null,columnas);

        DefensorEternia actual = lista.getPrimero();
        do  {
            Object[] fila = {actual.ID, actual.nombre, actual.habilidad, actual.poder, actual.region};
            modelo.addRow(fila);
            actual = actual.sig;
        }while(actual != null);

        table1.setModel(modelo);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("FrmDefensorEternia");
        frame.setContentPane(new FrmDefensorEternia().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
