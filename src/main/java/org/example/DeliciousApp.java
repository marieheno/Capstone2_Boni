package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class DeliciousApp extends JFrame {

    private RoundedButton startButton;
    private boolean orderStarted = false;

    // ================= COLORS =================

    private final Color BACKGROUND = new Color(247, 242, 236);
    private final Color BROWN = new Color(95, 45, 18);
    private final Color ORANGE = new Color(215, 110, 35);
    private final Color CARD = Color.WHITE;

    // ================= CARD LAYOUT =================

    private CardLayout cardLayout;
    private JPanel mainPanel;

    // ================= ORDER =================

    private JTextArea receiptArea;

    private ArrayList<String> orderItems = new ArrayList<>();

    private double total = 0;

    private JLabel totalLabel;

    public DeliciousApp() {

        setTitle("DELI-CIOUS");

        setSize(430, 850);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        cardLayout = new CardLayout();

        mainPanel = new JPanel(cardLayout);

        // Screens
        mainPanel.add(createHomeScreen(), "HOME");
        mainPanel.add(createOrderScreen(), "ORDER");
        mainPanel.add(createSandwichScreen(), "SANDWICH");
        mainPanel.add(createDrinkScreen(), "DRINK");
        mainPanel.add(createChipsScreen(), "CHIPS");
        mainPanel.add(createCheckoutScreen(), "CHECKOUT");

        add(mainPanel);

        setVisible(true);
    }

    // =========================================================
    // HOME SCREEN
    // =========================================================

    private JScrollPane createHomeScreen() {

        JPanel panel = new JPanel();

        panel.setBackground(BACKGROUND);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setBorder(new EmptyBorder(20,20,20,20));

        // ================= TITLE =================

        JLabel title = new JLabel("DELI-CIOUS");

        title.setFont(new Font("Arial", Font.BOLD, 38));

        title.setForeground(BROWN);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);

        panel.add(Box.createRigidArea(new Dimension(0,20)));

        // ================= TEXT =================

        JLabel text1 = new JLabel("FRESH. FAST.");

        text1.setFont(new Font("Arial", Font.BOLD, 30));

        text1.setForeground(BROWN);

        text1.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(text1);

        JLabel text2 = new JLabel("DELICIOUS.");

        text2.setFont(new Font("Arial", Font.BOLD, 34));

        text2.setForeground(ORANGE);

        text2.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(text2);

        panel.add(Box.createRigidArea(new Dimension(0,10)));

        JLabel desc = new JLabel("Build your perfect sandwich your way!");

        desc.setFont(new Font("Arial", Font.PLAIN, 18));

        desc.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(desc);

        panel.add(Box.createRigidArea(new Dimension(0,20)));

        // ================= IMAGE =================

        ImageIcon icon = new ImageIcon(
                Objects.requireNonNull(
                        Main.class.getResource("/images/sandwich.png")));

        Image img = icon.getImage();

        Image scaled = img.getScaledInstance(
                340,
                240,
                Image.SCALE_SMOOTH);

        JLabel image = new JLabel(new ImageIcon(scaled));

        image.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(image);

        panel.add(Box.createRigidArea(new Dimension(0,25)));

        // ================= POPULAR =================

        JLabel popular = new JLabel("POPULAR SANDWICHES");

        popular.setFont(new Font("Arial", Font.BOLD, 24));

        popular.setForeground(BROWN);

        popular.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(popular);

        panel.add(Box.createRigidArea(new Dimension(0,20)));

        // ================= CARDS =================

        JPanel cardsPanel = new JPanel();

        cardsPanel.setBackground(BACKGROUND);

        cardsPanel.setLayout(new GridLayout(1,2,15,15));

        cardsPanel.add(createSignatureCard(
                "BLT",
                "$8.99",
                "/images/BLT.jpg"));

        cardsPanel.add(createSignatureCard(
                "Philly Cheese Steak",
                "$9.49",
                "/images/philly.png"));

        panel.add(cardsPanel);

        panel.add(Box.createRigidArea(new Dimension(0,30)));

        // ================= START BUTTON =================

         startButton =
                new RoundedButton("START NEW ORDER");

        startButton.setMaximumSize(new Dimension(350,55));

        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton.addActionListener(e ->
                cardLayout.show(mainPanel,"ORDER"));

        panel.add(startButton);

        panel.add(Box.createRigidArea(new Dimension(0,15)));

        // ================= EXIT BUTTON =================

        RoundedButton exitButton =
                new RoundedButton("EXIT");

        exitButton.setMaximumSize(new Dimension(350,55));

        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        exitButton.addActionListener(e -> System.exit(0));

        panel.add(exitButton);

        JScrollPane scrollPane = new JScrollPane(panel);

        scrollPane.setBorder(null);

        return scrollPane;
    }

    // =========================================================
    // ORDER SCREEN
    // =========================================================

    private JPanel createOrderScreen() {

        JPanel panel = createBasePanel();

        JLabel title =
                createTitle("YOUR ORDER");

        panel.add(title);

        panel.add(Box.createRigidArea(
                new Dimension(0,25)));

        // ================= ORDER GRID =================

        JPanel orderGrid =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                20,
                                20));

        orderGrid.setOpaque(false);

