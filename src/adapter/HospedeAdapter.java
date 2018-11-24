/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 *
 * @author jacson
 */
public class HospedeAdapter extends AbstractTableModel {
    private ArrayList linhas = null;
    
    private String[] colunas = null;

    public ArrayList getLinhas() {
        return linhas;
    }

    public void setLinhas(ArrayList linhas) {
        this.linhas = linhas;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
    
    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] linha = (Object[]) getLinhas().get(rowIndex);
        return linha[columnIndex];
    }
    
    public HospedeAdapter(ArrayList lin, String[] col) {
        setLinhas(lin);
        setColunas(col);
    }
}
