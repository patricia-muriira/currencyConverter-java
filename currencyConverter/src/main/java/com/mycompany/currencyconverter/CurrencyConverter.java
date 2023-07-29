/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.currencyconverter;

/**
 *
 * @author MWENDE 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

//the rates are as of July 2023 with the US Dolar as the base rate
public class CurrencyConverter extends JFrame implements ActionListener {
    private static final String[] CURRENCIES = {
         "USD", "KES", "EUR", "GBP", "JPY", "AUD", "CAD", "CHF", "CNY", "INR", "SGD"
    };

    private static final Map<String, Double> EXCHANGE_RATES = new HashMap<>();
    static {
        EXCHANGE_RATES.put("KES", 141.421); // Kenyan Shilling (KES)
        EXCHANGE_RATES.put("USD", 1.0);//United States Dollar (USD)
        EXCHANGE_RATES.put("EUR", 0.90909);//Euro (EUR)
        EXCHANGE_RATES.put("GBP", 0.77931);//British Pound Sterling (GBP)
        EXCHANGE_RATES.put("JPY", 140.018); //Japanese Yen (JPY)
        EXCHANGE_RATES.put("AUD", 1.50113);//Australian Dollar (AUD)
        EXCHANGE_RATES.put("CAD", 1.3233);//Canadian Dollar (CAD)
        EXCHANGE_RATES.put("CHF", 0.8694);//Swiss Franc (CHF)
        EXCHANGE_RATES.put("CNY", 7.15361);//Chinese Yuan Renmibi(CNY)
        EXCHANGE_RATES.put("INR", 82.2562); //Indian Rupee (INR)
        EXCHANGE_RATES.put("SGD", 1.33125);//Singapore Dollar (SGD)
    }    
    
   
    private JComboBox<String> sourceCurrencyBox;
    private JComboBox<String> targetCurrencyBox;
    private JTextField amountField;
    private JLabel resultLabel;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10)); 

        sourceCurrencyBox = new JComboBox<>(CURRENCIES);
        targetCurrencyBox = new JComboBox<>(CURRENCIES);
        amountField = new JTextField(10);
        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        resultLabel = new JLabel("");

        add(new JLabel("Source Currency:"));
        add(sourceCurrencyBox);
        add(new JLabel("Target Currency:"));
        add(targetCurrencyBox);
        add(new JLabel("Amount:"));
        add(amountField);
        add(convertButton);
        add(new JLabel("Converted Amount:"));
        add(resultLabel);

        pack();
        setSize(400, 250);//window size
        setLocationRelativeTo(null);//center the window
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Convert")) {
            String sourceCurrency = (String) sourceCurrencyBox.getSelectedItem();
            String targetCurrency = (String) targetCurrencyBox.getSelectedItem();
            double amount = Double.parseDouble(amountField.getText());

            double sourceRate = EXCHANGE_RATES.get(sourceCurrency);
            double targetRate = EXCHANGE_RATES.get(targetCurrency);
            double convertedAmount = (amount / sourceRate) * targetRate;

            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            resultLabel.setText(decimalFormat.format(convertedAmount) + " " + targetCurrency);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrencyConverter());
    }
}
