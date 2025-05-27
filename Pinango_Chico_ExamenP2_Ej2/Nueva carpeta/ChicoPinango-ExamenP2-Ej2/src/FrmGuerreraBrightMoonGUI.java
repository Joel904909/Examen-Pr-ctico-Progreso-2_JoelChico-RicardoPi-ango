import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class FrmGuerreraBrightMoonGUI {
    private JPanel pGeneral;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JComboBox<String> comboBox3;
    private JButton agregarGuerreraButton;
    private JButton buscarGuerreraPorIDButton;
    private JButton filtrarYOrdenarButton;
    private JButton conteoPorUbicaciónButton;
    private JTable table1;
    private JTextArea textArea1;
    private JFrame frame;

    ListaDoble lista = new ListaDoble();

    public FrmGuerreraBrightMoonGUI() {
        configurarTabla();

        agregarGuerreraButton.addActionListener(e -> {
            int ID = Integer.parseInt(textField1.getText());
            String alias = textField2.getText();
            int nivel = Integer.parseInt(comboBox2.getSelectedItem().toString());
            String poder = comboBox1.getSelectedItem().toString();
            String ubicacion = comboBox3.getSelectedItem().toString();

            if (lista.existeID(ID)) {
                JOptionPane.showMessageDialog(null, "El ID ya existe");
                return;
            }

            lista.insertarOrdenado(new GuerreraBrightMoon(ID, alias, nivel, poder, ubicacion));
            actualizarTabla(lista.toArray());
            limpiarCampos();
        });

        buscarGuerreraPorIDButton.addActionListener(e -> {
            int ID = Integer.parseInt(textField1.getText());
            GuerreraBrightMoon g = lista.buscarBinaria(ID);
            if (g == null) {
                JOptionPane.showMessageDialog(null, "Guerrera no encontrada");
            } else {
                textField2.setText(g.alias);
                comboBox1.setSelectedItem(g.poderBatalla);
                comboBox2.setSelectedItem(String.valueOf(g.nivelEstrategia));
                comboBox3.setSelectedItem(g.ubicacion);
            }
        });

        filtrarYOrdenarButton.addActionListener(e -> {
            String poder = comboBox1.getSelectedItem().toString();
            ArrayList<GuerreraBrightMoon> filtradas = lista.filtrarPorPoder(poder);
            lista.ordenarPorNivelEstrategiaDesc(filtradas);
            actualizarTabla(filtradas.toArray(new GuerreraBrightMoon[0]));
        });

        conteoPorUbicaciónButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < comboBox3.getItemCount(); i++) {
                String ubic = comboBox3.getItemAt(i);
                int count = lista.contarPorUbicacion(ubic);
                sb.append(ubic).append(": ").append(count).append("\n");
            }
            textArea1.setText(sb.toString());
        });
    }

    private void configurarTabla() {
        table1.setModel(new DefaultTableModel(new Object[]{"ID", "Alias", "Poder", "Nivel", "Ubicación"}, 0));
    }

    private void actualizarTabla(GuerreraBrightMoon[] array) {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        for (GuerreraBrightMoon g : array) {
            model.addRow(new Object[]{g.ID, g.alias, g.poderBatalla, g.nivelEstrategia, g.ubicacion});
        }
    }

    private void limpiarCampos() {
        textField1.setText("");
        textField2.setText("");
        comboBox1.setSelectedIndex(0);
        comboBox2.setSelectedIndex(0);
        comboBox3.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Guerreras Bright Moon");
        frame.setContentPane(new FrmGuerreraBrightMoonGUI().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