// =========================================
// BUILD SANDWICH
// =========================================

        orderGrid.add(createOrderCard(
                "🥪",
                "Build Sandwich",
                "Customize your sandwich",
                e -> cardLayout.show(
                        mainPanel,
                        "SANDWICH")));

// =========================================
// DRINKS
// =========================================

        orderGrid.add(createOrderCard(
                "🥤",
                "Drinks",
                "Add refreshing drinks",
                e -> cardLayout.show(
                        mainPanel,
                        "DRINK")));

// =========================================
// CHIPS
// =========================================

        orderGrid.add(createOrderCard(
                "🍟",
                "Chips",
                "Choose your favorite chips",
                e -> cardLayout.show(
                        mainPanel,
                        "CHIPS")));

// =========================================
// CHECKOUT
// =========================================

        orderGrid.add(createOrderCard(
                "🧾",
                "Checkout",
                "Review your order",
                e -> {

                    updateReceipt();

                    cardLayout.show(
                            mainPanel,
                            "CHECKOUT");
                }));

        panel.add(orderGrid);

        panel.add(Box.createRigidArea(
                new Dimension(0,20)));

        // =========================================
// BUTTON ROW
// =========================================

        JPanel buttonRow =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                10,
                                10));

        buttonRow.setOpaque(false);
        buttonRow.setMaximumSize(
                new Dimension(390,45));

// =========================================
// BACK BUTTON
// =========================================

        RoundedButton backButton =
                new RoundedButton("← BACK");

        backButton.addActionListener(e ->
                cardLayout.show(mainPanel,
                        "HOME"));

// =========================================
// CANCEL BUTTON
// =========================================

        RoundedButton cancelButton =
                new RoundedButton("CANCEL ORDER");

        cancelButton.addActionListener(e -> {

            orderItems.clear();

            total = 0;

            totalLabel.setText(
                    "TOTAL: $0.00");

            orderStarted = false;

            startButton.setText(
                    "START NEW ORDER");

            JOptionPane.showMessageDialog(
                    this,
                    "Order Cancelled");

            cardLayout.show(mainPanel,
                    "HOME");
        });

// =========================================
// ADD BUTTONS
// =========================================

        buttonRow.add(backButton);

        buttonRow.add(cancelButton);

        panel.add(buttonRow);

        // ================= TOTAL =================

        totalLabel =
                new JLabel("TOTAL: $0.00");

        totalLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        28));

        totalLabel.setForeground(BROWN);

        totalLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        panel.add(totalLabel);

        return panel;
    }

    // =========================================================
    // SANDWICH SCREEN
    // =========================================================
    private JScrollPane createSandwichScreen() {

        JPanel panel = createBasePanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS));

        // =========================================
        // TITLE
        // =========================================

        JLabel title =
                createTitle("BUILD SANDWICH");

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        panel.setAlignmentX(
                Component.CENTER_ALIGNMENT);
        JPanel titlePanel =
                new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.add(title);

        panel.add(titlePanel);

        panel.add(Box.createRigidArea(
                new Dimension(0,20)));

        // =========================================
        // IMAGE
        // =========================================

        ImageIcon icon = new ImageIcon(
                Objects.requireNonNull(
                        getClass().getResource(
                                "/images/sandwich.png")));

        Image img = icon.getImage();

        Image scaled = img.getScaledInstance(
                320,
                190,
                Image.SCALE_SMOOTH);

        JLabel image =
                new JLabel(new ImageIcon(scaled));

        image.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        JPanel imagePanel =
                new JPanel();

        imagePanel.setOpaque(false);

        imagePanel.add(image);

        panel.add(imagePanel);

        panel.add(Box.createRigidArea(
                new Dimension(0,25)));

        // =========================================
