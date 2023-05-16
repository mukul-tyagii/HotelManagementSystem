package com.mukul.view;

import javax.swing.*;
import java.awt.*;

public class Header extends JLabel {

    public Header() {
        setBackground(Color.decode("#0D2430"));
        setForeground(Color.WHITE);
        setOpaque(true);
        setPreferredSize(new Dimension(-1, 50));
        setText("Header");
        setFont(new Font("Arial", Font.BOLD, 18));
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
