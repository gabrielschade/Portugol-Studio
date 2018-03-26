/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.ps.ui.paineis;

import br.univali.ps.ui.abas.Aba;
import br.univali.ps.ui.swing.ColorController;
import br.univali.ps.ui.swing.Themeable;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Adson Esteves
 */
public class NewPainelTabulado extends javax.swing.JPanel implements Themeable{

    /**
     * Creates new form NewPainelTabulado
     */
    
    public NewPainelTabulado() {
        initComponents();
        configurarCores();
    }
    
    public Aba adicionaAba(Aba aba)
    {
        abaContainer.add(aba, aba.getName());
        adicionaAoCabecalho(aba);
        mudarParaAba(aba);
        aba.setPainelTabulado(this);
        SwingUtilities.invokeLater(() -> {
            aba.invalidate();
            aba.repaint();
        });
        return aba;
    }
    
    private Aba adicionaAoCabecalho(Aba aba)
    {
        cabecalhosAba.add(aba.getCabecalho());        
        return aba;
    }
    
    public Aba mudarParaAba(Aba aba)
    {        
        CardLayout cl = (CardLayout) abaContainer.getLayout();
        cl.show(abaContainer, aba.getName());
        SwingUtilities.invokeLater(() -> {
            aba.invalidate();
            aba.repaint();
        });
        return aba;
    } 
    
    public boolean contemAba(Aba aba)
    {
        Component[] components = abaContainer.getComponents();
        for (int i = 0; i < components.length; i++) {
            if(components[i] instanceof Aba)
            {                
                if((Aba)components[i] == aba)
                {                    
                    return true;
                }
            }
        }
        return false;
    }
    
    public Aba getAbaSelecionada() {
        for (Component component : getAbaContainer().getComponents()) {
            if(component instanceof Aba)
            {
                if(component.isVisible())
                {
                    return (Aba)component;
                }                
            }            
        }
        return null;
    }
    
    public void setAbaAtual(int abaNumber)
    {             
        Aba aba = (Aba)abaContainer.getComponents()[abaNumber];
        mudarParaAba(aba);
    }
    
    public void setAbaAtual(Aba aba)
    {
        mudarParaAba(aba);
    }

    public JPanel getAbaContainer() {
        return abaContainer;
    }

    public JPanel getCabecalhosAba() {
        return cabecalhosAba;
    }  
    
    
    @Override
    public void configurarCores() {
        abaContainer.setBackground(ColorController.FUNDO_MEDIO);
        abaContainer.setForeground(ColorController.COR_LETRA);
        cabecalhosAba.setBackground(ColorController.FUNDO_ESCURO);
        cabecalhosAba.setForeground(ColorController.COR_LETRA);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cabecalhosAba = new javax.swing.JPanel();
        abaContainer = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        cabecalhosAba.setMaximumSize(new java.awt.Dimension(32767, 40));
        cabecalhosAba.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));
        add(cabecalhosAba, java.awt.BorderLayout.NORTH);

        abaContainer.setLayout(new java.awt.CardLayout());
        add(abaContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaContainer;
    private javax.swing.JPanel cabecalhosAba;
    // End of variables declaration//GEN-END:variables

    
}
