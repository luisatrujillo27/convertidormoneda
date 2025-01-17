import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ConvertidorMonedas {
    public static void main(String[] args) {
        // Crear el marco principal
        JFrame frame = new JFrame("Convertidor de Monedas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1));

        // Etiqueta de bienvenida
        JLabel welcomeLabel = new JLabel("Seleccione una moneda y un monto para convertir", JLabel.CENTER);
        frame.add(welcomeLabel);

        // Entrada del monto
        JPanel inputPanel = new JPanel();
        JLabel amountLabel = new JLabel("Monto:");
        JTextField amountField = new JTextField(10);
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);
        frame.add(inputPanel);

        // Selección de monedas
        JPanel currencyPanel = new JPanel();
        JLabel fromLabel = new JLabel("De:");
        String[] currencies = {"USD", "EUR", "MXN", "JPY", "COP"};
        JComboBox<String> fromCurrency = new JComboBox<>(currencies);
        JLabel toLabel = new JLabel("A:");
        JComboBox<String> toCurrency = new JComboBox<>(currencies);
        currencyPanel.add(fromLabel);
        currencyPanel.add(fromCurrency);
        currencyPanel.add(toLabel);
        currencyPanel.add(toCurrency);
        frame.add(currencyPanel);

        // Botón de conversión
        JButton convertButton = new JButton("Convertir");
        frame.add(convertButton);

        // Resultado de la conversión
        JLabel resultLabel = new JLabel("Resultado: ", JLabel.CENTER);
        frame.add(resultLabel);

        // Map de tasas de cambio (ejemplo ficticio)
        Map<String, Double> exchangeRates = new HashMap<>();
        exchangeRates.put("USD_EUR", 0.85);
        exchangeRates.put("USD_MXN", 20.0);
        exchangeRates.put("USD_JPY", 110.0);
        exchangeRates.put("USD_COP", 4000.0);
        exchangeRates.put("EUR_USD", 1.18);
        exchangeRates.put("EUR_MXN", 23.5);
        exchangeRates.put("EUR_JPY", 130.0);
        exchangeRates.put("EUR_COP", 4700.0);

        // Acción del botón
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    String from = (String) fromCurrency.getSelectedItem();
                    String to = (String) toCurrency.getSelectedItem();

                    if (from.equals(to)) {
                        resultLabel.setText("Resultado: " + amount + " " + to);
                        return;
                    }

                    String key = from + "_" + to;
                    if (exchangeRates.containsKey(key)) {
                        double rate = exchangeRates.get(key);
                        double result = amount * rate;
                        resultLabel.setText(String.format("Resultado: %.2f %s", result, to));
                    } else {
                        resultLabel.setText("Tasa de cambio no disponible.");
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Por favor, ingrese un monto válido.");
                }
            }
        });

        // Mostrar la ventana
        frame.setVisible(true);
    }
}
