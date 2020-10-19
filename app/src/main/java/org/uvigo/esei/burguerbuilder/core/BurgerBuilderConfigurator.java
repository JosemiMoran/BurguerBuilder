package org.uvigo.esei.burguerbuilder.core;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.BitSet;

public class BurgerBuilderConfigurator {
    private static final double DISCOUNT = .05;
    public static String[] INGREDIENTS = new String[]{"onion", "salad" , "tomato", "cheese"};
    public static double[] INGREDIENTS_COSTS = new double[]{.25,.5,.75,1};


    public static String[] SERVICE_MODE = new String[]{"Table","Bar","Terrace"};
    public static double[] SERVICE_COSTS= new double[]{1,.25 ,2.5};
    private boolean[] selectedIngredients;
    public int serviceModeIndexSelected = 0;
    public boolean applyDiscount = false;
    public BurgerBuilderConfigurator(){
        selectedIngredients = new boolean[INGREDIENTS.length];
        selectedIngredients[0] = true;
    }

    public double calculatePrice(){
        double toret=0;
        for(int i= 0; i< selectedIngredients.length; i++){
            if (selectedIngredients[i]) toret+=INGREDIENTS_COSTS[i];
            if (applyDiscount){
                toret = toret - (toret*DISCOUNT);
            }
        }
        if (toret>0){
            toret+= SERVICE_COSTS[serviceModeIndexSelected];
        }

        return toret;
    }
    public boolean[] getSelectedIngredients(){
        return selectedIngredients;
    }

    public void setSelectedIngredients(boolean[] selectedIngredients) {
        this.selectedIngredients = selectedIngredients;
    }

    public void setServiceModeIndexSelected(int serviceModeIndexSelected) {
        this.serviceModeIndexSelected = serviceModeIndexSelected;
    }

    public int getServiceModeIndexSelected() {
        return serviceModeIndexSelected;
    }

    public boolean isApplyDiscount() {
        return applyDiscount;
    }

    public void setApplyDiscount(boolean applyDiscount) {
        this.applyDiscount = applyDiscount;
    }
}
