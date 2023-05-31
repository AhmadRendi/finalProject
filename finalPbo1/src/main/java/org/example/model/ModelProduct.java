package org.example.model;

import org.example.entity.Product;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ModelProduct extends AbstractTableModel {

    private List<Product> list = new ArrayList<>();

    public ModelProduct() {
    }

    public ModelProduct(List<Product> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex){
            case 0 :
                return  list.get(rowIndex).getNameBarang();
            case 1 :
                return list.get(rowIndex).getHarga();
            case 2 :
                return list.get(rowIndex).getStock();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {

        switch (column){
            case 0:
                return "Nama Barang";
            case 1:
                return "Harga";
            case 2:
                return "Stcok";
            default:
                return null;
        }
    }
}
