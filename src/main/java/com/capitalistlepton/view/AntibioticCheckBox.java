package com.capitalistlepton.view;

import com.capitalistlepton.Plague;
import com.capitalistlepton.model.Antibiotic;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zanelittrell on 3/10/17.
 */
public class AntibioticCheckBox extends JCheckBox {

    private Antibiotic anti;

    public AntibioticCheckBox(Antibiotic a, Plague instance) {
        super(a.label() + ": $" + a.cost());
        this.setFont(new Font("Menlo", Font.PLAIN, 12));
        this.setForeground(Color.WHITE);
        this.addItemListener(instance);
        this.anti = a;
    }

    public Antibiotic getAntibiotic() {
        return anti;
    }
}