// BREAD
// =========================================

        JPanel breadPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                10));

        breadPanel.setOpaque(false);

        breadPanel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createTitledBorder(
                                BorderFactory.createLineBorder(
                                        new Color(220,200,180),
                                        2,
                                        true),
                                "BREAD"),

                        new EmptyBorder(
                                10,
                                10,
                                10,
                                10)));

        JToggleButton white =
                check("White", "+0.00");

        JToggleButton wheat =
                check("Wheat", "+0.00");

        JToggleButton rye =
                check("Rye", "+0.00");

        JToggleButton wrap =
                check("Wrap", "+0.00");

        ButtonGroup breadGroup =
                new ButtonGroup();

        breadGroup.add(white);
        breadGroup.add(wheat);
        breadGroup.add(rye);
        breadGroup.add(wrap);

        white.setSelected(true);

        breadPanel.add(white);
        breadPanel.add(wheat);
        breadPanel.add(rye);
        breadPanel.add(wrap);

        panel.add(breadPanel);

        panel.add(Box.createRigidArea(
                new Dimension(0,20)));

        // =========================================
// SIZE
// =========================================

        JPanel sizePanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                10));

        sizePanel.setOpaque(false);

        sizePanel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createTitledBorder(
                                BorderFactory.createLineBorder(
                                        new Color(220,200,180),
                                        2,
                                        true),
                                "SIZE"),

                        new EmptyBorder(
                                10,
                                10,
                                10,
                                10)));

        JToggleButton four =
                check("4 Inch", "$5.50");

        JToggleButton eight =
                check("8 Inch", "$7.00");

        JToggleButton twelve =
                check("12 Inch", "$8.50");

        ButtonGroup sizeGroup =
                new ButtonGroup();

        sizeGroup.add(four);
        sizeGroup.add(eight);
        sizeGroup.add(twelve);

        eight.setSelected(true);

        sizePanel.add(four);
        sizePanel.add(eight);
        sizePanel.add(twelve);

        panel.add(sizePanel);

        panel.add(Box.createRigidArea(
                new Dimension(0,20)));

        // =========================================
        // MEATS
        // =========================================

        JPanel meatPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                10));

        meatPanel.setOpaque(false);

        meatPanel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createTitledBorder(
                                BorderFactory.createLineBorder(
                                        new Color(220,200,180),
                                        2,
                                        true),
                                "MEATS"),

                        new EmptyBorder(
                                10,
                                10,
                                10,
                                10)));
        meatPanel.add(check("Steak", "+2.00"));
        meatPanel.add(check("Extra Steak", "+1.00"));

        meatPanel.add(check("Ham", "+2.00"));
        meatPanel.add(check("Extra Ham", "+1.00"));

        meatPanel.add(check("Salami", "+2.00"));
        meatPanel.add(check("Extra Salami", "+1.00"));

        meatPanel.add(check("Roast Beef", "+2.00"));
        meatPanel.add(check("Extra Roast Beef", "+1.00"));

        meatPanel.add(check("Chicken", "+2.00"));
        meatPanel.add(check("Extra Chicken", "+1.00"));

        meatPanel.add(check("Bacon", "+2.00"));
        meatPanel.add(check("Extra Bacon", "+1.00"));

        panel.add(meatPanel);

        panel.add(Box.createRigidArea(
                new Dimension(0,20)));

        // =========================================
        // CHEESE
        // =========================================

        JPanel cheesePanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                10));

        cheesePanel.setOpaque(false);

        cheesePanel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createTitledBorder(
                                BorderFactory.createLineBorder(
                                        new Color(220,200,180),
                                        2,
                                        true),
                                "CHEESE"),

                        new EmptyBorder(
                                10,
                                10,
                                10,
                                10)));

        cheesePanel.add(check("American", "+1.50"));
        cheesePanel.add(check("Extra American", "+0.60"));

        cheesePanel.add(check("Provolone", "+1.50"));
        cheesePanel.add(check("Extra Provolone", "+0.60"));

        cheesePanel.add(check("Cheddar", "+1.50"));
        cheesePanel.add(check("Extra Cheddar", "+0.60"));

        cheesePanel.add(check("Swiss", "+1.50"));
        cheesePanel.add(check("Extra Swiss", "+0.60"));

        panel.add(cheesePanel);

        panel.add(Box.createRigidArea(
                new Dimension(0,20)));

        // =========================================
        // TOPPINGS
        // =========================================

        JPanel toppingsPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                10));

        toppingsPanel.setOpaque(false);

        toppingsPanel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createTitledBorder(
                                BorderFactory.createLineBorder(
                                        new Color(220,200,180),
                                        2,
                                        true),
                                "TOPPINGS"),

                        new EmptyBorder(
                                10,
                                10,
                                10,
                                10)));

        toppingsPanel.add(check("Lettuce", "+0.00"));
        toppingsPanel.add(check("Peppers", "+0.00"));

        toppingsPanel.add(check("Onions", "+0.00"));
        toppingsPanel.add(check("Tomatoes", "+0.00"));

        toppingsPanel.add(check("Jalapeños", "+0.00"));
        toppingsPanel.add(check("Cucumbers", "+0.00"));

        toppingsPanel.add(check("Pickles", "+0.00"));
        toppingsPanel.add(check("Guacamole", "+0.00"));

        toppingsPanel.add(check("Mushrooms", "+0.00"));

        panel.add(toppingsPanel);

        panel.add(Box.createRigidArea(
                new Dimension(0,20)));

        // =========================================
        // SAUCES
        // =========================================

        JPanel saucePanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                10));

        saucePanel.setOpaque(false);

        saucePanel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createTitledBorder(
                                BorderFactory.createLineBorder(
                                        new Color(220,200,180),
                                        2,
                                        true),
                                "SAUCES"),

                        new EmptyBorder(
                                10,
                                10,
                                10,
                                10)));

        saucePanel.add(check("Mayo", "+0.00"));
        saucePanel.add(check("Mustard", "+0.00"));

        saucePanel.add(check("Ketchup", "+0.00"));
        saucePanel.add(check("Ranch", "+0.00"));

        saucePanel.add(check("Thousand Islands", "+0.00"));
        saucePanel.add(check("Vinaigrette", "+0.00"));

        panel.add(saucePanel);

        panel.add(Box.createRigidArea(
                new Dimension(0,20)));

        // =========================================
        // SIDES
        // =========================================

        JPanel sidePanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                10));

        sidePanel.setOpaque(false);

        sidePanel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createTitledBorder(
                                BorderFactory.createLineBorder(
                                        new Color(220,200,180),
                                        2,
                                        true),
                                "SIDES"),

                        new EmptyBorder(
                                10,
                                10,
                                10,
                                10)));

        sidePanel.add(check("Au Jus", "+0.00"));

        sidePanel.add(check("Sauce", "+0.00"));

        sidePanel.add(check("Toasted", "+0.00"));

        panel.add(sidePanel);

        panel.add(Box.createRigidArea(
                new Dimension(0,30)));

        // =========================================
        // BUTTONS
        // =========================================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                20,
                                0));

        buttonPanel.setOpaque(false);

        RoundedButton backButton =
                new RoundedButton("BACK");

        RoundedButton addButton =
                new RoundedButton("ADD TO ORDER");

        backButton.addActionListener(e ->
                cardLayout.show(
                        mainPanel,
                        "ORDER"));

        addButton.addActionListener(e -> {

            total += 7.00;

            totalLabel.setText(
                    "TOTAL: $" +
                            String.format(
                                    "%.2f",
                                    total));

            JOptionPane.showMessageDialog(
                    this,
                    "Sandwich Added!");

            cardLayout.show(
                    mainPanel,
                    "ORDER");
        });

        buttonPanel.add(backButton);

        buttonPanel.add(addButton);

        panel.add(buttonPanel);

        panel.add(Box.createRigidArea(
                new Dimension(0,20)));

        JScrollPane scrollPane =
                new JScrollPane(
                        panel,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        return scrollPane;
    }

