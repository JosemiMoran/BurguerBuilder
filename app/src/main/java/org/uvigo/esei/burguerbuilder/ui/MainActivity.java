package org.uvigo.esei.burguerbuilder.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.uvigo.esei.burguerbuilder.R;
import org.uvigo.esei.burguerbuilder.core.BurgerBuilderConfigurator;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private BurgerBuilderConfigurator myBurgerBuilderConfigurator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBurgerBuilderConfigurator = new BurgerBuilderConfigurator();

        final Button BT_INGREDIENTS = findViewById(R.id.btIngredients);
        BT_INGREDIENTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIngredientsDialog();
            }
        });
    }

    private void showIngredientsDialog() {
        AlertDialog.Builder DLG = new AlertDialog.Builder(
                this
        );
        DLG.setTitle("Select ingredients");
        DLG.setMultiChoiceItems(
                BurgerBuilderConfigurator.INGREDIENTS,
                myBurgerBuilderConfigurator.getSelectedIngredients(),
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        myBurgerBuilderConfigurator.getSelectedIngredients()[which] = isChecked;
                    }
                }
        );
        DLG.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (myBurgerBuilderConfigurator.calculatePrice()>0)
                    showServiceDialog();
                updateTotalCost();

            }
        });
        DLG.create().show();
    }

    private void showServiceDialog() {
        AlertDialog.Builder DLG_SERVICE_MODE = new AlertDialog.Builder(this);
        DLG_SERVICE_MODE.setTitle("Service mode:");
        DLG_SERVICE_MODE.setSingleChoiceItems(BurgerBuilderConfigurator.SERVICE_MODE,
                myBurgerBuilderConfigurator.getServiceModeIndexSelected(),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myBurgerBuilderConfigurator.setServiceModeIndexSelected(which);
                    }
                }
        );
        DLG_SERVICE_MODE.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showDiscountDialog();
                updateTotalCost();
            }
        });
        DLG_SERVICE_MODE.create().show();
    }

    private void showDiscountDialog() {
        AlertDialog.Builder DLG_DISCOUNT = new AlertDialog.Builder(this);
        DLG_DISCOUNT.setTitle("Discount");
        DLG_DISCOUNT.setMessage("Apply discount:");
        DLG_DISCOUNT.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myBurgerBuilderConfigurator.setApplyDiscount(true);
                updateTotalCost();
            }
        });
        DLG_DISCOUNT.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myBurgerBuilderConfigurator.setApplyDiscount(false);
                updateTotalCost();
            }
        });
        DLG_DISCOUNT.create().show();
    }


    private void updateTotalCost() {
        TextView totalTextView = findViewById(R.id.tvTotal);
        double total = myBurgerBuilderConfigurator.calculatePrice();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        totalTextView.setText(decimalFormat.format(total) + "€");
    }
}