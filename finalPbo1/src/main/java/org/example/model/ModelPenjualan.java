package org.example.model;

import org.example.entity.Penjualan;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ModelPenjualan extends AbstractTableModel {

    private List<Penjualan> penjualanList = new ArrayList<>();

    public ModelPenjualan(List<Penjualan> penjualanList){
        this.penjualanList = penjualanList;
    }

    @Override
    public int getRowCount() {
        return penjualanList.size();
    }

    @Override
    public int getColumnCount() {

        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex){
            case 0 :
                return  penjualanList.get(rowIndex).getName();
            case 1 :
                return penjualanList.get(rowIndex).getHarga();
            case 2 :
                return penjualanList.get(rowIndex).getJumlah();
            case 3 :
                return penjualanList.get(rowIndex).getTotal();
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
                return "jumlah";
            case 3:
                return "Total";
            default:
                return null;
        }

    }
}