// =========================================
// CHECKBOX STYLE
// =========================================

    private JToggleButton check(
            String text,
            String price) {

        JToggleButton button =
                new JToggleButton(
                        "<html><center>"
                                + text
                                + "<br>"
                                + "<span style='font-size:13px;'>"
                                + price
                                + "</span>"
                                + "</center></html>");

        button.setPreferredSize(
                new Dimension(170,75));

        button.setFont(
                new Font("Arial",
                        Font.BOLD,
                        15));

        button.setHorizontalAlignment(
                SwingConstants.CENTER);

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR));

        button.setBackground(
                Color.WHITE);

        button.setForeground(
                new Color(70,40,20));

        button.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(220,200,180),
                                2,
                                true),

                        new EmptyBorder(
                                10,
                                10,
                                10,
                                10)));

        // =========================================
        // HOVER EFFECT
        // =========================================

        button.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    public void mouseEntered(
                            java.awt.event.MouseEvent e) {

                        if(!button.isSelected()) {

                            button.setBackground(
                                    new Color(
                                            255,
                                            245,
                                            235));
                        }
                    }

                    public void mouseExited(
                            java.awt.event.MouseEvent e) {

                        if(!button.isSelected()) {

                            button.setBackground(
                                    Color.WHITE);
                        }
                    }
                });

        // =========================================
        // SELECTED EFFECT
        // =========================================

        button.addItemListener(e -> {

            if(button.isSelected()) {

                button.setBackground(
                        new Color(95,45,18));

                button.setForeground(
                        Color.WHITE);
            }

            else {

                button.setBackground(
                        Color.WHITE);

                button.setForeground(
                        new Color(70,40,20));
            }
        });

        return button;
    }

