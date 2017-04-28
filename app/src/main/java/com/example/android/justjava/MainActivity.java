package com.example.android.justjava;

//import android.icu.text.NumberFormat;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    int basePrice = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_cb);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);

        EditText customerNameEditText = (EditText) findViewById(R.id.customer_name);
        String customerName = customerNameEditText.getText().toString();

        createOrderSummary(customerName, hasWhippedCream, hasChocolate, price);
//        displayMessage(createOrderSummary(customerName, hasWhippedCream, hasChocolate, price));
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {

        if (quantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }

        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void decrement(View view) {

        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }

        quantity--;
        displayQuantity(quantity);
    }

    /**
     * Create a summary of an order
     * @param price
     * @param hasWhippedCream
     * @param hasChocolate
     * @return priceMessage
     */
    private void createOrderSummary(String addName, boolean addWhippedCream, boolean addChocolate, int price) {
        String priceMessage = "Name: " + addName + "\n";
        priceMessage = priceMessage + "Add Whipped Cream? " + addWhippedCream + "\n";
        priceMessage = priceMessage + "Add Chocolate? " + addChocolate + "\n";
        priceMessage = priceMessage + "Quantity: " + quantity + "\n";
        priceMessage = priceMessage + "Total: $" + price + "\n";
        priceMessage = priceMessage + "Thank You.";
        String subject = "Just Java Order for: " + addName + "\n";
        composeEmail(subject, priceMessage);
//        return priceMessage;
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

    private int calculatePrice(Boolean hasWhippedCream, Boolean hasChocolate) {

        if (hasWhippedCream) {
            basePrice++;
        }

        if (hasChocolate) {
            basePrice=+2;
        }

        return quantity * basePrice;
    }

    public void composeEmail(String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }


}
