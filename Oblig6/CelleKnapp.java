// Litt snacks slik at cellene funker som knapper
// Hvis cellen lever kverkes den ved et klikk og
// lyset slukkes, motsatt hvis den er doed.
import java.awt.*;
import java.awt.event.*;
public class CelleKnapp implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Kobler knapp og celle
        // Oppdaterer status, farge og antall levende celler.
        Celle c = (Celle)e.getSource();
        if (c.levende) {
            c.settDoed();
            c.setBackground(Color.BLACK);
            GomOfLof.levPanel.setText("Antall levende: " + GomOfLof.v.rutenett.antallLevende());
        } else {
            c.settLevende();
            c.setBackground(Color.WHITE);
            GomOfLof.levPanel.setText("Antall levende: " + GomOfLof.v.rutenett.antallLevende());
        }
    }
}