// =========================================
// RADIO STYLE
// =========================================

    private JRadioButton radio(String text) {

        JRadioButton button =
                new JRadioButton(text);

        button.setOpaque(false);

        button.setFont(
                new Font("Arial",
                        Font.BOLD,
                        15));

        button.setForeground(
                new Color(70,40,20));

        return button;
    }

    // =========================================================
    // DRINK SCREEN
    // =========================================================

    private JScrollPane createDrinkScreen() {

        JPanel panel = createBasePanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS));

        // =========================================
        // TITLE
        // =========================================

        JLabel title =
                createTitle("CHOOSE YOUR DRINK");

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        panel.add(title);

        panel.add(Box.createRigidArea(
                new Dimension(0,25)));

        // =========================================
        // DRINK GRID
        // =========================================

        JPanel grid = new JPanel(
                new GridLayout(
                        2,
                        2,
                        20,
                        20));

        grid.setOpaque(false);

        grid.add(createDrinkCard(
                "Coke",
                "$2.50",
                "/images/coke.png"));

        grid.add(createDrinkCard(
                "Sprite",
                "$2.50",
                "/images/sprite.png"));

        grid.add(createDrinkCard(
                "Fanta",
                "$2.50",
                "/images/fanta.png"));

        grid.add(createDrinkCard(
                "Lemonade",
                "$2.00",
                "/images/lemonade.png"));

        panel.add(grid);

        panel.add(Box.createRigidArea(
                new Dimension(0,30)));

        // =========================================
        // BACK BUTTON
        // =========================================

        panel.add(Box.createRigidArea(
                new Dimension(0,20)));

        RoundedButton backButton =
                new RoundedButton("← BACK");

        backButton.setMaximumSize(
                new Dimension(220,50));

        backButton.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        backButton.addActionListener(e ->
                cardLayout.show(mainPanel,
                        "ORDER"));

        panel.add(backButton);

        // =========================================
        // SCROLL
        // =========================================

        JScrollPane scrollPane =
                new JScrollPane(
                        panel,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        return scrollPane;
    }

    private JPanel createDrinkCard(
            String name,
            String price,
            String imagePath) {

        JPanel card = new JPanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS));

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(230,230,230)),

                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20)));

        // =========================================
        // IMAGE
        // =========================================

        JLabel image =
                new JLabel(
                        loadIcon(
                                imagePath,
                                120,
                                120));

        image.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        // =========================================
        // NAME
        // =========================================

        JLabel title =
                new JLabel(name);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22));

        title.setForeground(BROWN);

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        // =========================================
        // PRICE
        // =========================================

        JLabel priceLabel =
                new JLabel(price);

        priceLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18));

        priceLabel.setForeground(ORANGE);

        priceLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        // =========================================
        // BUTTON
        // =========================================

        RoundedButton add =
                new RoundedButton(
                        "ADD DRINK");

        add.setMaximumSize(
                new Dimension(180,45));

        add.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        add.addActionListener(e -> {

            total += 2.50;

            totalLabel.setText(
                    "TOTAL: $" +
                            String.format(
                                    "%.2f",
                                    total));

            JOptionPane.showMessageDialog(
                    this,
                    name + " Added!");

            cardLayout.show(
                    mainPanel,
                    "ORDER");
        });

        // =========================================
        // ADD
        // =========================================

        card.add(image);

        card.add(Box.createRigidArea(
                new Dimension(0,10)));

        card.add(title);

        card.add(Box.createRigidArea(
                new Dimension(0,5)));

        card.add(priceLabel);

        card.add(Box.createRigidArea(
                new Dimension(0,15)));

        card.add(add);

        return card;
    }

    private ImageIcon loadIcon(
            String path,
            int width,
            int height) {

        ImageIcon icon =
                new ImageIcon(
                        getClass().getResource(path));

        Image image =
                icon.getImage().getScaledInstance(
                        width,
                        height,
                        Image.SCALE_SMOOTH);

        return new ImageIcon(image);
    }

    // =========================================================
    // CHIPS SCREEN
    // =========================================================

    private JPanel createChipsScreen() {

        JPanel panel = createBasePanel();

        JLabel title =
                createTitle("CHIPS");

        panel.add(title);

        panel.add(Box.createRigidArea(
                new Dimension(0,25)));

        // ================= CHIPS PANEL =================

        JPanel chipsPanel = new JPanel();

        chipsPanel.setOpaque(false);

        chipsPanel.setLayout(
                new GridLayout(1,3,15,15));

        chipsPanel.add(createChipCard(
                "BBQ",
                "$1.50",
                "/images/bbqchips.png"));

        chipsPanel.add(createChipCard(
                "Classic",
                "$1.50",
                "/images/classicchips.png"));

        chipsPanel.add(createChipCard(
                "Cheddar",
                "$1.50",
                "/images/cheddarchips.png"));

        panel.add(Box.createRigidArea(
                new Dimension(0,210)));

        panel.add(chipsPanel);

        // =========================================
        // BACK BUTTON
       // =========================================

        panel.add(Box.createRigidArea(
                new Dimension(0,20)));

        RoundedButton backButton =
                new RoundedButton("← BACK");

        backButton.setMaximumSize(
                new Dimension(220,50));

        backButton.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        backButton.addActionListener(e ->
                cardLayout.show(mainPanel,
                        "HOME"));

        panel.add(backButton);

        return panel;
    }

    private JPanel createChipCard(
            String name,
            String price,
            String imagePath) {

        JPanel card = new JPanel();

        card.setOpaque(false);

        card.setLayout(
                new BoxLayout(card,
                        BoxLayout.Y_AXIS));

        // ================= IMAGE =================

        ImageIcon icon = new ImageIcon(
                Objects.requireNonNull(
                        getClass().getResource(imagePath)));

        Image img = icon.getImage();

        Image scaled = img.getScaledInstance(
                160,
                150,
                Image.SCALE_SMOOTH);

        JLabel image =
                new JLabel(new ImageIcon(scaled));

        image.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        // ================= NAME =================

        JLabel chipName =
                new JLabel(name);

        chipName.setFont(
                new Font("Arial",
                        Font.BOLD,
                        20));

        chipName.setForeground(BROWN);

        chipName.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        // ================= PRICE =================

        JLabel chipPrice =
                new JLabel(price);

        chipPrice.setForeground(ORANGE);

        chipPrice.setFont(
                new Font("Arial",
                        Font.BOLD,
                        18));

        chipPrice.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        // ================= BUTTON =================

        RoundedButton add =
                new RoundedButton("ADD CHIPS");

        add.setMaximumSize(
                new Dimension(170,45));

        add.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        add.addActionListener(e -> {

            orderItems.add(
                    name +
                            " Chips\nPrice: " +
                            price);

            total += 1.50;

            totalLabel.setText(
                    "TOTAL: $" +
                            String.format("%.2f",
                                    total));

            orderStarted = true;

            startButton.setText(
                    "➡️ CONTINUE ORDER");

            JOptionPane.showMessageDialog(
                    this,
                    name + " Chips Added!");
        });

        // ================= ADD =================

        card.add(image);

        card.add(Box.createRigidArea(
                new Dimension(0,15)));

        card.add(chipName);

        card.add(Box.createRigidArea(
                new Dimension(0,5)));

        card.add(chipPrice);

        card.add(Box.createRigidArea(
                new Dimension(0,12)));

        card.add(add);

        return card;
    }

    // =========================================================
    // CHECKOUT SCREEN
    // =========================================================

    private JPanel createCheckoutScreen() {

        JPanel panel = createBasePanel();

        JLabel title =
                createTitle("🧾 CHECKOUT");

        panel.add(title);

        panel.add(Box.createRigidArea(new Dimension(0,20)));

        receiptArea = new JTextArea();

        receiptArea.setFont(
                new Font("Monospaced",
                        Font.PLAIN,
                        15));

        receiptArea.setEditable(false);

        JScrollPane scrollPane =
                new JScrollPane(receiptArea);

        scrollPane.setPreferredSize(
                new Dimension(350,400));

        panel.add(scrollPane);

        panel.add(Box.createRigidArea(new Dimension(0,20)));

        RoundedButton confirmButton =
                new RoundedButton("CONFIRM ORDER");

        confirmButton.setMaximumSize(
                new Dimension(350,55));

        confirmButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Receipt Saved!\nThank you!");

            orderItems.clear();

            total = 0;

            totalLabel.setText("TOTAL: $0.00");

            cardLayout.show(mainPanel,"HOME");
        });

        panel.add(confirmButton);

        return panel;
    }

    // =========================================================
    // UPDATE RECEIPT
    // =========================================================

    private void updateReceipt() {

        StringBuilder receipt =
                new StringBuilder();

        receipt.append("====== DELI-CIOUS ======\n\n");

        for(String item : orderItems) {

            receipt.append(item)
                    .append("\n\n");
        }

        receipt.append("----------------------\n");

        receipt.append("TOTAL: $")
                .append(String.format("%.2f", total));

        receiptArea.setText(receipt.toString());
    }

    // =========================================================
    // BASE PANEL
    // =========================================================

    private JPanel createBasePanel() {

        JPanel panel = new JPanel();

        panel.setBackground(BACKGROUND);

        panel.setLayout(
                new BoxLayout(panel,
                        BoxLayout.Y_AXIS));

        panel.setBorder(
                new EmptyBorder(20,20,20,20));

        return panel;
    }

    // =========================================================
    // TITLE
    // =========================================================

    private JLabel createTitle(String text) {

        JLabel label = new JLabel(text);

        label.setFont(
                new Font("Arial",
                        Font.BOLD,
                        28));

        label.setForeground(BROWN);

        label.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        return label;
    }

    // =========================================================
    // SIGNATURE CARD
    // =========================================================

    private JPanel createSignatureCard(
            String name,
            String price,
            String imagePath) {

        JPanel card = new JPanel();

        card.setBackground(CARD);

        card.setLayout(
                new BoxLayout(card,
                        BoxLayout.Y_AXIS));

        card.setBorder(
                new EmptyBorder(15,15,15,15));

        ImageIcon icon = new ImageIcon(
                Objects.requireNonNull(
                        getClass().getResource(imagePath)));

        Image img = icon.getImage();

        Image scaled = img.getScaledInstance(
                120,
                90,
                Image.SCALE_SMOOTH);

        JLabel imageLabel =
                new JLabel(new ImageIcon(scaled));

        imageLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        // ================= NAME =================

        JLabel nameLabel =
                new JLabel(name);

        nameLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        18));

        nameLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        // ================= PRICE =================

        JLabel priceLabel =
                new JLabel(price);

        priceLabel.setFont(
                new Font("Arial",
                        Font.PLAIN,
                        16));

        priceLabel.setForeground(ORANGE);

        priceLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        // ================= BUTTON =================

        RoundedButton addButton =
                new RoundedButton("ADD");

        addButton.setMaximumSize(
                new Dimension(120,40));

        addButton.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        addButton.addActionListener(e -> {

            orderItems.add(
                    name +
                            "\nPrice: " +
                            price);

            total += Double.parseDouble(
                    price.replace("$",""));

            totalLabel.setText(
                    "TOTAL: $" +
                            String.format("%.2f", total));

            orderStarted = true;

            startButton.setText(
                    "➡️ CONTINUE ORDER");

            JOptionPane.showMessageDialog(
                    this,
                    name + " Added!");
        });

        // ================= HOVER =================

        card.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(
                    java.awt.event.MouseEvent evt) {

                card.setBackground(
                        new Color(255,245,235));
            }

            public void mouseExited(
                    java.awt.event.MouseEvent evt) {

                card.setBackground(CARD);
            }
        });

        // ================= ADD =================

        card.add(imageLabel);

        card.add(Box.createRigidArea(
                new Dimension(0,10)));

        card.add(nameLabel);

        card.add(Box.createRigidArea(
                new Dimension(0,5)));

        card.add(priceLabel);

        card.add(Box.createRigidArea(
                new Dimension(0,10)));

        card.add(addButton);

        return card;
    }

    private JPanel createOrderCard(
            String emoji,
            String title,
            String desc,
            java.awt.event.ActionListener action) {

        JPanel card = new JPanel();

        card.setLayout(
                new BoxLayout(card,
                        BoxLayout.Y_AXIS));

        card.setBackground(
                new Color(255,255,255,230));

        card.setBorder(BorderFactory
                .createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(220,220,220)),

                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20)));

        // ================= EMOJI =================

        JLabel emojiLabel =
                new JLabel(emoji);

        emojiLabel.setFont(
                new Font("Segoe UI Emoji",
                        Font.PLAIN,
                        40));

        emojiLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        // ================= TITLE =================

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        22));

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        // ================= DESC =================

        JLabel descLabel =
                new JLabel(desc);

        descLabel.setFont(
                new Font("Arial",
                        Font.PLAIN,
                        15));

        descLabel.setForeground(Color.GRAY);

        descLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        // ================= BUTTON =================

        RoundedButton start =
                new RoundedButton("START");

        start.setMaximumSize(
                new Dimension(180,45));

        start.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        start.addActionListener(action);

        // ================= HOVER =================

        card.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(
                    java.awt.event.MouseEvent evt) {

                card.setBackground(
                        new Color(255,245,235));
            }

            public void mouseExited(
                    java.awt.event.MouseEvent evt) {

                card.setBackground(
                        new Color(255,255,255,230));
            }
        });

        // ================= ADD =================

        card.add(emojiLabel);

        card.add(Box.createRigidArea(
                new Dimension(0,10)));

        card.add(titleLabel);

        card.add(Box.createRigidArea(
                new Dimension(0,5)));

        card.add(descLabel);

        card.add(Box.createRigidArea(
                new Dimension(0,15)));

        card.add(start);

        return card;
    }
}