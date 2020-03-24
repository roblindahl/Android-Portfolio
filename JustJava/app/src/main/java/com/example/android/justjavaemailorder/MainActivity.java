package com.example.android.justjavaemailorder;

/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox whippedBox = findViewById(R.id.whipped_cream);
        boolean whippedState = whippedBox.isChecked();

        CheckBox chocoBox = findViewById(R.id.choco_plox);
        boolean chocoState = chocoBox.isChecked();

        EditText editBox = findViewById(R.id.name_view);
        String nameForOrder = editBox.getText().toString();

        int totalPrice = calculatePrice(whippedState, chocoState);

        createOrderSummary(totalPrice, whippedState, chocoState, nameForOrder);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     * private void displayPrice(int number) {
     *         TextView priceTextView = findViewById(R.id.price_text_view);
     *         priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
     *     }
     */

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    public void increment(View view) {
        if (quantity == 100) {
            Context context = getApplicationContext();
            CharSequence text = "We cannot service more than 100 coffees in one order.\n Please call us at: (123)234-4578 for custom ordering.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        } else {
            quantity = quantity + 1;
            displayQuantity(quantity);
        }
    }

    public void decrement(View view) {

        if (quantity == 1) {
            Context context = getApplicationContext();
            CharSequence text = "Cannot Have Zero or Negative Quantity";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        } else {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }

    }

    /**
     * Calculates the price * quantity
     *
     * @return price for the quantity
     */
    private int calculatePrice(boolean whippedState, boolean chocoState) {
        int amtDuePerCup = 0;
        if (whippedState == true) {
            amtDuePerCup = amtDuePerCup + 1;
        }

        if (chocoState == true) {
            amtDuePerCup = amtDuePerCup + 2;
        }
        return quantity * (5 + amtDuePerCup);
    }

    /**
     * puts a message on screen to describe what was ordered
     *
     * @param nameForOrder    Who is ordering?
     * @param hasWhippedCream yes no?
     * @param hasChocolate    yes no?
     * @param totalPrice      total $ due
     * @return String of name, options if any, quantity, total, and thanks!
     */
    private void createOrderSummary(int totalPrice, boolean hasWhippedCream, boolean hasChocolate, String nameForOrder) {
        String body = getString(R.string.name_desc, nameForOrder);
                body += "\n" + getString(R.string.add_whipped, hasWhippedCream);
                body += "\n" + getString(R.string.add_choco, hasChocolate);
                body += "\n" + getString(R.string.Quantity) + quantity;
                body += "\n" + getString(R.string.total) + totalPrice;
                body += "\n" +getString(R.string.thanks);
        String addresses = "order@justjava.com";
        String subject = "Just Java Order for " + nameForOrder;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

}