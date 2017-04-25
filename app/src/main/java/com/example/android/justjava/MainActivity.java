package com.example.android.justjava;

//import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_cb);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        displayMessage(createOrderSummary(price, hasWhippedCream, hasChocolate));
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void decrement(View view) {
        if (quantity!=0) {
            quantity--;
            displayQuantity(quantity);
        }
    }

    /**
     * Create a summary of an order
     * @param price
     * @param hasWhippedCream
     * @param hasChocolate
     * @return priceMessage
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: Dante Casanova\n";
        priceMessage = priceMessage + "Add Whipped Cream?" + addWhippedCream + "\n";
        priceMessage = priceMessage + "Add Chocolate?" + addChocolate + "\n";
        priceMessage = priceMessage + "Quantity: " + quantity + "\n";
        priceMessage = priceMessage + "Total: $" + price + "\n";
        priceMessage = priceMessage + "Thank You.";
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method calculates the given price value on the screen.
     *
     * @param quantity of coffees ordered
     */

    private int calculatePrice() {
        int price = quantity * 5;
        return price;
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


}
