package com.capitalistlepton.plague.view;

import com.capitalistlepton.plague.Plague;
import com.capitalistlepton.plague.PlagueConstants;
import com.capitalistlepton.plague.model.Antibiotic;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zanelittrell on 3/10/17.
 */
public class AntibioticCheckBox extends JCheckBox {

    private Antibiotic anti;

    public AntibioticCheckBox(Antibiotic a, Plague instance) {
        super(a.label() + ": $" + a.cost());
        this.setFont(PlagueConstants.GAME_FONT);
        this.setForeground(Color.WHITE);
        this.addItemListener(instance);
        this.anti = a;
    }

    public Antibiotic getAntibiotic() {
        return anti;
    }
}
